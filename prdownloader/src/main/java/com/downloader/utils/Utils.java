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

package com.downloader.utils;

import com.downloader.Constants;
import com.downloader.core.Core;
import com.downloader.database.DownloadModel;
import com.downloader.httpclient.HttpClient;
import com.downloader.internal.ComponentHolder;
import com.downloader.request.DownloadRequest;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by amitshekhar on 13/11/17.
 */

public final class Utils {

    private final static int MAX_REDIRECTION = 10;

    private Utils() {
        // no instance
    }

    public static String getPath(String dirPath, String fileName) {
        return dirPath + File.separator + fileName;
    }

    public static String getTempPath(String dirPath, String fileName) {
        return getPath(dirPath, fileName) + ".temp";
    }

    public static void renameFileName(String oldPath, String newPath) throws IOException {
        final File oldFile = new File(oldPath);
        try {
            final File newFile = new File(newPath);
            if (newFile.exists()) {
                if (!newFile.delete()) {
                    throw new IOException("Deletion Failed");
                }
            }
            if (!oldFile.renameTo(newFile)) {
                throw new IOException("Rename Failed");
            }
        } finally {
            if (oldFile.exists()) {
                //noinspection ResultOfMethodCallIgnored
                oldFile.delete();
            }
        }
    }

    public static void deleteTempFileAndDatabaseEntryInBackground(final String path, final int downloadId) {
        Core.getInstance().getExecutorSupplier().forBackgroundTasks()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        ComponentHolder.getInstance().getDbHelper().remove(downloadId);
                        File file = new File(path);
                        if (file.exists()) {
                            //noinspection ResultOfMethodCallIgnored
                            file.delete();
                        }
                    }
                });
    }

    public static void deleteUnwantedModelsAndTempFiles(final int days) {
        Core.getInstance().getExecutorSupplier().forBackgroundTasks()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        List<DownloadModel> models = ComponentHolder.getInstance()
                                .getDbHelper()
                                .getUnwantedModels(days);
                        if (models != null) {
                            for (DownloadModel model : models) {
                                final String tempPath = getTempPath(model.getDirPath(), model.getFileName());
                                ComponentHolder.getInstance().getDbHelper().remove(model.getId());
                                File file = new File(tempPath);
                                if (file.exists()) {
                                    //noinspection ResultOfMethodCallIgnored
                                    file.delete();
                                }
                            }
                        }
                    }
                });
    }

    public static int getUniqueId(String url, String dirPath, String fileName) {

        String string = url + File.separator + dirPath + File.separator + fileName;

        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);

        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }

        return hex.toString().hashCode();

    }

    public static HttpClient getRedirectedConnectionIfAny(HttpClient httpClient,
                                                          DownloadRequest request)
            throws IOException, IllegalAccessException {
        int redirectTimes = 0;
        int code = httpClient.getResponseCode();
        String location = httpClient.getResponseHeader("Location");

        while (isRedirection(code)) {
            if (location == null) {
                throw new IllegalAccessException("Location is null");
            }
            httpClient.close();

            request.setUrl(location);
            httpClient = ComponentHolder.getInstance().getHttpClient();
            httpClient.connect(request);
            code = httpClient.getResponseCode();
            location = httpClient.getResponseHeader("Location");
            redirectTimes++;
            if (redirectTimes >= MAX_REDIRECTION) {
                throw new IllegalAccessException("Max redirection done");
            }
        }

        return httpClient;
    }

    private static boolean isRedirection(int code) {
        return code == HttpURLConnection.HTTP_MOVED_PERM
                || code == HttpURLConnection.HTTP_MOVED_TEMP
                || code == HttpURLConnection.HTTP_SEE_OTHER
                || code == HttpURLConnection.HTTP_MULT_CHOICE
                || code == Constants.HTTP_TEMPORARY_REDIRECT
                || code == Constants.HTTP_PERMANENT_REDIRECT;
    }

}
