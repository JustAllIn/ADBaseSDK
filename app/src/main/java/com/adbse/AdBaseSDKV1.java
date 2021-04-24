package com.adbse;

public enum  AdBaseSDKV1 implements IAdBaseSDK{

    Instance;

    private final ApiProxy mApiProxy;


    AdBaseSDKV1() {
        mApiProxy = new ApiProxy();
    }

    @Override
    public int open() {
        return 0;
    }

    @Override
    public int heartbeat() {
        return 0;
    }

    @Override
    public int join() {
        return 0;
    }

    @Override
    public int login() {
        return 0;
    }

    @Override
    public int logout() {
        return 0;
    }

    @Override
    public int exit() {
        return 0;
    }
}
