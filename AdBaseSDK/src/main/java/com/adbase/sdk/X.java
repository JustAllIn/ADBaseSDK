package com.adbase.sdk;

import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * 日志工具类
 */
enum X {

    log;

    private WeakReference<IAdBaseSDK.ILogPrinter> weakReference;


    private static final boolean DEBUG = true;
    private static final String TAG = "adbase";

    /**
     * 日志打印
     *
     * @param msg msg
     */
    void i(String msg) {
        if (!DEBUG) {
            return;
        }
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        weakReference.get().log(msg);
    }

    public void setPrinter(IAdBaseSDK.ILogPrinter printer) {
        weakReference = new WeakReference<>(printer);
    }
}
