package com.yiqirong.androidbaseframework.widget.loadingview;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.yiqirong.androidbaseframework.R;

/**
 * Created by kangwencai on 2016/11/10.
 * <p>
 * 刷新页面失败的缺省页，当发生无数据,网络不好,加载错误显示这个页面
 */

public class RefreshPageFailedView extends FrameLayout implements View.OnClickListener {
    private Context mContext = null;
    private LinearLayout loading_ll;
    private ImageView imgLoad;
    private TextView txtLoad;
    private RefreshPageProgressView loadingView;

    public RefreshPageFailedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public RefreshPageFailedView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_loaded, this);
        loading_ll = (LinearLayout) findViewById(R.id.loading_ll);
        imgLoad = (ImageView) findViewById(R.id.iv_loaded);
        txtLoad = (TextView) findViewById(R.id.tv_loaded);
        loadingView = (RefreshPageProgressView) findViewById(R.id.customLoadingView);
        loading_ll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mOnReloadClickListener != null) {
            switch (v.getId()) {
                case R.id.loading_ll:
                    startLoading();
                    mOnReloadClickListener.reloading();
                    break;
            }
        }
    }

    /**
     * 开始加载
     */
    public void startLoading() {
        setVisibility(View.VISIBLE);
        imgLoad.setVisibility(View.GONE);
        txtLoad.setVisibility(View.GONE);
        loadingView.show();
        loading_ll.setEnabled(false);
    }


    /**
     * 加载成功
     */
    public void loadingSuccess() {
        loading_ll.setEnabled(false);
        loadingView.dismiss();
        setVisibility(View.GONE);
    }


    /**
     * 加载错误
     */
    public void setLoadingError() {
        imgLoad.setVisibility(View.VISIBLE);
        txtLoad.setVisibility(View.VISIBLE);
//        imgLoad.setImageResource(R.drawable.big_cat_cry_avatar);
        txtLoad.setText(R.string.load_error_text);
        loadingView.dismiss();
        loading_ll.setEnabled(true);
    }

    /**
     * 加载失败
     *
     * @param msg 显示文本
     */
    public void setLoadingError(String msg) {
        imgLoad.setVisibility(View.VISIBLE);
        txtLoad.setVisibility(View.VISIBLE);
//        imgLoad.setImageResource(R.drawable.big_cat_cry_avatar);
        txtLoad.setText(msg);
        loadingView.dismiss();
        loading_ll.setEnabled(true);
    }

    /**
     * 加载失败
     */
    public void setLoadingError(int resid) {
        imgLoad.setVisibility(View.VISIBLE);
        txtLoad.setVisibility(View.VISIBLE);
//        imgLoad.setImageResource(R.drawable.big_cat_cry_avatar);
        txtLoad.setText(resid);
        loadingView.dismiss();
        loading_ll.setEnabled(true);
    }

    /**
     * 设置无数据界面
     *
     * @param msg
     * @param picResId
     */
    public void setLoadingEmpty(String msg, int picResId) {
        setVisibility(View.VISIBLE);
        imgLoad.setVisibility(View.VISIBLE);
        txtLoad.setVisibility(View.VISIBLE);
        imgLoad.setImageResource(picResId);
        txtLoad.setText(msg);
        loadingView.dismiss();
        loading_ll.setEnabled(false);
    }

    public void setLoadingEmpty(String msg) {
        setVisibility(View.VISIBLE);
        imgLoad.setVisibility(View.VISIBLE);
        txtLoad.setVisibility(View.VISIBLE);
//        imgLoad.setImageResource(R.drawable.big_cat_cry_avatar);
        txtLoad.setText(msg);
        loadingView.dismiss();
        loading_ll.setEnabled(false);
    }


    /**
     * 无网络
     */
    public void setLoadingNoNetwork(String msg) {
        imgLoad.setVisibility(View.VISIBLE);
        txtLoad.setVisibility(View.VISIBLE);
//        imgLoad.setImageResource(R.drawable.big_cat_cry_avatar);
        txtLoad.setText(msg);
        loadingView.dismiss();
        loading_ll.setEnabled(true);
    }

    /**
     * 无网络
     */
    public void setLoadingNoNetwork(int resid) {
        imgLoad.setVisibility(View.VISIBLE);
        txtLoad.setVisibility(View.VISIBLE);
//        imgLoad.setImageResource(R.drawable.big_cat_cry_avatar);
        txtLoad.setText(resid);
        loadingView.dismiss();
        loading_ll.setEnabled(true);
    }

    /**
     * 加载失败
     */
    public void setLoadingNoNetwork() {
        imgLoad.setVisibility(View.VISIBLE);
        txtLoad.setVisibility(View.VISIBLE);
//        imgLoad.setImageResource(R.drawable.big_cat_cry_avatar);
        txtLoad.setText(R.string.load_no_wifi_text);
        loadingView.dismiss();
        loading_ll.setEnabled(true);
    }


    private OnReloadClickListener mOnReloadClickListener = null;

    public void setOnReloadClickListener(OnReloadClickListener listener) {
        mOnReloadClickListener = listener;
    }

    /**
     * 点击接口
     */
    public interface OnReloadClickListener {
        void reloading();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}

