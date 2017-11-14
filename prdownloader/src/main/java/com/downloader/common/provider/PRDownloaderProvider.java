package com.downloader.common.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.downloader.PRDownloader;

/**
 * WHY PREFIX PROVIDER AUTHORITY WITH "${applicationId}" :
 * Problem.
 * 1. In Android file provider -  authorities should be unique between apps, hence two different app can't have same authorities.
 * 2. Since we are defining provider in library project - If we have defined authorities as constant (like com.downloader.common.provider) for our provider,
 * then our library can't be used by different application.
 * <p>
 * Solution.
 * 1. Authorities - In manifest we have given authorities as "${applicationId}.prdownloaderprovider".
 * 2. ${applicationId} - In run time android will replace applicationId with application's appId (not our library's appId).
 * 3. For above step to happen - Application using our library must have set applicationId in build.gradle.
 * <p>
 * Reference
 * 1. https://firebase.googleblog.com/2016/12/how-does-firebase-initialize-on-android.html
 * 2. https://medium.com/@andretietz/auto-initialize-your-android-library-2349daf06920
 * <p>
 * Created by jyotidubey on 14/11/17.
 */

public class PRDownloaderProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        Context context = getContext();
        PRDownloader.initialize(context);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
