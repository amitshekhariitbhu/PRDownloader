package com.downloader;

/**
 * Created by amitshekhar on 13/11/17.
 */

public interface OnDownloadListener {

    void onDownloadComplete();

    void onError(Error error);

}
