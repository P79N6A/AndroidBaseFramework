package com.yiqirong.androidbaseframework.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yiqirong.androidbaseframework.R;
import com.yiqirong.androidbaseframework.net.RetrofitRest;
import com.yiqirong.androidbaseframework.net.rest.ApiResponse;
import com.yiqirong.androidbaseframework.net.rest.RestCallBack;
import com.yiqirong.androidbaseframework.net.service.MyRequestBodyCreator;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

                RetrofitRest.apiManagerService.getWeather(MyRequestBodyCreator.create("{phone:138090909090}")).enqueue(new RestCallBack<ApiResponse>() {

                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(String msg) {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

}
