package com.adbase.sdk;

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

    AdBaseSdkV1() {
        apiProxy = new ApiProxy();
    }

    @Override
    public int open() {
        apiProxy.open(getSeatId(), getAppCRC())
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


    private String getAppCRC() {
        // TODO: 2021/4/24 从哪儿来
        return null;
    }

    private String getSeatId() {
        // TODO: 2021/4/24 从哪儿来
        return null;
    }
}
