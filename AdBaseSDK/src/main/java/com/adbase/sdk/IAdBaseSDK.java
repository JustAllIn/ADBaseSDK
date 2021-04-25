package com.adbase.sdk;

public interface IAdBaseSDK {

    /**
     * simple factory
     */
    final class F {

        /**
         * 简单工厂方法，保留一定的拓展性
         * （虽然目前只有一个版本的SDK实现）
         *
         * @param type 根据不同type类型可以获取不同的SDK实例，便于后续可能的拓展
         * @return 获取SDK实例
         */
        public static IAdBaseSDK create(int type) {
            return AdBaseSdkV1.instance;
        }
    }

    int open();

    int join();

    int login();

    int logout();

    int exit();

}
