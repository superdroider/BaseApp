package com.superdroid.base.tasks;

import android.os.SystemClock;

import com.superdroid.base.dataprocessor.BaseDataProcessor;
import com.superdroid.base.events.RefreshAndLoadMoreEvent;
import com.superdroid.base.manager.ThreadManager;
import com.superdroid.base.utils.Constants;

import de.greenrobot.event.EventBus;

/**
 * Created by GT on 2015/8/27.
 */
public class RefreshAndLoadMoreTask<T> implements Runnable {

    private BaseDataProcessor<T> mBaseDataProcessor;
    private int type;

    public RefreshAndLoadMoreTask(BaseDataProcessor<T> mBaseDataProcessor, int type) {
        if (mBaseDataProcessor == null) {
            return;
        } else {
            this.type = type;
            this.mBaseDataProcessor = mBaseDataProcessor;
        }
    }

    public void execute() {
        ThreadManager.getThreadManagerInstance().shortTaskExecute(this);
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        T data = mBaseDataProcessor.loadDataUnuseCache();
        long end = System.currentTimeMillis();
        long still = end - start;
        if (still < 1000) {
            SystemClock.sleep(1000 - still);
        }
        EventBus.getDefault().post(new RefreshAndLoadMoreEvent(type, Constants.PALM_LOAD_OK, data));
    }
}
