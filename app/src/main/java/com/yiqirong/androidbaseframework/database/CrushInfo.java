package com.yiqirong.androidbaseframework.database;

import com.yiqirong.androidbaseframework.MainApplication;
import com.yiqirong.androidbaseframework.util_tools.CommonUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kangwencai on 2017/1/12.
 * 奔溃异常类
 */
@Entity
public class CrushInfo {

    @Id
    private Long id;
    //平台
    String type;
    //时间
    String time;

    //版本
    String version_code;
    //在哪个用户使用时发生的
    String user_name;
    //"手机型号: " +
    String phone_model;
    //"系统版本:" +
    String system_version;
    //"SDK版本:" +
    String sdk_version;

    //异常提示
    String content;
    //标题，异常类型+发生位置作为标题
    String title;

    public CrushInfo() {
        type = "android";
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss", Locale.CHINA);
        time = format.format(date);
        version_code = CommonUtil.getVersionCode(MainApplication.getApplication()) + "";
        phone_model = android.os.Build.MODEL;
        sdk_version = android.os.Build.VERSION.SDK;
        system_version = android.os.Build.VERSION.RELEASE;
    }


    @Generated(hash = 1380945079)
    public CrushInfo(Long id, String type, String time, String version_code, String user_name,
                     String phone_model, String system_version, String sdk_version, String content,
                     String title) {
        this.id = id;
        this.type = type;
        this.time = time;
        this.version_code = version_code;
        this.user_name = user_name;
        this.phone_model = phone_model;
        this.system_version = system_version;
        this.sdk_version = sdk_version;
        this.content = content;
        this.title = title;
    }


    @Override
    public String toString() {

        //为了方便后面进行读取
        return "{" +
                "type:" + type + "\n" +
                " time:" + time + "\n" +
                " version_code:" + version_code + "\n" +
                " user_name:" + user_name + "\n" +
                " phone_model:" + phone_model + "\n" +
                " system_version:" + system_version + "\n" +
                " sdk_version:" + sdk_version + "\n" +
                " title:" + title + "\n" +
                " content:" + content + "}";
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getType() {
        return this.type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getTime() {
        return this.time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public String getVersion_code() {
        return this.version_code;
    }


    public void setVersion_code(String version_code) {
        this.version_code = version_code;
    }


    public String getUser_name() {
        return this.user_name;
    }


    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }


    public String getPhone_model() {
        return this.phone_model;
    }


    public void setPhone_model(String phone_model) {
        this.phone_model = phone_model;
    }


    public String getSystem_version() {
        return this.system_version;
    }


    public void setSystem_version(String system_version) {
        this.system_version = system_version;
    }


    public String getSdk_version() {
        return this.sdk_version;
    }


    public void setSdk_version(String sdk_version) {
        this.sdk_version = sdk_version;
    }


    public String getContent() {
        return this.content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public String getTitle() {
        return this.title;
    }


    public void setTitle(String title) {
        this.title = title;
    }
}