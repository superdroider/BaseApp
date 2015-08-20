package com.superdroid.mybaseapplication.application;

import android.app.Application;

/**
 * Created by GT on 2015/8/20.
 */
public class App extends Application {


    private static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static Application getApplication() {
        return mApplication;
    }

}
