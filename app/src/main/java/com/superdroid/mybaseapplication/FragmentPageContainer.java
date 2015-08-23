package com.superdroid.mybaseapplication;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;
import android.widget.FrameLayout;

import com.superdroid.mybaseapplication.manager.ThreadManager;
import com.superdroid.mybaseapplication.utils.Constants;
import com.superdroid.mybaseapplication.utils.UIUtil;
import com.superdroid.mybaseapplication.utils.ViewUtil;

/**
 * Created by GT on 2015/8/20.
 * 存放四种页面的容器
 */
public abstract class FragmentPageContainer extends FrameLayout {

    private int currentState = Constants.PAGE_UNKNOWN;

    private View loadingPage;
    private View errorPage;
    private View emptyPage;
    private View successPage;


    public FragmentPageContainer(Context context) {
        super(context);
        initPage();
    }

    /**
     * 初始化页面
     */
    private void initPage() {
        loadingPage = createLoadingPage();
        if (loadingPage != null) {
            this.addView(loadingPage, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }

        errorPage = createErrorPage();
        if (errorPage != null) {
            this.addView(errorPage, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }

        emptyPage = createEmptyPage();
        if (emptyPage != null) {
            this.addView(emptyPage, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }
        showPageByState();
    }

    /**
     * 根据页面状态显示相应的页面
     */
    private void showPageByState() {
        if (loadingPage != null) {
            loadingPage.setVisibility((currentState == Constants.PAGE_UNKNOWN || currentState == Constants.PAGE_LOADING) ? View.VISIBLE : View.GONE);
        }
        if (errorPage != null) {
            errorPage.setVisibility(currentState == Constants.PAGE_ERROR ? View.VISIBLE : View.GONE);
        }
        if (emptyPage != null) {
            emptyPage.setVisibility(currentState == Constants.PAGE_EMPTY ? View.VISIBLE : View.GONE);
        }
        if (currentState == Constants.PAGE_SUCCESS && successPage == null) {
            successPage = createSuccessPage();
            if (successPage != null)
                this.addView(successPage, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }
    }

    /**
     * 创建加载中页面
     *
     * @return
     */
    private View createLoadingPage() {
        return ViewUtil.inflate(R.layout.page_loading);
    }

    /**
     * 创建错误页面
     *
     * @return
     */
    private View createErrorPage() {
        return ViewUtil.inflate(R.layout.page_error);
    }

    /**
     * 创建空白页面
     *
     * @return
     */
    private View createEmptyPage() {
        return ViewUtil.inflate(R.layout.page_empty);
    }

    /**
     * 创建成功页面
     *
     * @return
     */
    protected abstract View createSuccessPage();

    /**
     * 外部调用，显示Fragment页面
     */
    public void show() {
        if (currentState == Constants.PAGE_EMPTY || currentState == Constants.PAGE_ERROR) {
            currentState = Constants.PAGE_UNKNOWN;
        }
        if (currentState == Constants.PAGE_UNKNOWN) {
            currentState = Constants.PAGE_LOADING;
        }
        showPageByState();
        ThreadManager.getThreadManagerInstance().longTaskExecute(new LoadDataTask());
    }

    /**
     * 加载数据任务
     */
    private class LoadDataTask implements Runnable {
        @Override
        public void run() {
            LoadResult result = loadData();
            currentState = result.getValue();
            SystemClock.sleep(500);
            UIUtil.runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    showPageByState();
                }
            });
        }
    }

    /**
     * 加载数据
     *
     * @return 加载数据的结果
     */
    protected abstract LoadResult loadData();

    /**
     * 数据加载结果
     */
    public enum LoadResult {
        error(Constants.PAGE_ERROR), empty(Constants.PAGE_EMPTY), success(Constants.PAGE_SUCCESS);
        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
