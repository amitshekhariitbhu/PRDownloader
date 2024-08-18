package com.downloader.internal;

import com.downloader.Error;
import com.downloader.Priority;
import com.downloader.Response;
import com.downloader.Status;
import com.downloader.request.DownloadRequest;

/**
 * Created by amitshekhar on 13/11/17.
 */

public class DownloadRunnable implements Runnable {

    public final Priority priority;
    public final int sequence;
    public final DownloadRequest request;

    DownloadRunnable(DownloadRequest request) {
        this.request = request;
        this.priority = request.getPriority();
        this.sequence = request.getSequenceNumber();
    }

    @Override
    public void run() {
        request.setStatus(Status.RUNNING);
        DownloadTask downloadTask = DownloadTask.create(request);
        Response response = downloadTask.run();
        if (response.isSuccessful()) {
            request.deliverSuccess();
        } else if (response.isPaused()) {
            request.deliverPauseEvent();
        } else if (response.getError() != null) {
            request.deliverError(response.getError());
        } else if (!response.isCancelled()) {
            request.deliverError(new Error());
        }
    }

}
