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
