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

package com.downloader.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.downloader.PRDownloader;

/**
 * WHY PREFIX PROVIDER AUTHORITY WITH "${applicationId}" :
 * Problem.
 * 1. In Android file provider -  authorities should be unique between apps, hence two different app can't have same authorities.
 * 2. Since we are defining provider in library project - If we have defined authorities as constant (like com.downloader.provider.PRDownloaderProvider) for our provider,
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
        return true;
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

    @Override
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        if (providerInfo == null) {
            throw new NullPointerException("PRDownloaderProvider ProviderInfo cannot be null.");
        }
        // So if the authorities equal the library internal ones, the developer forgot to set his applicationId
        if ("com.downloader.provider.PRDownloaderProvider".equals(providerInfo.authority)) {
            throw new IllegalStateException("Incorrect provider authority in manifest. Most likely due to a "
                    + "missing applicationId variable in application\'s build.gradle.");
        }
        super.attachInfo(context, providerInfo);
    }

}
