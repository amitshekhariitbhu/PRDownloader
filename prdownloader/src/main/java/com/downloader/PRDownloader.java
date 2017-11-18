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

package com.downloader;

/**
 * Created by amitshekhar on 12/11/17.
 */

import android.content.Context;

import com.downloader.core.Core;
import com.downloader.internal.ComponentHolder;
import com.downloader.internal.DownloadRequestQueue;
import com.downloader.request.DownloadRequestBuilder;
import com.downloader.utils.Utils;

/**
 * PRDownloader entry point.
 * You must initialize this class before use. The simplest way is to just do
 * {#code PRDownloader.initialize(context)}.
 */
public class PRDownloader {

    /**
     * private constructor to prevent instantiation of this class
     */
    private PRDownloader() {
    }

    /**
     * Initializes PRDownloader with the default config.
     *
     * @param context The context
     */
    public static void initialize(Context context) {
        initialize(context, PRDownloaderConfig.newBuilder().build());
    }

    /**
     * Initializes PRDownloader with the custom config.
     *
     * @param context The context
     * @param config  The PRDownloaderConfig
     */
    public static void initialize(Context context, PRDownloaderConfig config) {
        ComponentHolder.getInstance().init(context, config);
        DownloadRequestQueue.initialize();
    }

    /**
     * Method to make download request
     *
     * @param url      The url on which request is to be made
     * @param dirPath  The directory path on which file is to be saved
     * @param fileName The file name with which file is to be saved
     * @return the DownloadRequestBuilder
     */
    public static DownloadRequestBuilder download(String url, String dirPath, String fileName) {
        return new DownloadRequestBuilder(url, dirPath, fileName);
    }

    /**
     * Method to pause request with the given downloadId
     *
     * @param downloadId The downloadId with which request is to be paused
     */
    public static void pause(int downloadId) {
        DownloadRequestQueue.getInstance().pause(downloadId);
    }

    /**
     * Method to resume request with the given downloadId
     *
     * @param downloadId The downloadId with which request is to be resumed
     */
    public static void resume(int downloadId) {
        DownloadRequestQueue.getInstance().resume(downloadId);
    }

    /**
     * Method to cancel request with the given downloadId
     *
     * @param downloadId The downloadId with which request is to be cancelled
     */
    public static void cancel(int downloadId) {
        DownloadRequestQueue.getInstance().cancel(downloadId);
    }

    /**
     * Method to cancel requests with the given tag
     *
     * @param tag The tag with which requests are to be cancelled
     */
    public static void cancel(Object tag) {
        DownloadRequestQueue.getInstance().cancel(tag);
    }

    /**
     * Method to cancel all requests
     */
    public static void cancelAll() {
        DownloadRequestQueue.getInstance().cancelAll();
    }

    /**
     * Method to check the request with the given downloadId is running or not
     *
     * @param downloadId The downloadId with which request status is to be checked
     * @return the running status
     */
    public static Status getStatus(int downloadId) {
        return DownloadRequestQueue.getInstance().getStatus(downloadId);
    }

    /**
     * Method to clean up temporary resumed files which is older than the given day
     *
     * @param days the days
     */
    public static void cleanUp(int days) {
        Utils.deleteUnwantedModelsAndTempFiles(days);
    }

    /**
     * Shuts PRDownloader down
     */
    public static void shutDown() {
        Core.shutDown();
    }

}
