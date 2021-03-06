package com.superdroid.base.utils;

import com.lidroid.xutils.BitmapUtils;
import com.superdroid.base.R;

/**
 * Created by Administrator on 2015/8/23.
 */
public class BitmapUtilHelper {
    private static BitmapUtils bitmapUtils;

    public static BitmapUtils getBitmapUtils() {
        if (bitmapUtils == null) {
            bitmapUtils = new BitmapUtils(UIUtil.getContext(), FileUtil.getImgCachePath(), 0.3f);
            bitmapUtils.configDefaultLoadFailedImage(R.mipmap.ic_empty_page);
            bitmapUtils.configDefaultLoadingImage(R.mipmap.ic_empty_page);
        }
        return bitmapUtils;
    }
}
