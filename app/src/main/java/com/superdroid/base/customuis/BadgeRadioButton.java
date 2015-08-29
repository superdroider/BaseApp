package com.superdroid.base.customuis;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.superdroid.base.customuis.badge.BGABadgeViewHelper;
import com.superdroid.base.customuis.badge.BGABadgeable;
import com.superdroid.base.customuis.badge.BGADragDismissDelegate;

/**
 * Author: superdroid
 * Date: 2015/8/29 09:29
 * Desc: 主界面底部Radiobutton
 */

public class BadgeRadioButton extends AppCompatRadioButton implements BGABadgeable {

    private BGABadgeViewHelper mBadgeViewHelper;

    public BadgeRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBadgeViewHelper = new BGABadgeViewHelper(this, context, attrs, BGABadgeViewHelper.BadgeGravity.RightTop);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mBadgeViewHelper.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mBadgeViewHelper.drawBadge(canvas);
    }

    @Override
    public void showCirclePointBadge() {
        mBadgeViewHelper.showCirclePointBadge();
    }

    @Override
    public void showTextBadge(String badgeText) {
        mBadgeViewHelper.showTextBadge(badgeText);
    }

    @Override
    public void hiddenBadge() {
        mBadgeViewHelper.hiddenBadge();
    }

    @Override
    public void showDrawableBadge(Bitmap bitmap) {
        mBadgeViewHelper.showDrawable(bitmap);
    }

    @Override
    public boolean callSuperOnTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void setDragDismissDelegage(BGADragDismissDelegate delegate) {
        mBadgeViewHelper.setDragDismissDelegage(delegate);
    }
}
