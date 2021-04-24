package com.adbse;

public interface IAdBaseSDK {

    int open();

    int heartbeat();

    int join();

    int login();

    int logout();

    int exit();

}
