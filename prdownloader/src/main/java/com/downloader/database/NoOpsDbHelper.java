package com.downloader.database;

/**
 * Created by anandgaurav on 14-11-2017.
 */

public class NoOpsDbHelper implements DbHelper {

    public NoOpsDbHelper() {

    }

    @Override
    public DownloadModel find(int id) {
        return null;
    }

    @Override
    public void insert(DownloadModel model) {

    }

    @Override
    public void update(DownloadModel model) {

    }

    @Override
    public void updateProgress(int id, long downloadedBytes) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void clear() {

    }
}
