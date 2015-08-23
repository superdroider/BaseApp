package com.superdroid.mybaseapplication.utils;

import com.lidroid.xutils.BitmapUtils;

/**
 * Created by Administrator on 2015/8/23.
 */
public class BitmapUtilHelper {
    private static BitmapUtils bitmapUtils;

    public static BitmapUtils getBitmapUtils() {
        if (bitmapUtils == null) {
            bitmapUtils = new BitmapUtils(UIUtil.getContext(), FileUtil.getImgCachePath(), 0.3f);
        }
        return bitmapUtils;
    }


}
