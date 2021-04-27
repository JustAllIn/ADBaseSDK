package com.adbase.sdk;

import android.app.Application;
import android.text.TextUtils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 第一个版本的sdk实现类
 *
 * @author Hua
 */
enum AdBaseSdkV1 implements IAdBaseSDK {

    /**
     * 枚举型单例
     */
    instance;

    /**
     * 网络接口请求代理
     */
    private final ApiProxy apiProxy = new ApiProxy();
    ;
    /**
     * app生命周期监听
     */
    private final ActivityLifeCycle mActivityLifecycleCallbacks = new ActivityLifeCycle() {
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

    /**
     * 在open接口请求成功后返回并缓存
     */
    private String alive_id;
    /**
     * 心跳管理器
     */
    private HeartBeatHandler mHeartBeat;
    /**
     * assets中的crc文件管理
     */
    private CRCAssets crcAssets;

    /**
     * @param printer 日志打印
     */
    @Override
    public void setLogger(ILogPrinter printer) {
        X.log.setPrinter(printer);
    }

    @Override
    public int open(Application application) {
        //静态缓存一个application对象
        DeviceInfo.application = application;
        //从assets里读取crc信息，读取失败则抛错return
        if (crcAssets == null) {
            crcAssets = new CRCAssets();
            if (!crcAssets.init(application)) {
                //初始化失败
                return -1;
            }
        }
        //注册生命周期监听
        application.registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks);

        //请求后端
        final String seatId = crcAssets.getSeatId();
        final String appCrc = crcAssets.getAppCrc();

        X.log.i("[api]======>open : start, seatId = " + seatId + ",appCrc = " + appCrc);
        apiProxy.open(seatId, appCrc)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String result = response.body().string();
                            String[] spilt = result.split("\\|");
                            alive_id = spilt[1];
                            X.log.i("[api]======>open  success: response = " + result + ",解析得到alive_id = " + alive_id);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (!TextUtils.isEmpty(alive_id)) {
                            if (mHeartBeat == null) {
                                mHeartBeat = new HeartBeatHandler(alive_id, apiProxy);
                            }
                            mHeartBeat.start();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        X.log.i("[api]======>open  failed: t = " + t.getMessage());
                    }
                });
        return 0;
    }

    @Override
    public int join(String type, String name) {
        if (TextUtils.isEmpty(alive_id)) {
            return -1;
        }

        if (TextUtils.isEmpty(type) || TextUtils.isEmpty(name)) {
            return -2;
        }

        X.log.i("[api]======>join : start, alive_id = " + alive_id);
        apiProxy.join(alive_id, type, name)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            X.log.i("[api]======>join : success, result = " + response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        X.log.i("[api]======>join : failed, t = " + t.getMessage());
                    }
                });
        return 0;
    }

    @Override
    public int login(String type, String name) {
        if (TextUtils.isEmpty(alive_id)) {
            return -1;
        }

        if (TextUtils.isEmpty(type) || TextUtils.isEmpty(name)) {
            return -2;
        }

        X.log.i("[api]======>login : start, alive_id = " + alive_id + ",type = " + type + ",name = " + name);
        apiProxy.login(alive_id, type, name)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            X.log.i("[api]======>login : success, result = " + response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        X.log.i("[api]======>login : failed, t = " + t.getMessage());
                    }
                });
        return 0;
    }

    @Override
    public int logout() {
        if (TextUtils.isEmpty(alive_id)) {
            return -1;
        }
        X.log.i("[api]======>logout : start, alive_id = " + alive_id);
        apiProxy.logout(alive_id)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            X.log.i("[api]======>logout : success, result = " + response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        X.log.i("[api]======>logout : failed, t = " + t.getMessage());
                    }
                });
        return 0;
    }

    @Override
    public int exit(Application application) {
        application.unregisterActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
        if (TextUtils.isEmpty(alive_id)) {
            return -1;
        }
        X.log.i("[api]======>exit : start, alive_id = " + alive_id);
        apiProxy.exit(alive_id)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            X.log.i("[api]======>exit : success, result = " + response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        X.log.i("[api]======>exit : failed, t = " + t.getMessage());
                    }
                });
        return 0;
    }
}
