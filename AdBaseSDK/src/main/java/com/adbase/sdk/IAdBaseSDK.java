package com.adbase.sdk;

public interface IAdBaseSDK {

    int open();

    int heartbeat();

    int join();

    int login();

    int logout();

    int exit();

}
