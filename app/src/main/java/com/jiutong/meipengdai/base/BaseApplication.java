package com.jiutong.meipengdai.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by suxi on 2017/5/19.
 */

public class BaseApplication extends Application{

    private final String TAG = BaseApplication.class.getSimpleName();
    private static BaseApplication instance;
    /** 主线程ID */
    private static int mMainThreadId = -1;
    /** 主线程ID */
    private static Thread mMainThread;
    /** 主线程Handler */
    private static Handler mMainThreadHandler;
    /** 主线程Looper */
    private static Looper mMainLooper;

    public Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = this;
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
        init();
    }

    /**
     * @Description: 获取实例
     * @return
     */
    public static BaseApplication getInstance() {
        return instance;
    }

    /**
     * 获取主线程ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程
     */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取主线程的looper
     */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    /**
     * 内存不足时回调
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    private void init() {

        //捕获全局异常
        CrashHandler.getInstance().init(mContext);

        Logger.init(TAG).setLogLevel(LogLevel.FULL);
    }

}
