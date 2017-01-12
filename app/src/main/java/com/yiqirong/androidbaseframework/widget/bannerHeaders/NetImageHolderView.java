package com.yiqirong.androidbaseframework.widget.bannerHeaders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.yiqirong.androidbaseframework.util_tools.image_tools.ImageLoaderUtils;

/**
 * Created by Sai on 15/8/4.
 * banner  加载网络图片Holder例子
 */
public class NetImageHolderView implements Holder<String> {
    private ImageView imageView;
    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
//        imageView.setImageResource(data);

        ImageLoaderUtils.getInstance().getImage(imageView,data);
    }
}
