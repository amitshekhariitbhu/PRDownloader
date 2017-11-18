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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by anandgaurav on 14-11-2017.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "prdownloader.db";
    private static final int DATABASE_VERSION = 1;

    DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                AppDbHelper.TABLE_NAME + "( " +
                DownloadModel.ID + " INTEGER PRIMARY KEY, " +
                DownloadModel.URL + " VARCHAR, " +
                DownloadModel.ETAG + " VARCHAR, " +
                DownloadModel.DIR_PATH + " VARCHAR, " +
                DownloadModel.FILE_NAME + " VARCHAR, " +
                DownloadModel.TOTAL_BYTES + " INTEGER, " +
                DownloadModel.DOWNLOADED_BYTES + " INTEGER, " +
                DownloadModel.LAST_MODIFIED_AT + " INTEGER " +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
