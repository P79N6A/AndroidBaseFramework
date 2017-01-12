package com.yiqirong.androidbaseframework;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.multidex.MultiDex;

import com.yiqirong.androidbaseframework.database.DaoMaster;
import com.yiqirong.androidbaseframework.database.DaoSession;
import com.yiqirong.androidbaseframework.net.RetrofitRest;
import com.yiqirong.androidbaseframework.util_tools.CrushException;
import com.yiqirong.androidbaseframework.util_tools.image_tools.ImageLoaderUtils;


/**
 * Created by kangwencai on 2016/11/7.
 */

public class MainApplication extends Application {
    private static MainApplication application;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        application = this;
        ImageLoaderUtils.getInstance();

        new RetrofitRest();
        CrushException.getInstance(this).init(this);

        setDatabase();

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

    /**
     * 获取根储存路径，外部缓存路径,会随着程序卸载而删除，如果不想卸载后还有残余就使用这个路径
     * 1.首选是sdcard下面的android/data/包名称/。。目录，但是这个路径不是一定可以建立的，
     * 有时有因为储存卡挂载状态和媒体库异常状态可能临时建立不成功，
     * 2.次选存放到data/包名称/data下，这里对应设置里面缓存数据
     * //
     *
     * @return
     */
    public String getRootCacheDirPath() {
        String state = Environment.getExternalStorageState();
        boolean isremoved = Environment.isExternalStorageRemovable();
        if (state.equals(Environment.MEDIA_MOUNTED) || !Environment.isExternalStorageRemovable()) {
            // 已挂载，放在手机的Picture目录下，不会因为软件卸载而删除
//            File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//            这个目录下的文件会随着卸载而卸载
            return getExternalCacheDir().getPath();
        } else {
            return getCacheDir().getPath();
        }
    }



    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

}
