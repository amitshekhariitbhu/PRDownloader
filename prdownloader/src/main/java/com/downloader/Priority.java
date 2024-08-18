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
