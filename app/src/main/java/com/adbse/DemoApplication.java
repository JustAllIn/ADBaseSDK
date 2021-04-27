package com.adbse;

import android.app.Application;

import com.adbase.sdk.IAdBaseSDK;

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        IAdBaseSDK.F.create().init(this);
    }
}
