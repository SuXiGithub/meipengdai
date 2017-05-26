package com.jiutong.meipengdai.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 初始化
 * Created by suxi on 17/5/9.
 */
public class HttpRequest {

    public static final String BASE_URL = "https://api.douban.com/v2/movie/";

    private static final int DEFAULT_TIMEOUT = 8;

    public Retrofit retrofit;

    //构造方法私有
    private HttpRequest() {
        initRetrofit();
    }

    private void initRetrofit() {
        //添加统一的log管理，打release包的时候记得将Level设置成Level.NONE
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);

        //手动创建一个OkHttpClient并设置超时时间
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpRequest INSTANCE = new HttpRequest();
    }

    //获取单例
    public static HttpRequest getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
