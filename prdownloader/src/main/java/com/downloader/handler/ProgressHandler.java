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

package com.downloader.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.downloader.Constants;
import com.downloader.OnProgressListener;
import com.downloader.Progress;

/**
 * Created by amitshekhar on 13/11/17.
 */

public class ProgressHandler extends Handler {

    private final OnProgressListener listener;
    private Long progressInterval;
    private Long timeCounter = 0L;

    public ProgressHandler(Long progressInterval, OnProgressListener listener) {
        super(Looper.getMainLooper());
        this.progressInterval = progressInterval;
        this.listener = listener;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case Constants.UPDATE:
                if (listener != null) {
                    final Progress progress = (Progress) msg.obj;
                    if (progress.currentBytes == progress.totalBytes || System.currentTimeMillis() > timeCounter + progressInterval) {
                        listener.onProgress(progress);
                        timeCounter = System.currentTimeMillis();
                    }
                }
                break;
            default:
                super.handleMessage(msg);
                break;
        }
    }
}
