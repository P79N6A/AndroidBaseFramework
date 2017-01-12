package com.yiqirong.androidbaseframework;

import android.os.Environment;

/**
 * Created by kangwencai on 2016/11/10.
 */

public interface IConstants {


    //是否是调试模式
    boolean IS_DEBUG = true;


    // 外部存储设备的根路径
    String ExternalStorageRootPath = Environment.getExternalStorageDirectory().getPath();
    // app目录根路径
    String BasePath = ExternalStorageRootPath + "/cai/";
    // 文件存放路径
    String FileCachePath = BasePath + "FileCache/";
    // 保存图片
    String ImageCachePath = BasePath + "ImageCache/";
    // 下载存储地址
    String DOWNLOADPath = BasePath + "DWONLOAD/";


    //外部缓存路径,会随着程序卸载而删除，但是这个路径不是一定可以建立的，有时有因为储存卡挂载状态和媒体库异常状态可能临时建立不成功
    String ExternalCashPath = MainApplication.getApplication().getRootCacheDirPath();
    //日志的路径
    String LogPath = ExternalCashPath + "/log/";
    //    String LogPath = BasePath + "/log/";
    // 保存图片
//    String ImageCachePath = ExternalCashPath + "/ImageCache/";

}
