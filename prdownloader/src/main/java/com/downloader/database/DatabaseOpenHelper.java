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

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                AppDbHelper.TABLE_NAME + "( " +
                DownloadModel.ID + " INTEGER PRIMARY KEY, " +
                DownloadModel.URL + " VARCHAR, " +
                DownloadModel.DIR_PATH + " VARCHAR, " +
                DownloadModel.FILE_NAME + " VARCHAR, " +
                DownloadModel.TOTAL_BYTES + " INTEGER, " +
                DownloadModel.DOWNLOADED_BYTES + " INTEGER " +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}