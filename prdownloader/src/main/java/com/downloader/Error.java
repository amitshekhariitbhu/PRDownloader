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

import java.util.List;
import java.util.Map;

/**
 * Created by amitshekhar on 13/11/17.
 */

public class Error {

    private boolean isServerError;
    private boolean isConnectionError;
    private String serverErrorMessage;
    private Map<String, List<String>> headerFields;
    private Throwable connectionException;
    private int responseCode;

    public boolean isServerError() {
        return isServerError;
    }

    public void setServerError(boolean serverError) {
        isServerError = serverError;
    }

    public boolean isConnectionError() {
        return isConnectionError;
    }

    public void setConnectionError(boolean connectionError) {
        isConnectionError = connectionError;
    }

    public void setServerErrorMessage(String serverErrorMessage) {
        this.serverErrorMessage = serverErrorMessage;
    }

    public String getServerErrorMessage() {
        return serverErrorMessage;
    }

    public void setHeaderFields(Map<String, List<String>> headerFields) {
        this.headerFields = headerFields;
    }

    public Map<String, List<String>> getHeaderFields() {
        return headerFields;
    }

    public void setConnectionException(Throwable connectionException) {
        this.connectionException = connectionException;
    }

    public Throwable getConnectionException() {
        return connectionException;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
