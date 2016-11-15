package com.yiqirong.androidbaseframework.net;

import android.content.Context;

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
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kangwencai on 2016/11/9.
 */

public class RetrofitRest {
//    public static final String BASE_URL = "https://kms.dev.cn/";
        public static final String BASE_URL = "http://kms.dev.cn/";
//    public static final String BASE_URL = "https://kyfw.12306.cn/";
    public static APIManagerService apiManagerService;
    private static Retrofit retrofit;

    public RetrofitRest() {
        apiManagerService = getInstance().create(APIManagerService.class);
    }

    public static synchronized Retrofit getInstance() {
        if (retrofit == null) {
//            OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    /*添加证书锁定，前面是域名，后面是证书文件的指纹码（hash-MD5值）*/
//                builder.certificatePinner(new CertificatePinner.Builder()
//                        .add("YOU API.com", "sha1/DmxUShsZuNiqPQsX2Oi9uv2sCnw=")
//                        .add("YOU API..com", "sha1/SXxoaOSEzPC6BgGmxAt/EAcsajw=")
//                        .add("YOU API..com", "sha1/blhOM3W9V/bVQhsWAcLYwPU6n24=")
//                        .add("YOU API..com", "sha1/T5x9IXmcrQ7YuQxXnxoCmeeQ84c=")
//                        .build());
             /*证书认证*/
//            onHttpCertficates(builder);
//            OkHttpClient client = builder.build();

//            ClearableCookieJar cookieJar1 = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));


            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(initClient())
                    .build();
        }

        return retrofit;
    }

