package com.jiutong.meipengdai.utils;

import android.content.Context;

import com.jiutong.meipengdai.base.BaseApplication;

/**
 * Created by suxi on 2017/5/22.
 */

public class SpConfig extends PreferenceUtil {

    private static final String YOUR_APP_NAME = "your_app_name";

    public SpConfig() {
        super(YOUR_APP_NAME);
    }

    @Override
    protected Context getContext() {
        return BaseApplication.getInstance();
    }

    private static class SingletonHolder {
        private static final SpConfig INSTANCE = new SpConfig();
    }

    public static SpConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
