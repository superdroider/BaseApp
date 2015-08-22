package com.superdroid.mybaseapplication.utils;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.superdroid.mybaseapplication.application.App;

/**
 * Created by GT on 2015/8/20.
 * 负责界面UI的工具类
 */
public class UIUtil {

    /**
     * 根据资源ID填充view
     *
     * @param layoutId
     */
    public static View inflate(int layoutId) {
        LogUtil.i("getContext()="+getContext()+"--layoutID="+layoutId);
        return View.inflate(getContext(), layoutId, null);
    }

    /**
     * 获取上下文
     *
     * @return
     */
    public static Context getContext() {
        return App.getApplication();
    }

    /**
     * 在主线程中执行runnable
     *
     * @param runnable 待执行的runnable
     */
    public static void runOnMainThread(Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            getMainThreadHandler().post(runnable);
        }
    }

    /**
     * 获取主线程中的handler
     *
     * @return
     */
    private static Handler getMainThreadHandler() {
        return App.getHandler();
    }

    /**
     * 判断当前线程是否是主线程
     *
     * @return
     */
    private static boolean isMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    /**
     * 获取主线程ID
     *
     * @return
     */
    private static int getMainThreadId() {
        return App.getMainThreadId();
    }
}
