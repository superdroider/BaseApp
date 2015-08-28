package com.superdroid.base.customuis;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by GT on 2015/8/25.
 */
public class BottomListenScrollView extends ScrollView {

    private int tempHeight;

    private OnScrollToBottomListener mOnScrollToBottomListener;

    public BottomListenScrollView(Context context) {
        super(context);
    }

    public BottomListenScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomListenScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (isScrollBottom(scrollY) && mOnScrollToBottomListener != null) {
            mOnScrollToBottomListener.onScrollBottomListener();
        }
    }

    private boolean isScrollBottom(int scrollY) {
        int currentHeight = scrollY + getHeight();
        boolean isScrollBottom = false;
        if (tempHeight < currentHeight && currentHeight == computeVerticalScrollRange()) {
            isScrollBottom = true;
        }
        tempHeight = currentHeight;
        return isScrollBottom;
    }

    /**
     * 设置底部监听器
     *
     * @param onScrollToBottomListener
     */
    public void setOnScrollToBottomListener(OnScrollToBottomListener onScrollToBottomListener) {
        this.mOnScrollToBottomListener = onScrollToBottomListener;
    }


    public interface OnScrollToBottomListener {
        /**
         * 滑动到底部监听
         */
        void onScrollBottomListener();
    }
}
