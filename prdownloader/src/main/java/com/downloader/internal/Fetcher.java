/*
 *    Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.downloader.internal;

import com.downloader.Constants;
import com.downloader.Error;
import com.downloader.Progress;
import com.downloader.Response;
import com.downloader.Status;
import com.downloader.database.DownloadModel;
import com.downloader.handler.ProgressHandler;
import com.downloader.httpclient.HttpClient;
import com.downloader.request.DownloadRequest;
import com.downloader.utils.Utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.SyncFailedException;
import java.net.HttpURLConnection;

/**
 * Created by amitshekhar on 13/11/17.
 */

public class Fetcher {

    private static final int BUFFER_SIZE = 1024 * 4;
    private static final long TIME_GAP_FOR_SYNC = 2000;
    private final DownloadRequest request;
    private ProgressHandler progressHandler;
    private long lastSyncTime;
    private BufferedOutputStream outputStream;
    private FileDescriptor fileDescriptor;
    private InputStream inputStream;
    private HttpClient httpClient;
    private long totalBytes;
    private int responseCode;
    private String eTag;
    private boolean isResumeSupported;
    private String tempPath;

    private Fetcher(DownloadRequest request) {
        this.request = request;
    }

    public static Fetcher create(DownloadRequest request) {
        return new Fetcher(request);
    }

    public Response fetch() {

        Response response = new Response();

        if (request.getStatus() == Status.CANCELLED) {
            response.setCancelled(true);
            return response;
        } else if (request.getStatus() == Status.PAUSED) {
            response.setPaused(true);
            return response;
        }

        try {

            if (request.getOnProgressListener() != null) {
                progressHandler = new ProgressHandler(request.getOnProgressListener());
            }

            tempPath = Utils.getTempPath(request.getDirPath(), request.getFileName());

            DownloadModel model = getDownloadModelIfAlreadyPresentInDatabase();

            if (model != null) {
                request.setTotalBytes(model.getTotalBytes());
                request.setDownloadedBytes(model.getDownloadedBytes());
            }

            httpClient = ComponentHolder.getInstance().getHttpClient();

            httpClient.connect(request);

            if (request.getStatus() == Status.CANCELLED) {
                response.setCancelled(true);
                return response;
            } else if (request.getStatus() == Status.PAUSED) {
                response.setPaused(true);
                return response;
            }

            httpClient = Utils.getRedirectedConnectionIfAny(httpClient, request);

            responseCode = httpClient.getResponseCode();

            eTag = httpClient.getResponseHeader("ETag");

            if (checkIfFreshStartRequiredAndStart(model)) {
                model = null;
            }

            if (!isSuccessful()) {
                Error error = new Error();
                error.setServerError(true);
                response.setError(error);
                return response;
            }

            setResumeSupportedOrNot();

            totalBytes = request.getTotalBytes();

            if (!isResumeSupported) {
                deleteTempFile();
            }

            if (totalBytes == 0) {
                totalBytes = httpClient.getContentLength();
                request.setTotalBytes(totalBytes);
            }

            if (isResumeSupported && model == null) {
                createAndInsertNewModel();
            }

            if (request.getStatus() == Status.CANCELLED) {
                response.setCancelled(true);
                return response;
            } else if (request.getStatus() == Status.PAUSED) {
                response.setPaused(true);
                return response;
            }

            request.deliverStartEvent();

            inputStream = httpClient.getInputStream();

            byte[] buff = new byte[BUFFER_SIZE];

            createOutputStreamAndSeekIfRequired();

            if (request.getStatus() == Status.CANCELLED) {
                response.setCancelled(true);
                return response;
            } else if (request.getStatus() == Status.PAUSED) {
                response.setPaused(true);
                return response;
            }

            do {

                final int byteCount = inputStream.read(buff);

                if (byteCount == -1) {
                    break;
                }

                outputStream.write(buff, 0, byteCount);

                request.setDownloadedBytes(request.getDownloadedBytes() + byteCount);

                sendProgress();

                syncIfRequired();

                if (request.getStatus() == Status.CANCELLED) {
                    response.setCancelled(true);
                    return response;
                } else if (request.getStatus() == Status.PAUSED) {
                    sync();
                    response.setPaused(true);
                    return response;
                }

            } while (true);

            final String path = Utils.getPath(request.getDirPath(), request.getFileName());

            Utils.renameFileName(tempPath, path);

            response.setSuccessful(true);

            if (isResumeSupported) {
                removeNoMoreNeededModelFromDatabase();
            }

        } catch (IOException | IllegalAccessException e) {
            if (!isResumeSupported) {
                deleteTempFile();
            }
            Error error = new Error();
            error.setConnectionError(true);
            response.setError(error);
        } finally {
            closeAllSafely();
        }

        return response;
    }

