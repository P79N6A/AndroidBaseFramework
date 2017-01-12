package com.yiqirong.androidbaseframework.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiqirong.androidbaseframework.R;
import com.yiqirong.androidbaseframework.activity.BaseActivity;
import com.yiqirong.androidbaseframework.widget.loadingview.CommitDataAnim;

import butterknife.ButterKnife;


/**
 * Created by wenjun on 2015/11/18.
 */
public abstract class BaseFragment extends Fragment {
    protected String TAG = getClass().getSimpleName();
    protected View view;
    protected Context mContext;
    protected BaseActivity mAct;
    protected CommitDataAnim mProgressDialog;
    private boolean mIsProgressDialogShow = false;


    protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BaseFragment.this.handleMessage(msg);
        }
    };

    protected void handleMessage(Message msg) {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAct = (BaseActivity) getActivity();
        mContext = getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(setContentViewId(), container, false);
            ButterKnife.bind(this, view);
            isPrepared = true;

            initViewData();
            initListener();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }
    /**
     * 绑定布局
     *
     * @return
     */
    protected abstract int setContentViewId();



    /**
     * 填充界面数据
     */
    protected abstract void initViewData();

    /**
     * 设置监听事件
     */
    protected abstract void initListener();


    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;
    /**
     * 控件是否初始化
     */
    protected boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    protected boolean isFrist = true;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {
    }

    /**
     * 懒加载方法
     */
    protected abstract void lazyLoad();


    /**
     * -------------------------------------------显示加载对话框相关方法----------------------------------------------
     */


    public void showProgressDialog(String msg) {
        showLoadingDialog(msg);
    }

    public void showProgressDialog(int resId) {
        String msg = getResources().getString(resId);
        showLoadingDialog(msg);
    }

    public void showProgressDialog() {
        String msg = getResources().getString(R.string.loading);
        showLoadingDialog(msg);
    }


    private void showLoadingDialog(String msg) {
        if (mIsProgressDialogShow) {
            return;
        }
        if (mProgressDialog == null) {
            mProgressDialog = new CommitDataAnim(getActivity(), msg);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                @Override
                public void onCancel(DialogInterface dialog) {
                    mIsProgressDialogShow = false;
                }
            });
        }
        mProgressDialog.show();
        mIsProgressDialogShow = true;
    }

    public void dismissProgressDialog() {
        if (mIsProgressDialogShow && mProgressDialog != null) {
            mProgressDialog.dismiss();
            mIsProgressDialogShow = false;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
