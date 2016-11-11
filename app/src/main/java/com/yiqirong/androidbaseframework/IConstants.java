package com.yiqirong.androidbaseframework;

import android.os.Environment;

/**
 * Created by kangwencai on 2016/11/10.
 */

public interface IConstants {


    // 外部存储设备的根路径
    String ExternalStorageRootPath = Environment.getExternalStorageDirectory().getPath();
    // app目录根路径
    String BasePath = ExternalStorageRootPath + "/b3a4a/";
    // 文件存放路径
    String FileCachePath = BasePath + "FileCache/";
    // 保存图片
    String ImageCachePath = BasePath + "ImageCache/";
    // 下载存储地址
    String DOWNLOADPath = BasePath + "DWONLOAD/";

}
