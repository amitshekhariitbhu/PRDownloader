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

package com.downloader.httpclient;

import com.downloader.request.DownloadRequest;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by amitshekhar on 13/11/17.
 */

public interface HttpClient extends Cloneable {

    HttpClient clone();

    void connect(DownloadRequest request) throws IOException;

    int getResponseCode() throws IOException;

    InputStream getInputStream() throws IOException;

    long getContentLength();

    String getResponseHeader(String name);

    void close();

}
