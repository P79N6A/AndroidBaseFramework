package com.yiqirong.androidbaseframework.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yiqirong.androidbaseframework.R;
import com.yiqirong.androidbaseframework.util_tools.DisplayUtil;
import com.yiqirong.androidbaseframework.util_tools.LogUtils;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by kangwencai on 2016/11/21.
 */

public class TestBFragment extends BaseFragment {

    @BindView(R.id.test_tv)
    TextView testTv;
    @BindView(R.id.view_pager_ptr_frame)
    PtrClassicFrameLayout viewPagerPtrFrame;

    @Override
    protected int setContentViewId() {
        return R.layout.fragment_mine;
    }


    @Override
    protected void initViewData() {
        final StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.setPadding(0, DisplayUtil.dp2px(15), 0, 0);
        viewPagerPtrFrame.setHeaderView(header);
        viewPagerPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                testTv.setText("刷新结果：阿斯顿发送地方");
//                结束刷新
                viewPagerPtrFrame.refreshComplete();
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void lazyLoad() {

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.e("Fragment      TestBFragment        onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.e("Fragment      TestBFragment        onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.e("Fragment      TestBFragment        onCreateView");

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.e("Fragment       TestBFragment       onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.e("Fragment       TestBFragment       onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e("Fragment       TestBFragment       onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.e("Fragment      TestBFragment        onPause");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e("Fragment      TestBFragment        onDestroy");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.e("Fragment     TestBFragment         onDetach");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        LogUtils.e("Fragment      TestBFragment        setUserVisibleHint:"+isVisibleToUser);
    }
}
