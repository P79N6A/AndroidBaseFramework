package com.yiqirong.androidbaseframework.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiqirong.androidbaseframework.R;
import com.yiqirong.androidbaseframework.adapter.MainFragmentAdapter;
import com.yiqirong.androidbaseframework.database.CrushInfo;
import com.yiqirong.androidbaseframework.fragment.IndicateFragment;
import com.yiqirong.androidbaseframework.fragment.MainFragment;
import com.yiqirong.androidbaseframework.fragment.RecyclerViewFragment;
import com.yiqirong.androidbaseframework.fragment.RefreshFragment;
import com.yiqirong.androidbaseframework.util_tools.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kangwencai on 2016/11/10.
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.iv_tab_menu_0)
    ImageView ivTabMenu0;
    @BindView(R.id.tv_tab_menu_0)
    TextView tvTabMenu0;
    @BindView(R.id.iv_tab_menu_1)
    ImageView ivTabMenu1;
    @BindView(R.id.tv_tab_menu_1)
    TextView tvTabMenu1;
    @BindView(R.id.iv_tab_menu_2)
    ImageView ivTabMenu2;
    @BindView(R.id.tv_tab_menu_2)
    TextView tvTabMenu2;
    @BindView(R.id.iv_tab_menu_3)
    ImageView ivTabMenu3;
    @BindView(R.id.tv_tab_menu_3)
    TextView tvTabMenu3;
    @BindView(R.id.iv_tab_menu_4)
    ImageView ivTabMenu4;
    @BindView(R.id.tv_tab_menu_4)
    TextView tvTabMenu4;


    private int mCurrentTab = 0;
    private List<View> mTabViews = new ArrayList<>();
    private List<View> mTabTexts = new ArrayList<>();

    private List<Fragment> mList = new ArrayList<>();
    private MainFragmentAdapter mAdapter;


    @Override
    public void initViewFromXML() {
        setContent(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @Override
    public void initData() {

        mList.add(new MainFragment());
        mList.add(new RefreshFragment());
        mList.add(new IndicateFragment());
        mList.add(new RefreshFragment());
        mList.add(new RecyclerViewFragment());

        mAdapter = new MainFragmentAdapter(getSupportFragmentManager());
        mAdapter.setDataList(mList);
        vpMain.setAdapter(mAdapter);
        vpMain.addOnPageChangeListener(mPageChangeListener);

        //TODO:挖一个让程序崩溃的坑，然后重新启动，这样可以看看异常捕捉和数据库存储是否生效，到底有多少坑呢？大家一起来找茬吧
        List<CrushInfo> infoList = application.getDaoSession().getCrushInfoDao().loadAll();
        LogUtils.e(infoList.get(0).toString());

    }

    @Override
    public void fillView() {
        hideTitleBar();

        mTabViews.add(ivTabMenu0);
        mTabViews.add(ivTabMenu1);
        mTabViews.add(ivTabMenu2);
        mTabViews.add(ivTabMenu3);
        mTabViews.add(ivTabMenu4);
        mTabTexts.add(tvTabMenu0);
        mTabTexts.add(tvTabMenu1);
        mTabTexts.add(tvTabMenu2);
        mTabTexts.add(tvTabMenu3);
        mTabTexts.add(tvTabMenu4);
        ivTabMenu0.setSelected(true);
        tvTabMenu0.setSelected(true);
    }

    @Override
    public void initListener() {


        vpMain.addOnPageChangeListener(mPageChangeListener);
//        textTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                RetrofitRest.apiManagerService.getWeather(MyRequestBodyCreator.create("{phone:138090909090}"))
//                        .enqueue(new RestCallBack<ApiResponse>() {
//
//                            @Override
//                            public void onSuccess(String result) {
//                                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onFailure(String msg) {
//                                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//
//                            }
//
//                            @Override
//                            public void requestAgain() {
//
//                            }
//                        });
//            }
//        });
    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            changeTabState(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /*修改点击是下面四个tab状态*/
    private void changeTabState(int position) {
        if (mCurrentTab != position) {
            for (int i = 0; i < mTabViews.size(); i++) {
                if (position == i) {
                    mTabViews.get(i).setSelected(true);
                    mTabTexts.get(i).setSelected(true);
                } else {
                    mTabViews.get(i).setSelected(false);
                    mTabTexts.get(i).setSelected(false);
                }
            }
            mCurrentTab = position;
        }
    }

    @OnClick({R.id.rl_tab_menu_0, R.id.rl_tab_menu_1, R.id.rl_tab_menu_2, R.id.rl_tab_menu_3, R.id.rl_tab_menu_4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_tab_menu_0:
                vpMain.setCurrentItem(0);
                break;
            case R.id.rl_tab_menu_1:
                vpMain.setCurrentItem(1);
                break;
            case R.id.rl_tab_menu_2:
                vpMain.setCurrentItem(2);
                break;
            case R.id.rl_tab_menu_3:
                vpMain.setCurrentItem(3);
                break;
            case R.id.rl_tab_menu_4:
                vpMain.setCurrentItem(4);
                break;
        }
    }


}
