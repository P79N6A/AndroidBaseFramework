package com.yiqirong.androidbaseframework.util_tools;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Process;

import com.yiqirong.androidbaseframework.MainApplication;
import com.yiqirong.androidbaseframework.R;
import com.yiqirong.androidbaseframework.activity.MainActivity;
import com.yiqirong.androidbaseframework.database.CrushInfo;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 异常捕捉类，可以做测试或者用户使用时候的错误收集。
 * TODO://最好是用bugtags或者阿里百川的app监控替换，当然开一个服务向服务器上传异常或者自己每次启动的时候，上传一次该文件也是个不错的选择
 * <ul>
 * <li>最后退出程序的activity的onDestory里面，刷新文件，否则可能要到系统重启连接的电脑才能看到该文件，context.
 * sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
 * Uri.parse("file://" + path)));</li>
 * </ul>
 */
public class CrushException implements UncaughtExceptionHandler {
    private final String NAME = Environment.getExternalStorageDirectory()
            .getPath() + "/" + MainApplication.getApplication().getResources().getString(R.string.app_name) + "/exception/";
    //分割线
    public static final String DIVIDE_LINE = "\n---divider_line---";
    private static CrushException crushException;
    private FileWriter fileWriter;
    private PrintWriter printWriter;

    private CrushException(Context context) {
        /**
         //古老的方式，存放本地的方式现在改为数据库存储
         File file = new File(NAME);
         try {
         if (!file.exists()) {
         file.mkdirs();
         }
         file = new File(NAME, "log.txt");
         if (!file.exists()) {
         file.createNewFile();
         }
         fileWriter = new FileWriter(file, true);
         printWriter = new PrintWriter(fileWriter);
         } catch (IOException e) {
         e.printStackTrace();
         }
         **/
    }

    public void init(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static CrushException getInstance(Context context) {
        if (crushException == null) {
            crushException = new CrushException(context);
        }
        return crushException;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        /** //        古老的方式，存放本地的方式
         Date date = new Date();
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss",
         Locale.CHINA);
         printWriter.write("______" + format.format(date) + "______\n");
         ex.printStackTrace(printWriter);
         printWriter.flush();
         ex.printStackTrace();
         Process.killProcess(Process.myPid());
         **/

        try {
            CrushInfo info = new CrushInfo();
            info.setTitle(ex.toString());
            StringBuilder content = new StringBuilder();
            content.append(ex.getCause());
            StackTraceElement[] stacktrace = ex.getStackTrace();
            //异常类型+发生位置作为标题
            info.setTitle(ex.toString()  );
            for (int i = 0; i < stacktrace.length; i++) {

                content.append("\t at " + stacktrace[i].toString() + "\n");
            }
            info.setContent(content.toString());
            MainApplication.getApplication().getDaoSession().getCrushInfoDao().insert(info);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Process.killProcess(Process.myPid());
            Intent intent = new Intent( MainApplication.getApplication(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            MainApplication.getApplication().startActivity(intent);



        }

    }


}
