package com.adbase.sdk;

import android.util.Log;

/**
 * 日志工具类
 */
class AdBaseLog {

    private static final boolean DEBUG = true;


    /**
     * 日志打印
     *
     * @param msg msg
     */
    static void i(String msg) {
        if (!DEBUG) {
            return;
        }

        Log.i("adbase", msg);
    }
}
