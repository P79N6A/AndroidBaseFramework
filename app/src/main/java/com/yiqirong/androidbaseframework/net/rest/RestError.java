package com.yiqirong.androidbaseframework.net.rest;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by kangwencai on 2016/11/10.
 */

@Parcel
public class RestError {
    String code;

    String strMessage;

    public RestError() {
    }

    public RestError(String strMessage) {
        this.strMessage = strMessage;
    }

    //Getters and setters


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStrMessage() {
        return strMessage;
    }

    public void setStrMessage(String strMessage) {
        this.strMessage = strMessage;
    }
}
