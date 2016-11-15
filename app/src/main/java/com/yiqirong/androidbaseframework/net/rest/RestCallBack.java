package com.yiqirong.androidbaseframework.net.rest;


import com.google.gson.Gson;
import com.yiqirong.androidbaseframework.util_tools.image_tools.LogUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kangwencai on 2016/11/10.
 */

public abstract class RestCallBack<T extends ApiResponse> implements Callback<T> {
    /*系统错误*/
    private static final String SYS_ERROR = "SYS_ERROR";
    /*请求成功*/
    private static final String OK = "OK";
    /*定义一个静态常量专门来解析gson数据*/
    public static final Gson gson = new Gson();

    /**
     * @param result
     */
    public abstract void onSuccess(String result);

    /**
     * @param msg
     */
    public abstract void onFailure(String msg);


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        ApiResponse apiResponse = response.body();
        if (null==apiResponse){
            onFailure("请求失败");
            return;
        }
        String code = apiResponse.getCode();
        String msg = apiResponse.getSummary();
        LogUtils.e(code);
        if (OK.equals(code)) {
            onSuccess(apiResponse.getResult());
        } else {
            onFailure(msg);
        }


    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

        onFailure("请求失败");
    }
}