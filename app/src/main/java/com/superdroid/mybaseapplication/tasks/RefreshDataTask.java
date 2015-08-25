package com.superdroid.mybaseapplication.tasks;

import com.superdroid.mybaseapplication.dataprocessor.BaseDataProcessor;
import com.superdroid.mybaseapplication.utils.UIUtil;

/**
 * Created by GT on 2015/8/25.
 * 下拉刷新任务类
 */
public abstract class RefreshDataTask<T> implements Runnable {
    @Override
    public void run() {
        final T data = getBaseDataProcressor().loadDataUnuseCache();
        UIUtil.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                refreshComplete(data);
            }
        });
    }

    protected abstract void refreshComplete(T data);

    protected abstract BaseDataProcessor<T> getBaseDataProcressor();
}
