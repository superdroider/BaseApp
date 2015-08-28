package com.superdroid.base.customuis;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.superdroid.base.R;

public class BottomItemView extends LinearLayout {
    private static final int DEFAULT_COLOR = Color.parseColor("#ff999999");
    private static final int DEFAULT_IMAGE_RESOURCE = R.drawable.ic_empty_page;
    /**
     * 是否被选中
     */
    private boolean isSelected = false;

    /**
     * 被选中时字体颜色
     */
    private int selectedColor;
    /**
     * 未被选中时字体颜色
     */
    private int unSelectedColor;
    /**
     * TextView文字
     */
    private String mText;

    /**
     * 被选中时的图片资源ID
     */
    private int selectedImageResource;
    /**
     * 未被选中时的图片资源ID
     */
    private int unSelectedImageResource;

    private ImageView mImageView;
    private TextView mTextView;


    public BottomItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray mTypedArray = getContext().obtainStyledAttributes(attrs,
                R.styleable.BottomItemView);
        selectedColor = mTypedArray.getColor(
                R.styleable.BottomItemView_mSelectedColor, DEFAULT_COLOR);
        unSelectedColor = mTypedArray.getColor(
                R.styleable.BottomItemView_mUnselectedColor, DEFAULT_COLOR);

        selectedImageResource = mTypedArray
                .getResourceId(R.styleable.BottomItemView_mSelectedImg,
                        DEFAULT_IMAGE_RESOURCE);
        unSelectedImageResource = mTypedArray.getResourceId(
                R.styleable.BottomItemView_mUnselectedImg,
                DEFAULT_IMAGE_RESOURCE);
        mText = mTypedArray.getString(R.styleable.BottomItemView_mText);
        mTypedArray.recycle();
        initView();
        Log.i("tag", "defColor=" + DEFAULT_COLOR);

    }

    private void initView() {
        setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams mImageViewParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams mTextViewParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        mImageViewParams.gravity = Gravity.CENTER_HORIZONTAL;
        mImageView = new ImageView(getContext());
        mImageView.setImageResource(isSelected ? selectedImageResource
                : unSelectedImageResource);
        mImageViewParams.width = dip2px(25);
        mImageViewParams.height = dip2px(25);

        mTextViewParams.gravity = Gravity.CENTER_HORIZONTAL;
        mTextView = new TextView(getContext());
        mTextView.setTextColor(isSelected ? selectedColor : unSelectedColor);
        mTextView.setText(mText);

        this.addView(mImageView, mImageViewParams);
        this.addView(mTextView, mTextViewParams);
    }

    /**
     * 改变view的选中状态
     *
     * @param isSelected
     */
    private void changeSelectStatus(boolean isSelected) {
        if (isSelected) {
            mImageView.setImageResource(selectedImageResource);
            mTextView.setTextColor(selectedColor);
        } else {
            mImageView.setImageResource(unSelectedImageResource);
            mTextView.setTextColor(unSelectedColor);
        }
    }

    public ImageView getmImageView() {
        return mImageView;
    }

    public TextView getmTextView() {
        return mTextView;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        changeSelectStatus(isSelected);
        this.isSelected = isSelected;
    }

    /**
     * 设置选中时的字体颜色
     *
     * @param selectedColor
     */
    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }

    /**
     * 设置未选中时的字体颜色
     *
     * @param unSelectedColor
     */
    public void setUnSelectedColor(int unSelectedColor) {
        this.unSelectedColor = unSelectedColor;
    }

    /**
     * 设置TextView的文字内容
     *
     * @param mText
     */
    public void setmText(String mText) {
        this.mText = mText;
    }

    /**
     * 设置选中时图片资源id
     *
     * @param selectedImageResource
     */
    public void setSelectedImageResource(int selectedImageResource) {
        this.selectedImageResource = selectedImageResource;
    }

    /**
     * 设置未选中时图片资源id
     *
     * @param unSelectedImageResource
     */
    public void setUnSelectedImageResource(int unSelectedImageResource) {
        this.unSelectedImageResource = unSelectedImageResource;
    }

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dip(float pxValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
