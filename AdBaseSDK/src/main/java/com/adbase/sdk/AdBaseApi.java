package com.adbase.sdk;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * 管理 网络请求接口 定义
 */
interface AdBaseApi {

    /**
     * @return 目前的唯一接口
     */
    @GET("s.php")
    Call<ResponseBody> commonApi(
            @QueryMap Map<String, String> getParam
    );
}
