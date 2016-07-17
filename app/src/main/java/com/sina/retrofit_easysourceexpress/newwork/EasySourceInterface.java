package com.sina.retrofit_easysourceexpress.newwork;

import com.sina.retrofit_easysourceexpress.newwork.parse.ExpressList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wu on 2016/7/17.
 */
public interface EasySourceInterface {

    @GET(Constant.EASY_EXPRESS_PATH)  //使用retrofit2.0 的get方法，返回request
    Call<ExpressList> getExpressInfo(@Query(Constant.APP_KEY) String appKey,@Query(Constant.APP_SECRET) String appSecret);

}
