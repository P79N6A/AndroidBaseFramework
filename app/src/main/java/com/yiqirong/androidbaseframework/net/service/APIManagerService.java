package com.yiqirong.androidbaseframework.net.service;


import com.yiqirong.androidbaseframework.net.rest.ApiResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by kangwencai on 2016/11/10.
 */

public interface APIManagerService {
    @POST("test/")
    Call<ApiResponse> getWeather(@Query("q") String strCity);
    @POST("test/")
    Call<ApiResponse> getWeather(@Body RequestBody body);
}
