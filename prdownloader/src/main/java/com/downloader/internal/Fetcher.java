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
import com.downloader.Progress;
import com.downloader.Response;
import com.downloader.handler.ProgressHandler;
import com.downloader.httpclient.DefaultHttpClient;
import com.downloader.httpclient.HttpClient;
import com.downloader.request.DownloadRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Locale;

/**
 * Created by amitshekhar on 13/11/17.
 */

public class Fetcher {

    private static final int BUFFER_SIZE = 1024 * 4;
    private final DownloadRequest request;
    private ProgressHandler progressHandler;

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

            HttpClient httpClient = new DefaultHttpClient();

            httpClient.connect(request);

            final int responseCode = httpClient.getResponseCode();

            final long contentLength = httpClient.getContentLength();

            InputStream inputStream = httpClient.getInputStream();

            byte[] buff = new byte[BUFFER_SIZE];

            BufferedOutputStream outputStream = null;

            FileDescriptor fileDescriptor = null;

            try {

                final String path = request.getDirPath() + File.separator + request.getFileName();

                File file = new File(path);

                RandomAccessFile randomAccess = new RandomAccessFile(file, "rw");

                fileDescriptor = randomAccess.getFD();

                outputStream = new BufferedOutputStream(new FileOutputStream(randomAccess.getFD()));

                if (request.getDownloadedBytes() != 0) {
                    randomAccess.seek(request.getDownloadedBytes());
                }

                do {

                    int byteCount = inputStream.read(buff);

                    if (byteCount == -1) {
                        break;
                    }

                    outputStream.write(buff, 0, byteCount);

                    request.setDownloadedBytes(request.getDownloadedBytes() + byteCount);

                    if (progressHandler != null) {
                        progressHandler
                                .obtainMessage(Constants.UPDATE,
                                        new Progress(request.getDownloadedBytes(),
                                                contentLength)).sendToTarget();
                    }

                    // flush and sync
                    outputStream.flush();

                    fileDescriptor.sync();

                    if (request.isPaused()) {
                        response.setPaused(true);
                        return response;
                    }

                } while (true);

                response.setSuccessful(true);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null)
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                try {
                    if (outputStream != null) {
                        outputStream.flush();
                    }
                    if (fileDescriptor != null) {
                        fileDescriptor.sync();
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

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

}
