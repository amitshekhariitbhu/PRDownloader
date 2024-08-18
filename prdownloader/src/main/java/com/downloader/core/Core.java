package com.downloader.core;

/**
 * Created by amitshekhar on 13/11/17.
 */

public class Core {

    private static Core instance = null;
    private final ExecutorSupplier executorSupplier;

    private Core() {
        this.executorSupplier = new DefaultExecutorSupplier();
    }

    public static Core getInstance() {
        if (instance == null) {
            synchronized (Core.class) {
                if (instance == null) {
                    instance = new Core();
                }
            }
        }
        return instance;
    }

    public ExecutorSupplier getExecutorSupplier() {
        return executorSupplier;
    }

    public static void shutDown() {
        if (instance != null) {
            instance = null;
        }
    }
}
