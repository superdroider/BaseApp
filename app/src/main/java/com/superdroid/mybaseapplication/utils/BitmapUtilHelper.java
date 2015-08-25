package com.superdroid.mybaseapplication.utils;

import com.lidroid.xutils.BitmapUtils;
import com.superdroid.mybaseapplication.R;

/**
 * Created by Administrator on 2015/8/23.
 */
public class BitmapUtilHelper {
    private static BitmapUtils bitmapUtils;

    public static BitmapUtils getBitmapUtils() {
        if (bitmapUtils == null) {
            bitmapUtils = new BitmapUtils(UIUtil.getContext(), FileUtil.getImgCachePath(), 0.3f);
            bitmapUtils.configDefaultLoadFailedImage(R.drawable.ic_empty_page);
            bitmapUtils.configDefaultLoadingImage(R.drawable.ic_empty_page);
        }
        return bitmapUtils;
    }


}
