package com.downloader;

/**
 * Created by amitshekhar on 13/11/17.
 */

public final class Constants {

    private Constants() {
        // no instance
    }

    public static final int UPDATE = 0x01;
    public static final String RANGE = "Range";
    public static final String ETAG = "ETag";
    public static final String USER_AGENT = "User-Agent";
    public static final String DEFAULT_USER_AGENT = "PRDownloader";

    public static final int DEFAULT_READ_TIMEOUT_IN_MILLS = 20_000;
    public static final int DEFAULT_CONNECT_TIMEOUT_IN_MILLS = 20_000;

    public static final int HTTP_RANGE_NOT_SATISFIABLE = 416;
    public static final int HTTP_TEMPORARY_REDIRECT = 307;
    public static final int HTTP_PERMANENT_REDIRECT = 308;

}
