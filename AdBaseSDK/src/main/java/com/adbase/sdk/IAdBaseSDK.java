package com.adbase.sdk;

import android.content.Context;

/**
 * 抽象的AdBaseSDK
 * 外部通过 {@link #create()} 方法获取SDK实例，并调用相关接口
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
         * @return 获取SDK实例
         */
        public static IAdBaseSDK create() {
            return AdBaseSdkV1.instance;
        }
    }

    int open(Context context);

    int join(String type, String name);

    int login(String type, String name);

    int logout();

    int exit();

}
