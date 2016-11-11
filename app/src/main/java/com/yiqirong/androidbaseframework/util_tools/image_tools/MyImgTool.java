package com.yiqirong.androidbaseframework.util_tools.image_tools;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 获取缩略图或图片
 */
public class MyImgTool {
    /**
     * 获取图片缩略图
     *
     * @param context
     * @param ImagePath 图片路径
     * @return
     */
    public static Bitmap getImageThumbnail(Context context, String ImagePath) {
        ContentResolver testcr = context.getContentResolver();
        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID,};
        String whereClause = MediaStore.Images.Media.DATA + " = '" + ImagePath + "'";
        Cursor cursor = testcr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, whereClause,
                null, null);
        int _id = 0;
        String imagePath = "";
        //虽然没数据这地方还是要关闭....
        if (cursor == null || cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        if (cursor.moveToFirst()) {
            int _idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID);
            int _dataColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

            do {
                _id = cursor.getInt(_idColumn);
                imagePath = cursor.getString(_dataColumn);
                LogUtils.e(_id + " path:" + imagePath + "---");
            } while (cursor.moveToNext());
        }
        cursor.close();
        LogUtils.e("数据库是否关闭" + cursor.isClosed());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(testcr, _id, MediaStore.Images.Thumbnails.MINI_KIND,
                options);

        return bitmap;
    }

//    public void createThumbnail02(Bitmap mBitmap){
//        String url = MediaStore.Images.Media.insertImage(MyApplication.getInstance().getContentResolver(), mBitmap, "", "");
//
//    }
//
//    final  int MAX_SIZES=20;
///** 创建缩略图,返回缩略图文件路径 */
//         public String createThumbnail(Bitmap source, String fileName){
//                  int oldW = source.getWidth();
//                  int oldH = source.getHeight();
//
//                  int w = Math.round((float)oldW/MAX_SIZES);  //MAX_SIZE为缩略图最大尺寸
//                  int h = Math.round((float)oldH/MAX_SIZES);
//
//                 int newW = 0;
//                 int newH = 0;
//
//                 if(w <= 1 && h <= 1){
//                     return saveBitmap(source, fileName);
//                 }
//
//                 int i = w > h ? w : h;  //获取缩放比例
//
//                 newW = oldW/i;
//                 newH = oldH/i;
//
//                 Bitmap imgThumb = ThumbnailUtils.extractThumbnail(source, newW, newH);  //关键代码！！
//
//                 return saveBitmap(imgThumb, fileName);  //注：saveBitmap方法为保存图片并返回路径的private方法
//             }


    /**
     * 获取视频缩略图
     *
     * @param videoPath
     * @param width
     * @param height
     * @param kind
     * @return
     */
    private Bitmap getVideoThumbnail(String videoPath, int width, int height, int kind) {
        Bitmap bitmap = null;
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }

    /**
     * 从指定路径读取图片并进行缩放
     *
     * @param srcPath
     * @return
     */
    public static Bitmap compressImageFromFile(String srcPath) {
//        if(bitmap!=null){
//            bitmap.recycle();
//        }
        Bitmap bitmap;
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;//只读边,不读内容
//        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = 800f;//
        float ww = 480f;//
        int be = 1;
        if (w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置采样率

        newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;//该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收

//        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        bitmap = decodeFile(srcPath, newOpts);

//      return compressBmpFromBmp(bitmap);//原来的方法调用了这个方法企图进行二次压缩
        //其实是无效的,大家尽管尝试
        return bitmap;
    }

    /***
     * 根据配置解析图片
     *
     * @param srcPath
     * @param newOpts
     * @return
     */
    public static Bitmap decodeFile(String srcPath, BitmapFactory.Options newOpts) {
        Bitmap bitmap = null;
        try {
            InputStream inputStream = new FileInputStream(srcPath);
            bitmap = BitmapFactory.decodeStream(inputStream, null,
                    newOpts);
            inputStream.close();
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LogUtils.e("该路径下的图片未找到");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
