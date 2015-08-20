package com.superdroid.mybaseapplication.utils;

import android.content.Context;
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
}
