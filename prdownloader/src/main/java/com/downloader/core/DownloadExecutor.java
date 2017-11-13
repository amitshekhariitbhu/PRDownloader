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

import com.downloader.internal.DownloadRunnable;

import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by amitshekhar on 13/11/17.
 */

public class DownloadExecutor extends ThreadPoolExecutor {

    DownloadExecutor(int maxNumThreads, ThreadFactory threadFactory) {
        super(maxNumThreads, maxNumThreads, 0, TimeUnit.MILLISECONDS,
                new PriorityBlockingQueue<Runnable>(), threadFactory);
    }

    @Override
    public Future<?> submit(Runnable task) {
        DownloadFutureTask futureTask = new DownloadFutureTask((DownloadRunnable) task);
        execute(futureTask);
        return futureTask;
    }
}
