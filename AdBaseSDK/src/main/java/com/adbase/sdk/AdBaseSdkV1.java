package com.adbase.sdk;

import android.app.Application;
import android.content.Context;

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

    private String alive_id;    // TODO: 2021/4/24 在open接口请求成功后返回并缓存
    private HeartBeatHandler mHeartBeat;
    private CRCAssets crcAssets;

    AdBaseSdkV1() {
        apiProxy = new ApiProxy();
    }

    @Override
    public int open(Context context) {
        if (crcAssets == null) {
            crcAssets = new CRCAssets();
            crcAssets.init(context);
        }

        final String seatId = crcAssets.getSeatId();
        final String appCrc = crcAssets.getAppCrc();
        apiProxy.open(seatId, appCrc)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        alive_id = response.body();    // TODO: 2021/4/24 确定不需要解析吗
                        mHeartBeat = new HeartBeatHandler(alive_id, apiProxy);
                        mHeartBeat.start();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
        return 0;
    }

    private int heartbeat() {
        apiProxy.heartBeat(alive_id)
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
        final String type = ""; // TODO: 2021/4/24 ?
        final String name = ""; // TODO: 2021/4/24 ?
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
    public int exit() {
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
