package com.downloader.database;

import java.util.List;

/**
 * Created by anandgaurav on 14-11-2017.
 */

public interface DbHelper {

    DownloadModel find(int id);

    void insert(DownloadModel model);

    void update(DownloadModel model);

    void updateProgress(int id, long downloadedBytes, long lastModifiedAt);

    void remove(int id);

    List<DownloadModel> getUnwantedModels(int days);

    void clear();

}
