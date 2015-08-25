package com.superdroid.mybaseapplication.tasks;

import com.superdroid.mybaseapplication.dataprocessor.BannerDataProcessor;
import com.superdroid.mybaseapplication.entities.Banner;
import com.superdroid.mybaseapplication.utils.UIUtil;

import java.util.List;

/**
 * Created by GT on 2015/8/24.
 */
public abstract class LoadBannerDataTask implements Runnable {
    @Override
    public void run() {
        final List<Banner> data = new BannerDataProcessor().loadData();
        UIUtil.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                setBannerData(data);
            }
        });
    }

    protected abstract void setBannerData(List<Banner> data);
}
