package com.superdroid.base.application;

import android.app.Application;
import android.os.Handler;

/**
 * Created by GT on 2015/8/20.
 */
public class App extends Application {


    private static Application mApplication;
    private static int mainThreadId = 0;
    private static Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        mainThreadId = android.os.Process.myPid();
        mHandler = new Handler();
    }

    public static Application getApplication() {
        return mApplication;
    }

    /**
     * 得到主线程ID
     *
     * @return 主线程ID
     */
    public static int getMainThreadId() {
        return mainThreadId;
    }

    /**
     * 获取主线程中的handler
     *
     * @return
     */
    public static Handler getHandler() {
        return mHandler;
    }
}
