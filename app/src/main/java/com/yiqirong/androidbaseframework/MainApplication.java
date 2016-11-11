package com.yiqirong.androidbaseframework;

import android.app.Application;


import com.yiqirong.androidbaseframework.net.RetrofitRest;


/**
 * Created by kangwencai on 2016/11/7.
 */

public class MainApplication extends Application {
    private static MainApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;


        new RetrofitRest();


//        String appId = "77099-1";
//        String appVersion = "";
//        HotFixManager.getInstance().initialize(this, appVersion, appId, true, new PatchLoadStatusListener() {
//            @Override
//            public void onload(int mode, int code, String info, int handlePatchVersion) {
//                // 补丁加载回调通知
//                if (code == PatchStatusCode.CODE_SUCCESS_LOAD) {
//                    // TODO: 10/24/16 表明补丁加载成功
//                } else if (code == PatchStatusCode.CODE_ERROR_NEEDRESTART) {
//                    // TODO: 10/24/16 表明新补丁生效需要重启. 业务方可自行实现逻辑, 提示用户或者强制重启, 可以监听应用进入后台事件, 然后应用自杀
//                } else {
//                    // TODO: 10/25/16 其它信息
//                }
//            }
//        });
//        HotFixManager.getInstance().queryNewHotPatch();
    }

    public static MainApplication getApplication() {
        return application;
    }
}
