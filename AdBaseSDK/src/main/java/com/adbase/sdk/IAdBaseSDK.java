package com.adbase.sdk;

/**
 * 抽象的AdBaseSDK
 * 外部通过{@link F#create(int)}方法获取SDK实例，并调用相关接口
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
         * @param type 根据不同type类型可以获取不同的SDK实例，便于后续可能的拓展
         * @return 获取SDK实例
         */
        public static IAdBaseSDK create(int type) {
            return AdBaseSdkV1.instance;
        }
    }

    int open();

    int join(String type, String name);

    int login(String type, String name);

    int logout();

    int exit();

}
