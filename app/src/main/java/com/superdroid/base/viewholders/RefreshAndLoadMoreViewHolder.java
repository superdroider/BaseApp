package com.superdroid.base.viewholders;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.superdroid.base.R;
import com.superdroid.base.customuis.BottomListenScrollView;
import com.superdroid.base.utils.UIUtil;
import com.superdroid.base.utils.ViewUtil;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by GT on 2015/8/25.
 * 负责刷新数据时view的控制;备用实现复杂
 */
public class RefreshAndLoadMoreViewHolder<T> extends BaseHolder<T> implements BottomListenScrollView.OnScrollToBottomListener {

    private BottomListenScrollView mScrollView;
    private PtrClassicFrameLayout mPtrFrame;
    private FrameLayout fl_header;
    private FrameLayout fl_center;
    private FrameLayout fl_bottom;

    private LoadMoreHolder mLoadMoreHolder;

    private OnRefreshListener mOnRefreshListener;
    private OnLoadMoreListener mOnLoadMoreListener;


    private boolean isRefreshComplete;

    @Override
    public View initView() {
        View view = ViewUtil.inflate(R.layout.holder_ralm_view);
        mScrollView = (BottomListenScrollView) view.findViewById(R.id.ralm_sv);
        mPtrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.ralm_pfl);
        fl_header = (FrameLayout) view.findViewById(R.id.ralm_fl_header);
        fl_center = (FrameLayout) view.findViewById(R.id.ralm_fl_center);
        fl_bottom = (FrameLayout) view.findViewById(R.id.ralm_fl_bottom);
        mScrollView.setOnScrollToBottomListener(this);
        mLoadMoreHolder = new LoadMoreHolder();
        fl_bottom.addView(mLoadMoreHolder.getContentView());
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
                if (mOnRefreshListener != null)
                    mOnRefreshListener.refreshBegin();
            }
        });
        mPtrFrame.addPtrUIHandler(new PtrUIHandler() {
            @Override
            public void onUIReset(PtrFrameLayout frame) {
                if (isRefreshComplete && mOnRefreshListener != null)
                    mOnRefreshListener.refreshComplete();
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

    public LoadMoreHolder getLoadMoreHolder() {
        return mLoadMoreHolder;
    }

    public void onRefreshComplete() {
        if (mPtrFrame.isRefreshing()) {
            mPtrFrame.refreshComplete();
        }
    }

    public FrameLayout getFl_header() {
        return fl_header;
    }

    public FrameLayout getFl_center() {
        return fl_center;
    }

    public FrameLayout getFl_bottom() {
        return fl_bottom;
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
        if (mOnLoadMoreListener != null) {
            UIUtil.executeDelayedTask(new Runnable() {
                @Override
                public void run() {
                    mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                }
            }, 10);
            mOnLoadMoreListener.loadMoreBegin();
        }
    }

    public interface OnRefreshListener {
        void refreshBegin();

        void refreshComplete();
    }

    public interface OnLoadMoreListener<T> {
        void loadMoreBegin();

        void loadMoreComplete(T data);
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        mOnRefreshListener = onRefreshListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        mOnLoadMoreListener = onLoadMoreListener;
    }
}
