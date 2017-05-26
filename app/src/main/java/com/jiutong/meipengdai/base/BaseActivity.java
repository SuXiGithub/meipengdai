package com.jiutong.meipengdai.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.jiutong.meipengdai.utils.StatusBarCompat;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //沉浸式状态栏，设置window模式
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResouceId());
        AppManager.getAppManager().addActivity(this);
        //激活沉浸式状态栏
        StatusBarCompat.compat(this);

        initDate(savedInstanceState);
        initView();
        initEvent();
    }

    public abstract int getLayoutResouceId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    protected void initView(){}

    protected void initDate(Bundle savedInstanceState){}

    protected void initEvent(){}

}
