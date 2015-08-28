package com.superdroid.base.tasks;

import com.superdroid.base.dataprocessor.BaseDataProcessor;
import com.superdroid.base.manager.ThreadManager;
import com.superdroid.base.utils.UIUtil;

/**
 * Created by GT on 2015/8/27.
 */
public abstract class RefreshAndLoadMoreDataTask<T> implements Runnable {

    private BaseDataProcessor<T> mBaseDataProcessor;

    public RefreshAndLoadMoreDataTask(BaseDataProcessor<T> mBaseDataProcessor) {
        this.mBaseDataProcessor = mBaseDataProcessor;
    }

    public void execute() {
        ThreadManager.getThreadManagerInstance().shortTaskExecute(this);
    }

    @Override
    public void run() {
        final T data = mBaseDataProcessor.loadDataUnuseCache();
        UIUtil.runOnMainThread(new Runnable() {
            @Override
            public void run() {
                loadDataOk(data);
            }
        });
    }

    protected abstract void loadDataOk(T data);
}
