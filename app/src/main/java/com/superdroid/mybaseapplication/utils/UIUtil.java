package com.superdroid.mybaseapplication.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.widget.Toast;

import com.superdroid.mybaseapplication.application.App;

/**
 * Created by GT on 2015/8/20.
 * 负责界面UI的工具类
 */
public class UIUtil {


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

    /**
     * 显示Toast
     *
     * @param strId 显示内容的id
     */
    public static void showToast(int strId) {
        Toast.makeText(getContext(), strId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示Toast
     *
     * @param str 待显示内容
     */
    public static void showToast(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 获得资源
     *
     * @return
     */
    public static Resources getResource() {
        return getContext().getResources();
    }

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    /**
     * 延时执行异步任务
     *
     * @param runnable
     * @param delayMillis
     */
    public static void executeDelayedTask(Runnable runnable, long delayMillis) {
        getMainThreadHandler().postDelayed(runnable, delayMillis);
    }

    /**
     * 移除异步任务
     *
     * @param runnable
     */
    public static void cancleTask(Runnable runnable) {
        getMainThreadHandler().removeCallbacks(runnable);
    }
}
