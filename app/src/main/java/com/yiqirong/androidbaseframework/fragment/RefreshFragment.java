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
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by kangwencai on 2016/11/21.
 */

public class RefreshFragment extends BaseFragment {

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

}
