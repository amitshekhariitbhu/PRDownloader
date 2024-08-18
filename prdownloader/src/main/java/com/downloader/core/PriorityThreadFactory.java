package com.downloader.core;

import android.os.Process;

import java.util.concurrent.ThreadFactory;

/**
 * Created by amitshekhar on 13/11/17.
 */

public class PriorityThreadFactory implements ThreadFactory {

    private final int mThreadPriority;

    PriorityThreadFactory(int threadPriority) {
        mThreadPriority = threadPriority;
    }

    @Override
    public Thread newThread(final Runnable runnable) {
        Runnable wrapperRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Process.setThreadPriority(mThreadPriority);
                } catch (Throwable ignored) {

                }
                runnable.run();
            }
        };
        return new Thread(wrapperRunnable);
    }
}