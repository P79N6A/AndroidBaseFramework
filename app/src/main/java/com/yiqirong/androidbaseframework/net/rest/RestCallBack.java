package com.yiqirong.androidbaseframework.net.rest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kangwencai on 2016/11/10.
 */

public abstract class RestCallBack<T> implements Callback<T> {
    public abstract void onFailure(RestError restError);
    public abstract void onSuccess(RestError restError);

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
//        RestError restError = (RestError) response.getBodyAs(RestError.class);
    response.toString();
    }


}