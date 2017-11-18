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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anandgaurav on 14-11-2017.
 */

public class AppDbHelper implements DbHelper {

    public static final String TABLE_NAME = "prdownloader";
    private final SQLiteDatabase db;

    public AppDbHelper(Context context) {
        DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(context);
        db = databaseOpenHelper.getWritableDatabase();
    }

    @Override
    public DownloadModel find(int id) {
        Cursor cursor = null;
        DownloadModel model = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +
                    DownloadModel.ID + " = " + id, null);
            if (cursor != null && cursor.moveToFirst()) {
                model = new DownloadModel();
                model.setId(id);
                model.setUrl(cursor.getString(cursor.getColumnIndex(DownloadModel.URL)));
                model.setETag(cursor.getString(cursor.getColumnIndex(DownloadModel.ETAG)));
                model.setDirPath(cursor.getString(cursor.getColumnIndex(DownloadModel.DIR_PATH)));
                model.setFileName(cursor.getString(cursor.getColumnIndex(DownloadModel.FILE_NAME)));
                model.setTotalBytes(cursor.getLong(cursor.getColumnIndex(DownloadModel.TOTAL_BYTES)));
                model.setDownloadedBytes(cursor.getLong(cursor.getColumnIndex(DownloadModel.DOWNLOADED_BYTES)));
                model.setLastModifiedAt(cursor.getLong(cursor.getColumnIndex(DownloadModel.LAST_MODIFIED_AT)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return model;
    }

    @Override
    public void insert(DownloadModel model) {
        try {
            ContentValues values = new ContentValues();
            values.put(DownloadModel.ID, model.getId());
            values.put(DownloadModel.URL, model.getUrl());
            values.put(DownloadModel.ETAG, model.getETag());
            values.put(DownloadModel.DIR_PATH, model.getDirPath());
            values.put(DownloadModel.FILE_NAME, model.getFileName());
            values.put(DownloadModel.TOTAL_BYTES, model.getTotalBytes());
            values.put(DownloadModel.DOWNLOADED_BYTES, model.getDownloadedBytes());
            values.put(DownloadModel.LAST_MODIFIED_AT, model.getLastModifiedAt());
            db.insert(TABLE_NAME, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(DownloadModel model) {
        try {
            ContentValues values = new ContentValues();
            values.put(DownloadModel.URL, model.getUrl());
            values.put(DownloadModel.ETAG, model.getETag());
            values.put(DownloadModel.DIR_PATH, model.getDirPath());
            values.put(DownloadModel.FILE_NAME, model.getFileName());
            values.put(DownloadModel.TOTAL_BYTES, model.getTotalBytes());
            values.put(DownloadModel.DOWNLOADED_BYTES, model.getDownloadedBytes());
            values.put(DownloadModel.LAST_MODIFIED_AT, model.getLastModifiedAt());
            db.update(TABLE_NAME, values, DownloadModel.ID + " = ? ",
                    new String[]{String.valueOf(model.getId())});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProgress(int id, long downloadedBytes, long lastModifiedAt) {
        try {
            ContentValues values = new ContentValues();
            values.put(DownloadModel.DOWNLOADED_BYTES, downloadedBytes);
            values.put(DownloadModel.LAST_MODIFIED_AT, lastModifiedAt);
            db.update(TABLE_NAME, values, DownloadModel.ID + " = ? ",
                    new String[]{String.valueOf(id)});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        try {
            db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " +
                    DownloadModel.ID + " = " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DownloadModel> getUnwantedModels(int days) {
        List<DownloadModel> models = new ArrayList<>();
        Cursor cursor = null;
        try {
            final long daysInMillis = days * 24 * 60 * 60 * 1000L;
            final long beforeTimeInMillis = System.currentTimeMillis() - daysInMillis;
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +
                    DownloadModel.LAST_MODIFIED_AT + " <= " + beforeTimeInMillis, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    DownloadModel model = new DownloadModel();
                    model.setId(cursor.getInt(cursor.getColumnIndex(DownloadModel.ID)));
                    model.setUrl(cursor.getString(cursor.getColumnIndex(DownloadModel.URL)));
                    model.setETag(cursor.getString(cursor.getColumnIndex(DownloadModel.ETAG)));
                    model.setDirPath(cursor.getString(cursor.getColumnIndex(DownloadModel.DIR_PATH)));
                    model.setFileName(cursor.getString(cursor.getColumnIndex(DownloadModel.FILE_NAME)));
                    model.setTotalBytes(cursor.getLong(cursor.getColumnIndex(DownloadModel.TOTAL_BYTES)));
                    model.setDownloadedBytes(cursor.getLong(cursor.getColumnIndex(DownloadModel.DOWNLOADED_BYTES)));
                    model.setLastModifiedAt(cursor.getLong(cursor.getColumnIndex(DownloadModel.LAST_MODIFIED_AT)));

                    models.add(model);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return models;
    }

    @Override
    public void clear() {
        try {
            db.delete(TABLE_NAME, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
