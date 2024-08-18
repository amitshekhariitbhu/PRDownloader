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
