package com.superdroid.mybaseapplication.utils;

import android.content.Context;
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
}
