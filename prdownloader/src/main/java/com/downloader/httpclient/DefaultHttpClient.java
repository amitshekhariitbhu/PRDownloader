package com.downloader.httpclient;

import com.downloader.Constants;
import com.downloader.request.DownloadRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Created by amitshekhar on 13/11/17.
 */

public class DefaultHttpClient implements HttpClient {

    private URLConnection connection;

    public DefaultHttpClient() {

    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    @Override
    public HttpClient clone() {
        return new DefaultHttpClient();
    }

    @Override
    public void connect(DownloadRequest request) throws IOException {
        connection = new URL(request.getUrl()).openConnection();
        connection.setReadTimeout(request.getReadTimeout());
        connection.setConnectTimeout(request.getConnectTimeout());
        final String range = String.format(Locale.ENGLISH,
                "bytes=%d-", request.getDownloadedBytes());
        connection.addRequestProperty(Constants.RANGE, range);
        connection.addRequestProperty(Constants.USER_AGENT, request.getUserAgent());
        addHeaders(request);
        connection.connect();
    }

    @Override
    public int getResponseCode() throws IOException {
        int responseCode = 0;
        if (connection instanceof HttpURLConnection) {
            responseCode = ((HttpURLConnection) connection).getResponseCode();
        }
        return responseCode;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return connection.getInputStream();
    }

    @Override
    public long getContentLength() {
        String length = connection.getHeaderField("Content-Length");
        try {
            return Long.parseLong(length);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @Override
    public String getResponseHeader(String name) {
        return connection.getHeaderField(name);
    }

    @Override
    public void close() {
        // no operation
    }

    @Override
    public Map<String, List<String>> getHeaderFields() {
        return connection.getHeaderFields();
    }

    @Override
    public InputStream getErrorStream() {
        if (connection instanceof HttpURLConnection) {
            return ((HttpURLConnection) connection).getErrorStream();
        }
        return null;
    }

    private void addHeaders(DownloadRequest request) {
        final HashMap<String, List<String>> headers = request.getHeaders();
        if (headers != null) {
            Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
            for (Map.Entry<String, List<String>> entry : entries) {
                String name = entry.getKey();
                List<String> list = entry.getValue();
                if (list != null) {
                    for (String value : list) {
                        connection.addRequestProperty(name, value);
                    }
                }
            }
        }
    }

}
