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
        super(maxNumThreads, maxNumThreads, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<Runnable>(), threadFactory);
    }

    @Override
    public Future<?> submit(Runnable task) {
        DownloadFutureTask futureTask = new DownloadFutureTask((DownloadRunnable) task);
        execute(futureTask);
        return futureTask;
    }
}
