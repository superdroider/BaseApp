package com.superdroid.mybaseapplication.fragments;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.superdroid.mybaseapplication.R;
import com.superdroid.mybaseapplication.adapters.DefaultAdapter;
import com.superdroid.mybaseapplication.customuis.FragmentPageContainer;
import com.superdroid.mybaseapplication.dataprocessor.BaseDataProcessor;
import com.superdroid.mybaseapplication.utils.ViewUtil;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by GT on 2015/8/24.
 */
public abstract class BaseListFragment<T> extends BaseFragment {
    protected PtrClassicFrameLayout mPtrFrame;
    protected ListView base_lv;
    protected FrameLayout contentView;
    protected FrameLayout headerView;
    protected FrameLayout bottomView;
    protected View successView;

    protected List<T> data;
    protected BaseDataProcessor<List<T>> mBaseDataProcessor;

    protected DefaultAdapter<T> adapter;

    @Override
    protected View createSuccessPage() {
        successView = ViewUtil.inflate(R.layout.base_list);
        initView();
        adapter = getDefaultAdapter();
        base_lv.setAdapter(adapter);
        setupPtrClassicFrameLayout();
        addHeaderView();
        addBottomeView();
        return successView;
    }

    protected abstract void addBottomeView();

    protected abstract void addHeaderView();

    protected abstract DefaultAdapter<T> getDefaultAdapter();

    /**
     * 设置下拉刷新布局参数
     */
    private void setupPtrClassicFrameLayout() {
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        mPtrFrame.setPullToRefresh(false);
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mBaseDataProcessor.refreshData();
            }
        });
    }

    /**
     * 初始化view
     */
    private void initView() {
        base_lv = (ListView) successView.findViewById(R.id.base_lv);
        mPtrFrame = (PtrClassicFrameLayout) successView.findViewById(R.id.base_pfl);
        headerView = (FrameLayout) successView.findViewById(R.id.base_fl_header);
        contentView = (FrameLayout) successView.findViewById(R.id.base_fl_content);
        bottomView = (FrameLayout) successView.findViewById(R.id.base_fl_bottom);
    }

    @Override
    protected FragmentPageContainer.LoadResult loadData() {
        mBaseDataProcessor = getBaseDataProcessor();
        data = mBaseDataProcessor.loadData();
        return getLoadDataResult();
    }

    private FragmentPageContainer.LoadResult getLoadDataResult() {

        if (data == null) {
            return FragmentPageContainer.LoadResult.error;
        } else if (data.size() == 0) {
            return FragmentPageContainer.LoadResult.empty;
        } else {
            return FragmentPageContainer.LoadResult.success;
        }
    }

    public FrameLayout getContentView() {
        return contentView;
    }

    public FrameLayout getHeaderView() {
        return headerView;
    }

    public FrameLayout getBottomView() {
        return bottomView;
    }

    protected abstract BaseDataProcessor<List<T>> getBaseDataProcessor();
}
