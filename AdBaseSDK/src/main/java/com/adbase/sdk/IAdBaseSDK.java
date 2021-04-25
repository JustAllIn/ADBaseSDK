package com.adbase.sdk;

import android.app.Application;

/**
 * 抽象的AdBaseSDK
 * 外部通过 {@link F#create(Application)} 方法获取SDK实例，并调用相关接口
 * SDK内部控制具体的逻辑实现
 */
public interface IAdBaseSDK {

    /**
     * simple factory
     */
    final class F {

        /**
         * 简单工厂方法，保留一定的拓展性
         * （虽然目前只有一个版本的SDK实现）
         *
         * @param application application context
         * @return 获取SDK实例
         */
        public static IAdBaseSDK create(Application application) {
            DeviceInfo.application = application;
            return AdBaseSdkV1.instance;
        }
    }

    int open();

    int join(String type, String name);

    int login(String type, String name);

    int logout();

    int exit();

}
