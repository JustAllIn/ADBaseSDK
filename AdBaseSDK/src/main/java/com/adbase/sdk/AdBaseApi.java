package com.adbase.sdk;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * 管理 网络请求接口 定义
 */
public interface AdBaseApi {

    /**
     * @return 目前的唯一接口
     */
    @GET("s.php")
    Call<String> commonApi(
            @FieldMap Map<String, String> postParam,
            @QueryMap Map<String, String> getParam
    );
}
