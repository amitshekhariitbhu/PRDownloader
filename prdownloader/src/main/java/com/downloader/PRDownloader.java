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
import com.downloader.internal.DownloadRequestQueue;
import com.downloader.request.DownloadRequestBuilder;

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
     * Shuts PRDownloader down
     */
    public static void shutDown() {
        Core.shutDown();
    }

}
