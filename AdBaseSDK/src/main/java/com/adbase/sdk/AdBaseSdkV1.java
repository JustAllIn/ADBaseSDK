package com.adbase.sdk;

import android.app.Application;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 第一个版本的sdk实现类
 *
 * @author Hua
 */
enum AdBaseSdkV1 implements IAdBaseSDK {

    instance;

    private final ApiProxy apiProxy;
    private final ActivityLifeCircle mActivityLifecycleCallbacks = new ActivityLifeCircle() {
        /**
         * app回到前台
         */
        @Override
        void onAppForeground() {
            if (mHeartBeat != null) {
                mHeartBeat.start();
            }
        }

        /**
         * app退到后台
         */
        @Override
        void onAppBackground() {
            if (mHeartBeat != null) {
                mHeartBeat.stop();
            }
        }
    };

    private String alive_id;    // TODO: 2021/4/24 在open接口请求成功后返回并缓存
    private HeartBeatHandler mHeartBeat;
    private CRCAssets crcAssets;


    AdBaseSdkV1() {
        apiProxy = new ApiProxy();
    }

    @Override
    public int open(Application application) {
        if (crcAssets == null) {
            crcAssets = new CRCAssets();
            if (!crcAssets.init(application)) {
                //初始化失败
                return -1;
            }
        }
        application.registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks);

        final String seatId = crcAssets.getSeatId();
        final String appCrc = crcAssets.getAppCrc();
        apiProxy.open(seatId, appCrc)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        alive_id = response.body();    // TODO: 2021/4/24 确定不需要解析吗
                        if (mHeartBeat == null) {
                            mHeartBeat = new HeartBeatHandler(alive_id, apiProxy);
                        }
                        mHeartBeat.start();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
        return 0;
    }

    @Override
    public int join(String type, String name) {
        apiProxy.join(alive_id, type, name)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
        return 0;
    }

    @Override
    public int login(String type, String name) {
        apiProxy.login(alive_id, type, name)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
        return 0;
    }

    @Override
    public int logout() {
        apiProxy.logout(alive_id)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
        return 0;
    }

    @Override
    public int exit(Application application) {
        application.unregisterActivityLifecycleCallbacks(mActivityLifecycleCallbacks);

        apiProxy.exit(alive_id)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
        return 0;
    }
}
