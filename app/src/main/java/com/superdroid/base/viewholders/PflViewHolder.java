package com.superdroid.base.viewholders;

import android.view.View;

import com.superdroid.base.R;
import com.superdroid.base.customuis.BottomListenScrollView;
import com.superdroid.base.events.RefreshAndLoadMoreEvent;
import com.superdroid.base.utils.Constants;
import com.superdroid.base.utils.ViewUtil;

import de.greenrobot.event.EventBus;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by GT on 2015/8/25.
 * 负责刷新数据时view的控制
 */
public class PflViewHolder extends BaseHolder<Object> implements BottomListenScrollView.OnScrollToBottomListener {

    private BottomListenScrollView mScrollView;

    private PtrClassicFrameLayout mPtrFrame;


    private boolean isRefreshComplete;

    @Override
    public View initView() {
        View view = ViewUtil.inflate(R.layout.pfl_view);
        mScrollView = (BottomListenScrollView) view.findViewById(R.id.sv);
        mPtrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.pfl);
        mScrollView.setOnScrollToBottomListener(this);
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
                EventBus.getDefault().post(new RefreshAndLoadMoreEvent(Constants.PALM_REFRESH, Constants.PALM_REFRESH_BEGIN));
            }
        });
        mPtrFrame.addPtrUIHandler(new PtrUIHandler() {
            @Override
            public void onUIReset(PtrFrameLayout frame) {
                if (isRefreshComplete)
                    EventBus.getDefault().post(new RefreshAndLoadMoreEvent(Constants.PALM_REFRESH, Constants.PALM_REFRESH_COMPLETE));
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
    public BottomListenScrollView getScrollView() {
        return this.mScrollView;
    }

    @Override
    public void onScrollBottomListener() {

        EventBus.getDefault().post(new RefreshAndLoadMoreEvent(Constants.PALM_LOADMORE, Constants.PALM_LOADMORE_BEGIN));
    }
}
