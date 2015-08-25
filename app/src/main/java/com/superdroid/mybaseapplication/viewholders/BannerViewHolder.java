package com.superdroid.mybaseapplication.viewholders;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.superdroid.mybaseapplication.R;
import com.superdroid.mybaseapplication.customuis.BannerView;
import com.superdroid.mybaseapplication.entities.Banner;
import com.superdroid.mybaseapplication.utils.UIUtil;
import com.superdroid.mybaseapplication.utils.ViewUtil;

import java.util.List;

/**
 * Created by GT on 2015/8/24.
 */
public class BannerViewHolder extends BaseHolder<List<Banner>> implements BannerView.OnItemClickListener, BannerView.OnItemChangeListener {

    private BannerView mBannerView;

    private LinearLayout pointsLayout;

    private TextView text_message;


    @Override
    public View initView() {
        View view = ViewUtil.inflate(R.layout.banner);
        mBannerView = (BannerView) view.findViewById(R.id.cust_banner);
        pointsLayout = (LinearLayout) view.findViewById(R.id.layout_point);
        text_message = (TextView) view.findViewById(R.id.text_message);
        mBannerView.setOnItemClickListener(this);
        mBannerView.setOnItemChangeListener(this);
        return view;
    }

    @Override
    public void setData(List<Banner> data) {
        super.setData(data);
    }

    @Override
    public void refreshView() {
        mBannerView.setData(data);
        initPointsLayout(UIUtil.getContext(), data.size());
    }

    /**
     * 初始化圈圈
     */
    private void initPointsLayout(Context context, int size) {
        pointsLayout.removeAllViews();
        for (int i = 0; i < size; i++) {
            ImageView image = new ImageView(context);
            float denstity = UIUtil.getResource().getDisplayMetrics().density;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    (int) (6 * denstity), (int) (6 * denstity));
            params.leftMargin = (int) (2 * denstity);
            params.rightMargin = (int) (2 * denstity);
            image.setLayoutParams(params);
            if (i == 0) {
                image.setBackgroundResource(R.drawable.dot_focused);
            } else {
                image.setBackgroundResource(R.drawable.dot_normal);
            }
            pointsLayout.addView(image);
        }
    }

    /**
     * 刷新 圈圈 背景
     */
    private void refreshPoint(int position) {
        if (pointsLayout != null) {
            for (int i = 0; i < pointsLayout.getChildCount(); i++) {
                if (i == position) {
                    pointsLayout.getChildAt(i).setBackgroundResource(
                            R.drawable.dot_focused);
                } else {
                    pointsLayout.getChildAt(i).setBackgroundResource(
                            R.drawable.dot_normal);
                }
            }
        }
    }

    @Override
    public void onItemChange(ViewGroup container, int position) {
        text_message.setText(data.get(position).getTitle());
        refreshPoint(position);
    }

    @Override
    public void onItemClick(ViewGroup container, ImageView itemView, int position) {
        UIUtil.showToast("ItemClicked:" + position);
    }
}
