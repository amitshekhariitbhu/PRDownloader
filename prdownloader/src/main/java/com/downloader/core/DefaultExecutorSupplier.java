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

import android.os.Process;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by amitshekhar on 13/11/17.
 */

public class DefaultExecutorSupplier implements ExecutorSupplier {

    private static final int DEFAULT_MAX_NUM_THREADS = 2 * Runtime.getRuntime().availableProcessors() + 1;
    private final DownloadExecutor networkExecutor;
    private final Executor backgroundExecutor;
    private final Executor mainThreadExecutor;

    DefaultExecutorSupplier() {
        ThreadFactory backgroundPriorityThreadFactory = new PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND);
        networkExecutor = new DownloadExecutor(DEFAULT_MAX_NUM_THREADS, backgroundPriorityThreadFactory);
        backgroundExecutor = Executors.newSingleThreadExecutor();
        mainThreadExecutor = new MainThreadExecutor();
    }

    @Override
    public DownloadExecutor forDownloadTasks() {
        return networkExecutor;
    }

    @Override
    public Executor forBackgroundTasks() {
        return backgroundExecutor;
    }

    @Override
    public Executor forMainThreadTasks() {
        return mainThreadExecutor;
    }
}
