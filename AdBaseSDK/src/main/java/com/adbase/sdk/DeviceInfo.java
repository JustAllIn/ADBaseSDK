package com.adbase.sdk;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

/**
 * 设备信息工具类
 */
class DeviceInfo {

    static Application application;

    /**
     * @return 当前操作系统版本号
     */
    public static String getOSVersion() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    /**
     * @return 系统名称，写死Android
     */
    public static String getOSName() {
        return "Android";
    }

    /**
     * @return 谁鄂毕名称
     */
    public static String getDeviceName() {
        return Build.BRAND + "_" + Build.MODEL;
    }

    /**
     * @return 设备id
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getDeviceId() {
        try {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                return ((TelephonyManager) application.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();      //Android 9及以下可以尝试获取IMEI
            } else {
                return Settings.Secure.getString(application.getContentResolver(), Settings.Secure.ANDROID_ID); //Android 10以上官方建议用Android id代替
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "unknown";
    }
}
