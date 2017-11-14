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

import com.downloader.PRDownloaderConfig;

/**
 * Created by amitshekhar on 14/11/17.
 */

public class ComponentHolder {

    private final static ComponentHolder INSTANCE = new ComponentHolder();

    private PRDownloaderConfig config;

    public static ComponentHolder getInstance() {
        return INSTANCE;
    }

    public PRDownloaderConfig getConfig() {
        if (config == null) {
            config = PRDownloaderConfig.newBuilder().build();
        }
        return config;
    }

    public void setConfig(PRDownloaderConfig config) {
        this.config = config;
    }

}