    private static OkHttpClient initClient() {

        InputStream[] streams = new InputStream[1];
        try {
            streams[0] = MainApplication.getApplication().getAssets().open("dev.cn.csr");
        } catch (IOException e) {
            e.printStackTrace();
        }


        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(streams, null, null);

        CookieJarImpl cookieJar1 = new CookieJarImpl(new MemoryCookieStore());
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
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


    /**
     * @param builder
     */
    private static void onHttpCertficates(OkHttpClient.Builder builder) {
        /*本地证书re*/
//        int[] certficates = new int[]{R.raw.dev};
//        builder.socketFactory(getSSLSocketFactory(MainApplication.getApplication(), certficates));
        /*直接用服务器证书中提供的密钥进行验证*/
        try {
            builder.socketFactory(getSSLSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 内置证书校验
     *
     * @param context
     * @param certificates
     * @return
     */
    protected static SSLSocketFactory getSSLSocketFactory(Context context, int[] certificates) {


        try {
            if (context == null) {
                throw new NullPointerException("context == null");
            }

            //导入证书得需要使用CertificateFactory，
            CertificateFactory certificateFactory;
            certificateFactory = CertificateFactory.getInstance("X.509");
             /*
      从my-root-cert.pem中提取X509证书信息，并保存在X509Certificate对象中
      注意，如果一个证书文件包含多个证书（证书链的情况），generateCertificate将只返回
      第一个证书
      调用generateCertificates函数可以返回一个证书数组，
      */
//            X509Certificate myX509Cer =myX509Cer
//                    (X509Certificate)certificateFactory
//                            .generateCertificate(inputStream);
            //Create a KeyStore containing our trusted CAs
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);

//            for (int i = 0; i < certificates.length; i++) {
//                //读取本地证书
//                InputStream is = context.getResources().openRawResource(certificates[i]);
//                keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(is));
//
//                if (is != null) {
//                    is.close();
//                }
//
//
//            }
            keyStore.setCertificateEntry(String.valueOf(0), certificateFactory.generateCertificate(context.getAssets().open("srca.cer")));

            //Create a TrustManager that trusts the CAs in our keyStore
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            //Create an SSLContext that uses our TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 服务器证书不内置，直接从里面拿公钥进行验证
     *
     * @return
     * @throws Exception
     */
    public static SSLSocketFactory getSSLSocketFactory() throws Exception {
        // Create a trust manager that does not validate certificate chains
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            //证书中的公钥
            public static final String PUB_KEY =
                    "-----BEGIN CERTIFICATE-----\n" +
                            "MIICmjCCAgOgAwIBAgIIbyZr5/jKH6QwDQYJKoZIhvcNAQEFBQAwRzELMAkGA1UEBhMCQ04xKTAn\n" +
                            "BgNVBAoTIFNpbm9yYWlsIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MQ0wCwYDVQQDEwRTUkNBMB4X\n" +
                            "DTA5MDUyNTA2NTYwMFoXDTI5MDUyMDA2NTYwMFowRzELMAkGA1UEBhMCQ04xKTAnBgNVBAoTIFNp\n" +
                            "bm9yYWlsIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MQ0wCwYDVQQDEwRTUkNBMIGfMA0GCSqGSIb3\n" +
                            "DQEBAQUAA4GNADCBiQKBgQDMpbNeb34p0GvLkZ6t72/OOba4mX2K/eZRWFfnuk8e5jKDH+9BgCb2\n" +
                            "9bSotqPqTbxXWPxIOz8EjyUO3bfR5pQ8ovNTOlks2rS5BdMhoi4sUjCKi5ELiqtyww/XgY5iFqv6\n" +
                            "D4Pw9QvOUcdRVSbPWo1DwMmH75It6pk/rARIFHEjWwIDAQABo4GOMIGLMB8GA1UdIwQYMBaAFHle\n" +
                            "tne34lKDQ+3HUYhMY4UsAENYMAwGA1UdEwQFMAMBAf8wLgYDVR0fBCcwJTAjoCGgH4YdaHR0cDov\n" +
                            "LzE5Mi4xNjguOS4xNDkvY3JsMS5jcmwwCwYDVR0PBAQDAgH+MB0GA1UdDgQWBBR5XrZ3t+JSg0Pt\n" +
                            "x1GITGOFLABDWDANBgkqhkiG9w0BAQUFAAOBgQDGrAm2U/of1LbOnG2bnnQtgcVaBXiVJF8LKPaV\n" +
                            "23XQ96HU8xfgSZMJS6U00WHAI7zp0q208RSUft9wDq9ee///VOhzR6Tebg9QfyPSohkBrhXQenvQ\n" +
                            "og555S+C3eJAAVeNCTeMS3N/M5hzBRJAoffn3qoYdAO1Q8bTguOi+2849A==\n" +
                            "-----END CERTIFICATE-----";

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {


            }

            //客户端并为对ssl证书的有效性进行校验
            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                if (chain == null) {
                    throw new IllegalArgumentException("checkServerTrusted:x509Certificate array isnull");
                }

                if (!(chain.length > 0)) {
                    throw new IllegalArgumentException("checkServerTrusted: X509Certificate is empty");
                }

                if (!(null != authType && authType.equalsIgnoreCase("RSA"))) {
                    throw new CertificateException("checkServerTrusted: AuthType is not RSA");
                }

                // Perform customary SSL/TLS checks
                try {
                    TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
                    tmf.init((KeyStore) null);
                    for (TrustManager trustManager : tmf.getTrustManagers()) {
                        ((X509TrustManager) trustManager).checkServerTrusted(chain, authType);
                    }
                } catch (Exception e) {
                    throw new CertificateException(e);
                }
                // Hack ahead: BigInteger and toString(). We know a DER encoded Public Key begins
                // with 0×30 (ASN.1 SEQUENCE and CONSTRUCTED), so there is no leading 0×00 to drop.
                RSAPublicKey pubkey = (RSAPublicKey) chain[0].getPublicKey();

                String encoded = new BigInteger(1 /* positive */, pubkey.getEncoded()).toString(16);
                // Pin it!
                final boolean expected = PUB_KEY.equalsIgnoreCase(encoded);

                if (!expected) {
                    throw new CertificateException("checkServerTrusted: Expected public key: "
                            + PUB_KEY + ", got public key:" + encoded);
                }

            }


            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};

        // Install the all-trusting trust manager
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        return sslContext.getSocketFactory();
    }
}
