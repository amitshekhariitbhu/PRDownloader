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

package com.downloader;

/**
 * Created by amitshekhar on 13/11/17.
 */

/**
 * Priority levels recognized by the request server.
 */
public enum Priority {

    /**
     * Lowest priority level. Used for prefetches of data.
     */
    LOW,

    /**
     * Medium priority level. Used for warming of data that might soon get visible.
     */
    MEDIUM,

    /**
     * Highest priority level. Used for data that are currently visible on screen.
     */
    HIGH,

    /**
     * Highest priority level. Used for data that are required instantly(mainly for emergency).
     */
    IMMEDIATE

}
