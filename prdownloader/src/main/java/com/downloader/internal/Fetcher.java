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
import com.downloader.database.DownloadModel;
import com.downloader.handler.ProgressHandler;
import com.downloader.httpclient.DefaultHttpClient;
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

    public static Fetcher create(DownloadRequest request) {
        return new Fetcher(request);
    }

    private Fetcher(DownloadRequest request) {
        this.request = request;
    }

    public Response fetch() {

        Response response = new Response();

        try {

            if (request.getProgressListener() != null) {
                progressHandler = new ProgressHandler(request.getProgressListener());
            }

            DownloadModel model = getDownloadModelIfAlreadyPresentInDatabase();

            if (model != null) {
                request.setTotalBytes(model.getTotalBytes());
                request.setDownloadedBytes(model.getDownloadedBytes());
            }

            httpClient = new DefaultHttpClient();

            httpClient.connect(request);

            httpClient = Utils.getRedirectedConnectionIfAny(httpClient, request);

            final int responseCode = httpClient.getResponseCode();

            if (!isSuccessful(responseCode)) {
                Error error = new Error();
                error.setServerError(true);
                response.setError(error);
                return response;
            }

            totalBytes = request.getTotalBytes();

            if (totalBytes == 0) {
                totalBytes = httpClient.getContentLength();
                request.setTotalBytes(totalBytes);
            }

            if (model == null) {
                createAndInsertNewModel();
            }

            inputStream = httpClient.getInputStream();

            byte[] buff = new byte[BUFFER_SIZE];

            final String path = request.getDirPath() + File.separator + request.getFileName();

            File file = new File(path);

            RandomAccessFile randomAccess = new RandomAccessFile(file, "rw");

            fileDescriptor = randomAccess.getFD();

            outputStream = new BufferedOutputStream(new FileOutputStream(randomAccess.getFD()));

            if (request.getDownloadedBytes() != 0) {
                randomAccess.seek(request.getDownloadedBytes());
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

                if (request.isPaused()) {
                    sync();
                    response.setPaused(true);
                    return response;
                }

            } while (true);

            response.setSuccessful(true);

            removeCompletedModelFromDatabase();

        } catch (IOException | IllegalAccessException e) {
            Error error = new Error();
            error.setConnectionError(true);
            response.setError(error);
        } finally {
            closeAllSafely();
        }

        return response;
    }

    private boolean isSuccessful(int code) {
        return code == HttpURLConnection.HTTP_OK || code == HttpURLConnection.HTTP_PARTIAL;
    }

    private DownloadModel getDownloadModelIfAlreadyPresentInDatabase() {
        return ComponentHolder.getInstance().getDbHelper().find(request.getDownloadId());
    }

    private void createAndInsertNewModel() {
        DownloadModel model = new DownloadModel();
        model.setId(request.getDownloadId());
        model.setUrl(request.getUrl());
        model.setDirPath(request.getDirPath());
        model.setFileName(request.getFileName());
        model.setDownloadedBytes(request.getDownloadedBytes());
        model.setTotalBytes(totalBytes);
        ComponentHolder.getInstance().getDbHelper().insert(model);
    }

    private void removeCompletedModelFromDatabase() {
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
        ComponentHolder.getInstance().getDbHelper()
                .updateProgress(request.getDownloadId(), request.getDownloadedBytes());
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
