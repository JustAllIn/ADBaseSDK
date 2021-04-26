package com.adbase.sdk;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * 网络请求代理
 * 构造最简单的网络库，维护接口调用及协议字段
 */
final class ApiProxy {

    private AdBaseApi mApi;
    private static final String baseUrl = "http://www.adbase.com";


    /**
     * @param seatId read from assets
     * @param crc    read from assets
     * @return request call
     */
    Call<ResponseBody> open(final String seatId, final String crc) {

        Map<String, String> getParams = new LinkedHashMap<>();
        getParams.put("a", "o");
        getParams.put("s", seatId);
        getParams.put("c", crc);
        getParams.put("di", DeviceInfo.getDeviceId());
        getParams.put("dn", DeviceInfo.getDeviceName());
        getParams.put("on", DeviceInfo.getOSName());
        getParams.put("ov", DeviceInfo.getOSVersion());
        // add more post params if need

        return getApi().commonApi(AdBaseAuth.addAuth(getParams));
    }

    /**
     * @param aliveId get from api-open
     * @return request call
     */
    Call<ResponseBody> heartBeat(final String aliveId) {

        Map<String, String> getParams = new LinkedHashMap<>();
        getParams.put("a", "h");
        getParams.put("ai", aliveId);
        getParams.put("di", DeviceInfo.getDeviceId());
        // add more post params if need

        return getApi().commonApi(AdBaseAuth.addAuth(getParams));
    }

    /**
     * @param aliveId get from api-open
     * @param type    ?
     * @param name    ?
     * @return request call
     */
    Call<ResponseBody> join(final String aliveId, final String type, final String name) {
        // add more post params if need

        Map<String, String> getParams = new LinkedHashMap<>();
        getParams.put("a", "j");
        getParams.put("ai", aliveId);
        getParams.put("di", DeviceInfo.getDeviceId());
        getParams.put("t", type);
        getParams.put("n", name);
        // add more post params if need

        return getApi().commonApi(AdBaseAuth.addAuth(getParams));
    }


    /**
     * @param aliveId get from api-open
     * @param type    ?
     * @param name    ?
     * @return request call
     */
    Call<ResponseBody> login(final String aliveId, final String type, final String name) {

        Map<String, String> getParams = new LinkedHashMap<>();
        getParams.put("a", "li");
        getParams.put("ai", aliveId);
        getParams.put("di", DeviceInfo.getDeviceId());
        getParams.put("t", type);
        getParams.put("n", name);
        // add more post params if need

        return getApi().commonApi(AdBaseAuth.addAuth(getParams));
    }

    /**
     * @param aliveId get from api-open
     * @return request call
     */
    Call<ResponseBody> logout(final String aliveId) {

        Map<String, String> getParams = new LinkedHashMap<>();
        getParams.put("a", "lo");
        getParams.put("ai", aliveId);
        getParams.put("di", DeviceInfo.getDeviceId());
        // add more post params if need

        return getApi().commonApi(AdBaseAuth.addAuth(getParams));
    }

    /**
     * @param aliveId get from api-open
     * @return request call
     */
    Call<ResponseBody> exit(final String aliveId) {

        Map<String, String> getParams = new LinkedHashMap<>();
        getParams.put("a", "e");
        getParams.put("ai", aliveId);
        getParams.put("di", DeviceInfo.getDeviceId());
        // add more post params if need

        return getApi().commonApi(AdBaseAuth.addAuth(getParams));
    }


    /**
     * @return lazy init retrofit
     */
    private AdBaseApi getApi() {
        if (mApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(new OkHttpClient())
                    .build();
            mApi = retrofit.create(AdBaseApi.class);
        }
        return mApi;
    }
}
