package com.superdroid.mybaseapplication.viewholders;

import android.view.View;
import android.widget.ScrollView;

import com.superdroid.mybaseapplication.R;
import com.superdroid.mybaseapplication.utils.ViewUtil;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by GT on 2015/8/25.
 */
public abstract class PflViewHolder extends BaseHolder<Object> {

    private ScrollView mScrollView;
    private PtrClassicFrameLayout mPtrFrame;

    private boolean isRefreshComplete;

    @Override
    public View initView() {
        View view = ViewUtil.inflate(R.layout.pfl_view);
        mScrollView = (ScrollView) view.findViewById(R.id.sv);
        mPtrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.pfl);
        setupPtrClassicFrameLayout();
        return view;
    }

    @Override
    public void refreshView() {

    }

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
                PflViewHolder.this.onRefreshBegin(frame);
            }
        });
        mPtrFrame.addPtrUIHandler(new PtrUIHandler() {
            @Override
            public void onUIReset(PtrFrameLayout frame) {
                if (isRefreshComplete)
                    onRefreshComplete(frame);
            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout frame) {
            }

            @Override
            public void onUIRefreshBegin(PtrFrameLayout frame) {
                isRefreshComplete = false;
            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {
                isRefreshComplete = true;
            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
            }
        });
    }

    public abstract void onRefreshBegin(PtrFrameLayout frame);

    public abstract void onRefreshComplete(PtrFrameLayout frame);


    public void onRefreshComplete() {
        if (mPtrFrame.isRefreshing()) {
            mPtrFrame.refreshComplete();
        }
    }

    /**
     * 返回scrollView
     *
     * @return
     */
    public ScrollView getScrollView() {
        return this.mScrollView;
    }
}
