package com.superdroid.mybaseapplication.utils;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.superdroid.mybaseapplication.application.App;

/**
 * Created by GT on 2015/8/20.
 * �������UI�Ĺ�����
 */
public class UIUtil {

    /**
     * ������ԴID���view
     *
     * @param layoutId
     */
    public static View inflate(int layoutId) {
        return View.inflate(getContext(), layoutId, null);
    }

    /**
     * ��ȡ������
     *
     * @return
     */
    public static Context getContext() {
        return App.getApplication();
    }

    /**
     * �����߳���ִ��runnable
     *
     * @param runnable ��ִ�е�runnable
     */
    public static void runOnMainThread(Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            getMainThreadHandler().post(runnable);
        }
    }

    /**
     * ��ȡ���߳��е�handler
     *
     * @return
     */
    private static Handler getMainThreadHandler() {
        return App.getHandler();
    }

    /**
     * �жϵ�ǰ�߳��Ƿ������߳�
     *
     * @return
     */
    private static boolean isMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    /**
     * ��ȡ���߳�ID
     *
     * @return
     */
    private static int getMainThreadId() {
        return App.getMainThreadId();
    }
}
