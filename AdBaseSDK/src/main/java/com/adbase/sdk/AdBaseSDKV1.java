package com.adbase.sdk;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public enum  AdBaseSDKV1 implements IAdBaseSDK{

    Instance;

    private final ApiProxy mApiProxy;

    private String alive_id;    // TODO: 2021/4/24 在open接口请求成功后返回并缓存


    AdBaseSDKV1() {
        mApiProxy = new ApiProxy();
    }

    @Override
    public int open() {
        mApiProxy.open(getSeatId(),getAppCRC())
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                       alive_id  =  response.body();    // TODO: 2021/4/24 确定不需要解析吗
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
        return 0;
    }

    @Override
    public int heartbeat() {
        mApiProxy.heartBeat(alive_id)
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
    public int join() {
        final String type = ""; // TODO: 2021/4/24 ?
        final String name = ""; // TODO: 2021/4/24 ?

        mApiProxy.join(alive_id,type,name)
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
    public int login() {
        final String type = ""; // TODO: 2021/4/24 ?
        final String name = ""; // TODO: 2021/4/24 ?

        mApiProxy.login(alive_id,type,name)
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
        mApiProxy.login(alive_id,type,name)
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
        mApiProxy.exit(alive_id)
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
