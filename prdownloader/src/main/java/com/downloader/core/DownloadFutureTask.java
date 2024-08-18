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
