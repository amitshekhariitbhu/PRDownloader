package com.downloader.request;

import com.downloader.Priority;

/**
 * Created by amitshekhar on 13/11/17.
 */

public interface RequestBuilder {

    RequestBuilder setHeader(String name, String value);

    RequestBuilder setPriority(Priority priority);

    RequestBuilder setTag(Object tag);

    RequestBuilder setReadTimeout(int readTimeout);

    RequestBuilder setConnectTimeout(int connectTimeout);

    RequestBuilder setUserAgent(String userAgent);

}
