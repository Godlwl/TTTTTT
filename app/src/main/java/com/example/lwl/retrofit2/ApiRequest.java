package com.example.lwl.retrofit2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LWL on 2017/2/20.
 */

public class ApiRequest {
    private Retrofit retrofit;
    private WeiZhangApi weiZhangApi;
    public ApiRequest(){
        retrofit=new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn/sweizhang/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public WeiZhangApi getWeizhang(){
        weiZhangApi=retrofit.create(WeiZhangApi.class);
        return weiZhangApi;
    }
}
