package com.superdroid.mybaseapplication.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by GT on 2015/8/21.
 */
public class ViewUtil {
    /**
     * 移除当前view
     *
     * @param view 待移除的view
     */
    public static void removeView(View view) {
        ViewParent viewParent = view.getParent();
        if (viewParent instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) viewParent;
            viewGroup.removeView(view);
        }
    }
}
