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

package com.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.downloader.DownloadListener;
import com.downloader.Error;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.downloader.ProgressListener;
import com.sample.utils.Utils;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String URL = "http://www.appsapk.com/downloading/latest/Facebook-119.0.0.23.70.apk";
        PRDownloader.download(URL, Utils.getRootDirPath(getApplicationContext()), "facebook.apk")
                .build()
                .setProgressListener(new ProgressListener() {
                    @Override
                    public void onProgress(Progress progress) {
                        Log.d(TAG, " onProgress :" + progress.toString());
                    }
                })
                .start(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        Log.d(TAG, "onDownloadComplete");
                    }

                    @Override
                    public void onError(Error error) {
                        Log.d(TAG, "onError");
                    }
                });
    }
}
