package com.yiqirong.androidbaseframework.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Jax
 * @version V1.0.0
 * @Created on 2015/12/2 17:48.
 * 首页四个Tab情况下使用的FragmentPagerAdapter禁用了destroyItem方法
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;

    public void setDataList(List<Fragment> list) {
        this.mList = list;
    }

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mList != null && mList.size() > position ? mList.get(position) : null;
    }

    @Override
    public int getCount() {


        return mList != null && mList.size() > 0 ? mList.size() : 0;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //禁用了destroyItem方法,所以只适合Fragment页面固定的情况下使用
    }
}