    private void createOutputStreamAndSeekIfRequired() throws IOException {
        File file = new File(tempPath);
        RandomAccessFile randomAccess = new RandomAccessFile(file, "rw");
        fileDescriptor = randomAccess.getFD();
        outputStream = new BufferedOutputStream(new FileOutputStream(randomAccess.getFD()));
        if (isResumeSupported && request.getDownloadedBytes() != 0) {
            randomAccess.seek(request.getDownloadedBytes());
        }
    }

    private void deleteTempFile() {
        File file = new File(tempPath);
        if (file.exists()) {
            file.delete();
        }
    }

    private boolean isSuccessful() {
        return responseCode >= HttpURLConnection.HTTP_OK
                && responseCode < HttpURLConnection.HTTP_MULT_CHOICE;
    }

    private void setResumeSupportedOrNot() {
        isResumeSupported = (responseCode == HttpURLConnection.HTTP_PARTIAL);
    }

    private boolean checkIfFreshStartRequiredAndStart(DownloadModel model) throws IOException,
            IllegalAccessException {
        if (responseCode == Constants.HTTP_RANGE_NOT_SATISFIABLE || isETagChanged(model)) {
            if (model != null) {
                removeNoMoreNeededModelFromDatabase();
            }
            deleteTempFile();
            request.setDownloadedBytes(0);
            request.setTotalBytes(0);
            httpClient = ComponentHolder.getInstance().getHttpClient();
            httpClient.connect(request);
            httpClient = Utils.getRedirectedConnectionIfAny(httpClient, request);
            responseCode = httpClient.getResponseCode();
            return true;
        }
        return false;
    }

    private boolean isETagChanged(DownloadModel model) {
        return !(eTag == null || model == null || model.getETag() == null)
                && !model.getETag().equals(eTag);
    }

    private DownloadModel getDownloadModelIfAlreadyPresentInDatabase() {
        return ComponentHolder.getInstance().getDbHelper().find(request.getDownloadId());
    }

    private void createAndInsertNewModel() {
        DownloadModel model = new DownloadModel();
        model.setId(request.getDownloadId());
        model.setUrl(request.getUrl());
        model.setETag(eTag);
        model.setDirPath(request.getDirPath());
        model.setFileName(request.getFileName());
        model.setDownloadedBytes(request.getDownloadedBytes());
        model.setTotalBytes(totalBytes);
        ComponentHolder.getInstance().getDbHelper().insert(model);
    }

    private void removeNoMoreNeededModelFromDatabase() {
        ComponentHolder.getInstance().getDbHelper().remove(request.getDownloadId());
    }

    private void sendProgress() {
        if (progressHandler != null) {
            progressHandler
                    .obtainMessage(Constants.UPDATE,
                            new Progress(request.getDownloadedBytes(),
                                    totalBytes)).sendToTarget();
        }
    }

    private void syncIfRequired() throws IOException {
        if (System.currentTimeMillis() - lastSyncTime > TIME_GAP_FOR_SYNC) {
            sync();
        }
    }

    private void sync() throws IOException {
        outputStream.flush();
        fileDescriptor.sync();
        if (isResumeSupported) {
            ComponentHolder.getInstance().getDbHelper()
                    .updateProgress(request.getDownloadId(), request.getDownloadedBytes());
        }
        lastSyncTime = System.currentTimeMillis();
    }

    private void closeAllSafely() {
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileDescriptor != null) {
                try {
                    fileDescriptor.sync();
                } catch (SyncFailedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            if (outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

}
