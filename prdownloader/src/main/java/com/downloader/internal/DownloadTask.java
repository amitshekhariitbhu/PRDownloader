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

import com.downloader.Response;
import com.downloader.request.DownloadRequest;

/**
 * Created by amitshekhar on 13/11/17.
 */

public class DownloadTask {

    private final DownloadRequest request;

    public static DownloadTask create(DownloadRequest request) {
        return new DownloadTask(request);
    }

    private DownloadTask(DownloadRequest request) {
        this.request = request;
    }

    public Response fetch() {
        Response response = new Response();
        return response;
    }

}