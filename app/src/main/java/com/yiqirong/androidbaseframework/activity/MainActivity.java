package com.yiqirong.androidbaseframework.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yiqirong.androidbaseframework.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kangwencai on 2016/11/10.
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.text_tv)
    TextView textTv;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;

    @Override
    public void initViewFromXML() {
        setContent(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @Override
    public void initData() {

    }

    @Override
    public void fillView() {

    }

    @Override
    public void initListener() {


        findViewById(R.id.text_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
