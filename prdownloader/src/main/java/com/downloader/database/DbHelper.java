package com.downloader.database;

/**
 * Created by anandgaurav on 14-11-2017.
 */

public interface DbHelper {

    DownloadModel find(int id);

    void insert(DownloadModel model);

    void update(DownloadModel model);

    void updateProgress(int id, long downloadedBytes);

    void remove(int id);

    void clear();

}
