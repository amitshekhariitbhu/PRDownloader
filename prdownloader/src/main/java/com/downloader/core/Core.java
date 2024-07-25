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
