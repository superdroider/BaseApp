package com.superdroid.mybaseapplication.customuis;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.superdroid.mybaseapplication.entities.Banner;
import com.superdroid.mybaseapplication.utils.BitmapUtilHelper;

import java.util.List;

/**
 * 自动轮播，左右滑动 ViewPager
 *
 * @author likangxin
 * @version 2014-7-23 上午8:17:12
 * @ModifiedBy
 */
public class BannerView extends ViewPager {
    private static final int DURATION = 3000; // 多少毫秒 自动轮播一次
    private Handler handler;
    private Runnable moveRunnable;
    private BannerAdapter mBannerAdapter;
    private int count;//data的大小
    private boolean isStop = true; // 轮播是否停止
    private List<Banner> data;


    /**
     * 设置数据
     */
    public void setData(List<Banner> data) {
        this.data = data;
        if (data != null) {
            if (mBannerAdapter == null) {
                count = data.size();
                mBannerAdapter = new BannerAdapter();
                setAdapter(mBannerAdapter);
                setCurrentItem(Integer.MAX_VALUE / 2 + count / 2);
                if (count > 1)
                    startAutoMove();
            } else {
                refreshData(data);
            }

        }
    }

    /**
     * 刷新数据
     *
     * @param data
     */
    public void refreshData(List<Banner> data) {
        if (mBannerAdapter != null && data != null) {
            int forCount = count + 2 - getCurrentItem() % count;//循环次数
            count = data.size();
            for (int i = 0; i < forCount; i++) {
                setCurrentItem(getCurrentItem() + 1);
            }
        }
    }

    public void setStop(boolean isStop) {
        this.isStop = isStop;
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BannerView(Context context) {
        super(context);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (data != null && data.size() != 0) {
                    BannerView.this.setCurrentItem(BannerView.this
                            .getCurrentItem() + 1);
                }
            }
        };

        moveRunnable = new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);

                if (!isStop) {
                    handler.postDelayed(this, DURATION);
                }
            }
        };

        this.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (onItemChangeListener != null) {
                    onItemChangeListener.onItemChange(BannerView.this, position
                            % count);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                if (arg0 == SCROLL_STATE_IDLE && count > 1) {
                    startAutoMove();
                } else {
                    cancelAutoMove();
                }
            }
        });
    }

    /**
     * 启动 自动轮播
     *
     * @author likangxin
     */
    private void startAutoMove() {
        if (isStop) {
            isStop = false;
            handler.postDelayed(moveRunnable, DURATION);
        }
    }

    /**
     * 停止 自动轮播
     *
     * @author Mill
     */
    private void cancelAutoMove() {
        if (!isStop) {
            isStop = true;
            handler.removeCallbacks(moveRunnable);
        }
    }

    /**
     * 焦点图适配器
     */
    class BannerAdapter extends PagerAdapter {
        ImageView view;

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(final ViewGroup container,
                                      final int position) {
            final int realPosition = position % count;
            view = new ImageView(getContext());
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            BitmapUtilHelper.getBitmapUtils().display(view, data.get(realPosition).getImage());
            container.addView(view);

            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(container, view,
                                realPosition);
                    }
                }
            });
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (object != null)
                container.removeView((View) object);
        }

    }

    /**
     * item点击事件 监听
     */
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(ViewGroup container, ImageView itemView, int position);
    }

    /**
     * item 切换监听
     */
    private OnItemChangeListener onItemChangeListener;

    public void setOnItemChangeListener(
            OnItemChangeListener onItemChangeListener) {
        this.onItemChangeListener = onItemChangeListener;
    }

    public interface OnItemChangeListener {
        public void onItemChange(ViewGroup container, int position);
    }

    @Override
    protected void onDetachedFromWindow() {
        cancelAutoMove();
        super.onDetachedFromWindow();
    }

    @Override
    protected void onAttachedToWindow() {
        if (count > 1) {
            startAutoMove();
        }
        super.onAttachedToWindow();
    }
}
