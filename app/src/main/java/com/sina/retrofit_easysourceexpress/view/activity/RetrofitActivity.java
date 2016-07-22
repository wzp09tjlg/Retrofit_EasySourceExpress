package com.sina.retrofit_easysourceexpress.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sina.retrofit_easysourceexpress.R;
import com.sina.retrofit_easysourceexpress.newwork.Constant;
import com.sina.retrofit_easysourceexpress.newwork.EasySourceService;
import com.sina.retrofit_easysourceexpress.newwork.parse.ExpressList;
import com.sina.retrofit_easysourceexpress.utils.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wu on 2016/7/17.
 */
public class RetrofitActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = RetrofitActivity.class.getSimpleName();

    private Button mBtnSyc;
    private Button mBtnAsc;

    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        initViews();
        initData();
    }

    private void initViews(){
        mBtnAsc = (Button)findViewById(R.id.btn_asc);
        mBtnSyc = (Button)findViewById(R.id.btn_syc);
    }

    private void initData(){
        mContext = this;

        mBtnAsc.setOnClickListener(this);
        mBtnSyc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_asc:
                doAscRequest();
                break;
            case R.id.btn_syc:
                doSycRequest();
                break;
        }
    }

    //异步访问
    private void doAscRequest(){
        EasySourceService sourceService = EasySourceService.getInstance();
        Call<ExpressList> call = sourceService.getExpressInfo(Constant.EASY_SOURCE_APPID
                ,Constant.EASY_SOURCE_SECRET);  //这里参数如果没有传递正确
        // 会报 ~ Channel is unrecoverably broken and will be disposed
        // 然后会得到response.body 为空指针
        call.enqueue(new Callback<ExpressList>() {
            @Override
            public void onResponse(Call<ExpressList> call, Response<ExpressList> response) {
                StringBuilder builder = new StringBuilder();
                builder.append("onResponse:\n");
                for(ExpressList.ExpressBeanList.ExpressBean bean : response.body().showapi_res_body.expressList){
                    LogUtil.e("bean:" + bean);
                    builder.append("bean:" + bean);
                }
                Toast.makeText(mContext,builder.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ExpressList> call, Throwable t) {
               LogUtil.e("onFailure:" + t.getMessage().toString());
                Toast.makeText(mContext, "onFailure:\n" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //同步访问
    private void doSycRequest(){
        EasySourceService sourceService = EasySourceService.getInstance();
        final Call<ExpressList> call = sourceService.getExpressInfo(Constant.EASY_SOURCE_APPID
                ,Constant.EASY_SOURCE_SECRET);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<ExpressList> response = call.execute();
                    if(response.isSuccessful()){
                        LogUtil.e("response is success!");
                        for(ExpressList.ExpressBeanList.ExpressBean bean : response.body().showapi_res_body.expressList){
                            LogUtil.e("bean:" + bean);
                        }
                    }else{
                        LogUtil.e("response error!");
                    }
                }catch (Exception e){}

            }
        }).start();
    }
}
