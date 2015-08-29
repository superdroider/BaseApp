package com.superdroid.base.tasks;

import android.os.SystemClock;

import com.superdroid.base.dataprocessor.BaseDataProcessor;
import com.superdroid.base.manager.ThreadManager;
import com.superdroid.base.utils.UIUtil;

/**
 * Author: superdroid
 * Date: 2015/8/29 12:29
 * Desc: 加载数据任务类，默认使用缓存数据
 */

public abstract class LoadDataTask<T> implements Runnable {
    private BaseDataProcessor<T> mBaseDataProcessor;
    private boolean isUseCache = true;

    public LoadDataTask(BaseDataProcessor<T> mBaseDataProcessor, boolean isUseCache) {
        if (mBaseDataProcessor == null) {
            return;
        } else {
            this.mBaseDataProcessor = mBaseDataProcessor;
            this.isUseCache = isUseCache;
        }
    }

    /**
     * 执行当前应用
     */
    public void execute() {
        ThreadManager.getThreadManagerInstance().shortTaskExecute(this);
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        T data = mBaseDataProcessor.loadDataUnuseCache();
        long end = System.currentTimeMillis();
        long still = end - start;
        if (still < 1000) {//保证线程至少执行一秒
            SystemClock.sleep(1000 - still);
        }
        UIUtil.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                LoadDataOk();
            }
        });
    }

    /**
     * 数据加载完成回调
     */
    protected abstract void LoadDataOk();
}
