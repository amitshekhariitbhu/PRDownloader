package com.downloader;

import com.downloader.httpclient.DefaultHttpClient;
import com.downloader.httpclient.HttpClient;

/**
 * Created by amitshekhar on 13/11/17.
 */

public class PRDownloaderConfig {

    private int readTimeout;
    private int connectTimeout;
    private String userAgent;
    private HttpClient httpClient;
    private boolean databaseEnabled;

    private PRDownloaderConfig(Builder builder) {
        this.readTimeout = builder.readTimeout;
        this.connectTimeout = builder.connectTimeout;
        this.userAgent = builder.userAgent;
        this.httpClient = builder.httpClient;
        this.databaseEnabled = builder.databaseEnabled;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public boolean isDatabaseEnabled() {
        return databaseEnabled;
    }

    public void setDatabaseEnabled(boolean databaseEnabled) {
        this.databaseEnabled = databaseEnabled;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        int readTimeout = Constants.DEFAULT_READ_TIMEOUT_IN_MILLS;
        int connectTimeout = Constants.DEFAULT_CONNECT_TIMEOUT_IN_MILLS;
        String userAgent = Constants.DEFAULT_USER_AGENT;
        HttpClient httpClient = new DefaultHttpClient();
        boolean databaseEnabled = false;

        public Builder setReadTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder setUserAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public Builder setHttpClient(HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public Builder setDatabaseEnabled(boolean databaseEnabled) {
            this.databaseEnabled = databaseEnabled;
            return this;
        }

        public PRDownloaderConfig build() {
            return new PRDownloaderConfig(this);
        }
    }
}
