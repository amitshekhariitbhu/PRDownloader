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

package com.downloader.request;

import com.downloader.Priority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amitshekhar on 13/11/17.
 */

public class DownloadRequestBuilder implements RequestBuilder {

    String url;
    String dirPath;
    String fileName;
    Long progressInterval = 100L;
    Priority priority = Priority.MEDIUM;
    Object tag;
    int readTimeout;
    int connectTimeout;
    String userAgent;
    HashMap<String, List<String>> headerMap;

    public DownloadRequestBuilder(String url, String dirPath, String fileName) {
        this.url = url;
        this.dirPath = dirPath;
        this.fileName = sanitizeFilename(fileName);
    }

    /**
     * replace all illegal file name characters with "_"
     */
    public static String sanitizeFilename(String filename) {
        // Define the set of illegal characters for filenames
        String illegalChars = "[/\\\\?%*:|\"<>]";

        // Replace illegal characters with an underscore
        return filename.replaceAll(illegalChars, "_");
    }

    @Override
    public DownloadRequestBuilder setHeader(String name, String value) {
        if (headerMap == null) {
            headerMap = new HashMap<>();
        }
        List<String> list = headerMap.get(name);
        if (list == null) {
            list = new ArrayList<>();
            headerMap.put(name, list);
        }
        if (!list.contains(value)) {
            list.add(value);
        }
        return this;
    }

    @Override
    public DownloadRequestBuilder setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public DownloadRequestBuilder setTag(Object tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public DownloadRequestBuilder setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    @Override
    public DownloadRequestBuilder setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    @Override
    public DownloadRequestBuilder setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    @Override
    public RequestBuilder setProgressInterval(Long progressInterval) {
        this.progressInterval = progressInterval;
        return this;
    }

    public DownloadRequest build() {
        return new DownloadRequest(this);
    }

}
