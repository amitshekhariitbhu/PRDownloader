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

package com.downloader.core;

import com.downloader.Priority;
import com.downloader.internal.DownloadRunnable;

import java.util.concurrent.FutureTask;

/**
 * Created by amitshekhar on 13/11/17.
 */

public class DownloadFutureTask extends FutureTask<DownloadRunnable> implements Comparable<DownloadFutureTask> {

    private final DownloadRunnable runnable;

    DownloadFutureTask(DownloadRunnable downloadRunnable) {
        super(downloadRunnable, null);
        this.runnable = downloadRunnable;
    }

    @Override
    public int compareTo(DownloadFutureTask other) {
        Priority p1 = runnable.priority;
        Priority p2 = other.runnable.priority;
        return (p1 == p2 ? runnable.sequence - other.runnable.sequence : p2.ordinal() - p1.ordinal());
    }
}
