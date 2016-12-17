package com.yiqirong.androidbaseframework.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yiqirong.androidbaseframework.MainApplication;
import com.yiqirong.androidbaseframework.net.cookie.CookieJarImpl;
import com.yiqirong.androidbaseframework.net.cookie.store.MemoryCookieStore;
import com.yiqirong.androidbaseframework.net.rest.HttpsUtils;
import com.yiqirong.androidbaseframework.net.rest.ItemTypeAdapterFactory;
import com.yiqirong.androidbaseframework.net.service.APIManagerService;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kangwencai on 2016/11/9.
 */

public class RetrofitRest {
//    public static final String BASE_URL = "https://kms.dev.cn/";
            public static final String BASE_URL = "http://kms.dev.cn/";
//        public static final String BASE_URL = "http://18.18.19.91:8080/";
//    public static final String BASE_URL = "https://kyfw.12306.cn/";
    public static APIManagerService apiManagerService;
    private static Retrofit retrofit;

    public RetrofitRest() {
        apiManagerService = getInstance().create(APIManagerService.class);
    }

    public static synchronized Retrofit getInstance() {
        if (retrofit == null) {

            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .addConverterFactory(StringconverterF)
                    .client(initClient())
                    .build();
        }

        return retrofit;
    }

    /**
     * 初始化证书和client
     * @return
     */
    private static OkHttpClient initClient() {
        InputStream[] streams = new InputStream[1];
        try {
            //12306的证书
//            streams[0] = MainApplication.getInstance().getAssets().open("srca.cer");
            streams[0] = MainApplication.getApplication().getAssets().open("dev.cn.crt");

        } catch (IOException e) {
            e.printStackTrace();
        }


        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(streams, null, null);

        CookieJarImpl cookieJar1 = new CookieJarImpl(new MemoryCookieStore());
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20000L, TimeUnit.MILLISECONDS)
                .readTimeout(20000L, TimeUnit.MILLISECONDS)
                //cookie加不加没那么重要
                .cookieJar(cookieJar1)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
//                添加可信任网点的域名和公钥hash码
//                .certificatePinner(new CertificatePinner.Builder()
//                        .add("YOU API.com", "sha1/DmxUShsZuNiqPQsX2Oi9uv2sCnw=")
//                        .add("YOU API..com", "sha1/SXxoaOSEzPC6BgGmxAt/EAcsajw=")
//                        .add("YOU API..com", "sha1/blhOM3W9V/bVQhsWAcLYwPU6n24=")
//                        .add("YOU API..com", "sha1/T5x9IXmcrQ7YuQxXnxoCmeeQ84c=")
//                        .build())
                .build();


        return okHttpClient;
    }



}
