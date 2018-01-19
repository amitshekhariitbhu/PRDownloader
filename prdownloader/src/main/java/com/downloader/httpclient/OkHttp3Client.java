package com.downloader.httpclient;

import com.downloader.Constants;
import com.downloader.request.DownloadRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by diego.francisco on 19/01/2018.
 */

public class OkHttp3Client implements HttpClient {

    private Response response;

    public OkHttp3Client() {

    }

    @Override
    public HttpClient clone() {
        return new OkHttp3Client();
    }

    @Override
    public void connect(DownloadRequest request) throws IOException {
        final String range = String.format(Locale.ENGLISH,
                "bytes=%d-", request.getDownloadedBytes());

        Request httpRequest = new Request.Builder()
                .url(request.getUrl())
                .addHeader(Constants.USER_AGENT, request.getUserAgent())
                .addHeader(Constants.RANGE, range)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(request.getReadTimeout(), TimeUnit.MILLISECONDS)
                .connectTimeout(request.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .build();

        response = client.newCall(httpRequest).execute();
    }

    @Override
    public int getResponseCode() throws IOException {
        return response.code();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return response.body().byteStream();
    }

    @Override
    public long getContentLength() {
        String length = getResponseHeader("Content-Length");
        try {
            return Long.parseLong(length);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @Override
    public String getResponseHeader(String name) {
        return response.header(name);
    }

    @Override
    public void close() {
        response.close();
    }

}
