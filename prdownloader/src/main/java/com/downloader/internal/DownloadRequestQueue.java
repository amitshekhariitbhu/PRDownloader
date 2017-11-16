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

package com.downloader.internal;

import com.downloader.Status;
import com.downloader.core.Core;
import com.downloader.request.DownloadRequest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by amitshekhar on 13/11/17.
 */

public class DownloadRequestQueue {

    private static DownloadRequestQueue instance;
    private final Map<Integer, DownloadRequest> currentRequestMap;
    private final AtomicInteger sequenceGenerator;

    private DownloadRequestQueue() {
        currentRequestMap = new ConcurrentHashMap<>();
        sequenceGenerator = new AtomicInteger();
    }

    public static void initialize() {
        getInstance();
    }

    public static DownloadRequestQueue getInstance() {
        if (instance == null) {
            synchronized (DownloadRequestQueue.class) {
                if (instance == null) {
                    instance = new DownloadRequestQueue();
                }
            }
        }
        return instance;
    }

    private int getSequenceNumber() {
        return sequenceGenerator.incrementAndGet();
    }

    public void pause(int downloadId) {
        DownloadRequest request = currentRequestMap.get(downloadId);
        if (request != null) {
            request.setStatus(Status.PAUSED);
        }
    }

    public void resume(int downloadId) {
        DownloadRequest request = currentRequestMap.get(downloadId);
        if (request != null) {
            request.setStatus(Status.QUEUED);
            request.setFuture(Core.getInstance()
                    .getExecutorSupplier()
                    .forDownloadTasks()
                    .submit(new DownloadRunnable(request)));
        }
    }

    private void cancelAndRemoveFromMap(DownloadRequest request) {
        if (request != null) {
            request.cancel();
            currentRequestMap.remove(request.getDownloadId());
        }
    }

    public void cancel(int downloadId) {
        DownloadRequest request = currentRequestMap.get(downloadId);
        cancelAndRemoveFromMap(request);
    }

    public void cancel(Object tag) {
        for (Map.Entry<Integer, DownloadRequest> currentRequestMapEntry : currentRequestMap.entrySet()) {
            DownloadRequest request = currentRequestMapEntry.getValue();
            if (request.getTag() instanceof String && tag instanceof String) {
                final String tempRequestTag = (String) request.getTag();
                final String tempTag = (String) tag;
                if (tempRequestTag.equals(tempTag)) {
                    cancelAndRemoveFromMap(request);
                }
            } else if (request.getTag().equals(tag)) {
                cancelAndRemoveFromMap(request);
            }
        }
    }

    public void cancelAll() {
        for (Map.Entry<Integer, DownloadRequest> currentRequestMapEntry : currentRequestMap.entrySet()) {
            DownloadRequest request = currentRequestMapEntry.getValue();
            cancelAndRemoveFromMap(request);
        }
    }

    public Status getStatus(int downloadId) {
        DownloadRequest request = currentRequestMap.get(downloadId);
        if (request != null) {
            return request.getStatus();
        }
        return Status.UNKNOWN;
    }

    public void addRequest(DownloadRequest request) {
        currentRequestMap.put(request.getDownloadId(), request);
        request.setStatus(Status.QUEUED);
        request.setSequenceNumber(getSequenceNumber());
        request.setFuture(Core.getInstance()
                .getExecutorSupplier()
                .forDownloadTasks()
                .submit(new DownloadRunnable(request)));
    }

    public void finish(DownloadRequest request) {
        currentRequestMap.remove(request.getDownloadId());
    }
}
