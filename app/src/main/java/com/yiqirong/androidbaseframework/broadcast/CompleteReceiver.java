package com.yiqirong.androidbaseframework.broadcast;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Jax on 2016/5/5.
 * Description :调用系统的下载服务来更新apk，当下载完成之后该广播接收器会收到通知进行安装
 * Version : V1.0.0
 */
//启动方式
//private void download(String linkUrl) {
//        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//        Uri uri = Uri.parse(linkUrl);
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//        //设置允许使用的网络类型，这里是移动网络和wifi都可以
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
//        //禁止发出通知，既后台下载，如果要使用这一句必须声明一个权限：android.permission.DOWNLOAD_WITHOUT_NOTIFICATION
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
//        downloadManager.enqueue(request);
//        }
public class CompleteReceiver extends BroadcastReceiver {

    private DownloadManager downloadManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (action.equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {

            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);

            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(id);
            downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Cursor cursor = downloadManager.query(query);

            int columnCount = cursor.getColumnCount();
            String path = null;
            //TODO 这里把所有的列都打印一下，有什么需求，就怎么处理,文件的本地路径就是path
            while (cursor.moveToNext()) {
                for (int j = 0; j < columnCount; j++) {
                    String columnName = cursor.getColumnName(j);
                    String string = cursor.getString(j);
                    if (columnName.equals("local_filename") && string != null && string.endsWith("apk")) {
                        Intent installIntent = new Intent();
                        installIntent.setAction(Intent.ACTION_VIEW);
                        installIntent.setDataAndType(Uri.fromFile(new File(string)), "application/vnd.android.package-archive");
                        installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(installIntent);
                        path = null;
                        break;
                    }
                    if (columnName.equals("local_uri")) {
                        path = string;
                    }
                }
            }
            cursor.close();
            //如果sdcard不可用时下载下来的文件，那么这里将是一个内容提供者的路径，这里打印出来，有什么需求就怎么样处理
            if (path != null && path.startsWith("content:")) {
                cursor = context.getContentResolver().query(Uri.parse(path), null, null, null, null);
                columnCount = cursor.getColumnCount();
                while (cursor.moveToNext()) {
                    for (int j = 0; j < columnCount; j++) {
                        String columnName = cursor.getColumnName(j);
                        String string = cursor.getString(j);

                        if (columnName.equals("_data") && string != null && string.endsWith("apk")) {
                            Intent installIntent = new Intent();
                            installIntent.setAction(Intent.ACTION_VIEW);
                            installIntent.setDataAndType(Uri.fromFile(new File(string)), "application/vnd.android.package-archive");
                            context.startActivity(intent);
                            path = null;
                            break;
                        }
                    }
                }
                cursor.close();
            }
        } else if (action.equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {
            Toast.makeText(context, "点击通知了....", Toast.LENGTH_LONG).show();
        }
    }
}
