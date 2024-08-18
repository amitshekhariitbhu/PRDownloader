package com.downloader.core;

import java.util.concurrent.Executor;

/**
 * Created by amitshekhar on 13/11/17.
 */

public interface ExecutorSupplier {

    DownloadExecutor forDownloadTasks();

    Executor forBackgroundTasks();

    Executor forMainThreadTasks();

}
