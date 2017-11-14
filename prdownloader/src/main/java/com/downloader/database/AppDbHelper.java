package com.downloader.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
                    DownloadModel.ID + " = " + id, new String[]{String.valueOf(id)});
            cursor.moveToFirst();
            model = new DownloadModel();
            model.setId(id);
            model.setUrl(cursor.getString(cursor.getColumnIndex(DownloadModel.URL)));
            model.setDirPath(cursor.getString(cursor.getColumnIndex(DownloadModel.DIR_PATH)));
            model.setFileName(cursor.getString(cursor.getColumnIndex(DownloadModel.FILE_NAME)));
            model.setTotalBytes(cursor.getLong(cursor.getColumnIndex(DownloadModel.TOTAL_BYTES)));
            model.setDownloadedBytes(cursor.getLong(cursor.getColumnIndex(DownloadModel.DOWNLOADED_BYTES)));
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return model;
    }

    @Override
    public void insert(DownloadModel model) {
        ContentValues values = new ContentValues();
        values.put(DownloadModel.ID, model.getId());
        values.put(DownloadModel.URL, model.getUrl());
        values.put(DownloadModel.DIR_PATH, model.getDirPath());
        values.put(DownloadModel.FILE_NAME, model.getFileName());
        values.put(DownloadModel.TOTAL_BYTES, model.getTotalBytes());
        values.put(DownloadModel.DOWNLOADED_BYTES, model.getDownloadedBytes());
        db.insert(TABLE_NAME, null, values);
    }

    @Override
    public void update(DownloadModel model) {
        ContentValues values = new ContentValues();
        values.put(DownloadModel.URL, model.getUrl());
        values.put(DownloadModel.DIR_PATH, model.getDirPath());
        values.put(DownloadModel.FILE_NAME, model.getFileName());
        values.put(DownloadModel.TOTAL_BYTES, model.getTotalBytes());
        values.put(DownloadModel.DOWNLOADED_BYTES, model.getDownloadedBytes());
        db.update(TABLE_NAME, values, DownloadModel.ID + " = ? ",
                new String[]{String.valueOf(model.getId())});
    }

    @Override
    public void updateProgress(int id, long downloadedBytes) {
        ContentValues values = new ContentValues();
        values.put(DownloadModel.DOWNLOADED_BYTES, downloadedBytes);
        db.update(TABLE_NAME, values, DownloadModel.ID + " = ? ",
                new String[]{String.valueOf(id)});
    }

    @Override
    public void remove(int id) {
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " +
                DownloadModel.ID + " = " + id);
    }

    @Override
    public void clear() {
        db.delete(TABLE_NAME, null, null);
    }
}
