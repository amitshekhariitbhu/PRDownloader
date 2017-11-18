/*
 *    Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.downloader.database;

import java.util.List;

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
    public void updateProgress(int id, long downloadedBytes, long lastModifiedAt) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<DownloadModel> getUnwantedModels(int days) {
        return null;
    }

    @Override
    public void clear() {

    }
}
