package com.yiqirong.androidbaseframework.util_tools.image_tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;


import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import com.yiqirong.androidbaseframework.IConstants;
import com.yiqirong.androidbaseframework.MainApplication;
import com.yiqirong.androidbaseframework.R;
import com.yiqirong.androidbaseframework.net.RetrofitRest;

import java.io.File;

import retrofit2.Retrofit;

public class ImageLoaderUtils {
    private static final String TAG = "ImageLoaderUtils";

    private static ImageLoaderUtils mImageLoaderUtil;
    private int defaultImageResId = R.drawable.actionsheet_bottom_normal;
    private int errorImageResId = R.drawable.actionsheet_bottom_normal;
    private int emptyImageResId = R.drawable.actionsheet_bottom_normal;
    private int defaultAvatarId = R.drawable.actionsheet_bottom_normal;
    private static ImageLoader mImageLoader = ImageLoader.getInstance();
    private DisplayImageOptions mOptions;
    private DisplayImageOptions mAvatarOptions;

    public ImageLoaderUtils(Context context) {
        mImageLoader = ImageLoader.getInstance();
        File cacheDir = new File(IConstants.ImageCachePath);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs()
                .build();
        mImageLoader.init(config);
        mOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(defaultImageResId)
                .showImageForEmptyUri(emptyImageResId)
                .showImageOnFail(errorImageResId)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        mAvatarOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(defaultAvatarId)
                .showImageForEmptyUri(defaultAvatarId)
                .showImageOnFail(defaultAvatarId)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }


    public static ImageLoaderUtils getInstance() {
        Context context = MainApplication.getApplication();
        if (mImageLoaderUtil == null) {
            synchronized (ImageLoaderUtils.class) {
                if (mImageLoaderUtil == null) {
                    mImageLoaderUtil = new ImageLoaderUtils(context);
                }
            }
        }
        return mImageLoaderUtil;
    }

    /**
     * 获取图片
     *
     * @param imageView
     * @param url
     */
    public void getImage(final ImageView imageView, final String url) {
        mImageLoader.displayImage(formatImageUrl(url), imageView, mOptions);
//        mImageLoader.displayImage(formatImageUrl(INet.IMAGE_ROOT_url +url), imageView, mOptions);


    }

    public void getAvatar(final ImageView imageView, final String url) {
        mImageLoader.displayImage(formatImageUrl(url), imageView, mAvatarOptions);
    }

    public Bitmap getBitmapFromUri(String uri) {
        return mImageLoader.loadImageSync(uri, mOptions);
    }


    /**
     * 获取图片
     *
     * @param imageView
     * @param url
     */
    public void getImage(final ImageView imageView, final String url, final int defImageResId) {
        final DisplayImageOptions optionsT = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .considerExifParams(true)
                .showImageOnFail(defImageResId)
                .showImageOnLoading(defImageResId)
                .showImageForEmptyUri(defImageResId)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
        mImageLoader.displayImage(formatImageUrl(url), imageView, optionsT);
    }

    public void getImageWithListener(ImageView imageView, String url, SimpleImageLoadingListener listener) {
        mImageLoader.displayImage(formatImageUrl(url), imageView, mOptions, listener);
    }


    /**
     * 获取图片
     *
     * @param imageView
     * @param url
     */
    public void getRoundedImage(final ImageView imageView, final String url, final int defImageResId, int cornerRadiusPixels) {

        final DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .considerExifParams(true)
                .showImageOnFail(defImageResId)
                .showImageOnLoading(defImageResId)
                .showImageForEmptyUri(defImageResId)
                .displayer(new RoundedBitmapDisplayer(cornerRadiusPixels))
                .build();

        mImageLoader.displayImage(formatImageUrl(url), imageView, options);
    }


    /**
     * 获取图片
     *
     * @param imageView
     * @param url
     * @param defImageResId
     */
    public void getRoundedCornerBitmap(final ImageView imageView, final String url, final int defImageResId) {
        final DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(defImageResId)
                .showImageForEmptyUri(defImageResId)
                .showImageOnFail(defImageResId)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new BitmapDisplayer() {

                    @Override
                    public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom arg2) {
                        if (!(imageAware instanceof ImageViewAware)) {
                            throw new IllegalArgumentException("ImageAware should wrap ImageView. ImageViewAware is expected.");
                        }
                        imageAware.setImageBitmap(BitmapUtils.toRoundBitmap(bitmap));
                    }
                })
                .build();
        mImageLoader.displayImage(formatImageUrl(url), imageView, options);
    }

    private String formatImageUrl(String url) {
        String imgUrl;
        //不是网络图片和本地图片的时候就拼接成网络图片
        if (!TextUtils.isEmpty(url) && !url.startsWith("http://") && !url.startsWith("file://")) {
            imgUrl = RetrofitRest.BASE_URL + url;
        } else {
            imgUrl = url;
        }
        return imgUrl;
    }

    public void clearImgCache() {
        mImageLoader.clearDiskCache();
        mImageLoader.clearDiskCache();
    }
}
