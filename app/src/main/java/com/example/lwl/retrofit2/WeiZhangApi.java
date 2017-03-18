package com.example.lwl.retrofit2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by LWL on 2017/2/20.
 */

public interface WeiZhangApi {
    @GET("citys")
    Call<ResultDataResponse> getWeiZhang(@Query("key") String key);
}
