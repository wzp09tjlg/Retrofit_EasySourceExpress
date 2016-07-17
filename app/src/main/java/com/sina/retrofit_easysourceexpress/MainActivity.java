package com.sina.retrofit_easysourceexpress;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sina.retrofit_easysourceexpress.view.activity.RetrofitActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button mBtnRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
    }

    private void initViews(){
        mBtnRetrofit = (Button)findViewById(R.id.btn_retrofit);
    }

    private void initData(){
        mBtnRetrofit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_retrofit:
                Intent intentRetrofit = new Intent(MainActivity.this,RetrofitActivity.class);
                startActivity(intentRetrofit);
                break;
        }
    }
}
