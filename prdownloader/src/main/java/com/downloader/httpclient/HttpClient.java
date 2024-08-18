package com.downloader.httpclient;

import com.downloader.request.DownloadRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

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

    Map<String, List<String>> getHeaderFields();

    InputStream getErrorStream() throws IOException;

}
