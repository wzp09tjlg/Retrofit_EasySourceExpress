package com.sina.retrofit_easysourceexpress.newwork;

import com.sina.retrofit_easysourceexpress.newwork.parse.ExpressList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wu on 2016/7/17.
 */
public class EasySourceService {
    private static EasySourceInterface easySourceInterface;
    private static EasySourceService easySourceService;

    private static OkHttpClient okHttpClient = new OkHttpClient(); //retrofit 默认的处理网络机制
    private static Converter.Factory gsonFactory = GsonConverterFactory.create(); //数据转换的factory
    private static RxJavaCallAdapterFactory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    private static Retrofit retrofit;

    public EasySourceService(){
        init();
    }

    private void init(){
        retrofit = new Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl(Constant.EASY_SOURCE)
                .addConverterFactory(gsonFactory)
                //rxJava的adapterFactory
                //.addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }

    public static EasySourceService getInstance(){
        if(easySourceService == null)
            easySourceService = new EasySourceService();
        return easySourceService;
    }

    public Call<ExpressList> getExpressInfo(String appKey,String appSecret){
        return  retrofit.create(EasySourceInterface.class).getExpressInfo(appKey,appSecret);
    }
}
