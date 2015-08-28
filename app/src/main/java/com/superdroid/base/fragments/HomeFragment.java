package com.superdroid.base.fragments;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.superdroid.base.R;
import com.superdroid.base.adapters.HomeListAdapter;
import com.superdroid.base.customuis.FragmentPageContainer;
import com.superdroid.base.dataprocessor.BannerDataProcessor;
import com.superdroid.base.dataprocessor.HomeDataProcressor;
import com.superdroid.base.entities.Banner;
import com.superdroid.base.entities.HomeData;
import com.superdroid.base.utils.ViewUtil;
import com.superdroid.base.viewholders.BannerViewHolder;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;


public class HomeFragment extends BaseFragment {
    private List<HomeData.Data> data;
    private List<Banner> banners;

    private HomeDataProcressor mHomeDataProcressor;
    private HomeListAdapter adapter;

    private PtrClassicFrameLayout mPtrFrame;
    private BannerViewHolder holder;

    @Override
    protected View createSuccessPage() {
        View view = ViewUtil.inflate(R.layout.fragment_home);
        FrameLayout content = (FrameLayout) view.findViewById(R.id.home_fl_content);
        View  base = ViewUtil.inflate(R.layout.base_list);
        content.addView(base);
        FrameLayout header = (FrameLayout) base.findViewById(R.id.base_header);
        ListView homeList = (ListView) base.findViewById(R.id.base_lv);
        mPtrFrame = (PtrClassicFrameLayout) base.findViewById(R.id.base_pfl);
        setupPtrClassicFrameLayout(mPtrFrame);
        adapter = new HomeListAdapter(data);
        homeList.setAdapter(new HomeListAdapter(data));
        holder = new BannerViewHolder();
        holder.setData(banners);
        holder.refreshView();
        header.addView(holder.getContentView());
        return view;
    }

    @Override
    protected FragmentPageContainer.LoadResult loadData() {
        initHomeDataProcrsssor();
        data = new HomeDataProcressor().loadData();
        banners = new BannerDataProcessor().loadData();
        return getLoadDataResult(data);
    }

    private void initHomeDataProcrsssor() {
        mHomeDataProcressor = new HomeDataProcressor();
    }

    private void refreshComplete() {
        if (mPtrFrame != null && mPtrFrame.isRefreshing()) {
            mPtrFrame.refreshComplete();
            if (adapter != null && data != null && data.size() > 0) {
                adapter.refreshData(data);
                adapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 设置下拉刷新布局参数
     */
    private void setupPtrClassicFrameLayout(PtrClassicFrameLayout mPtrFrame) {
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(500);
        mPtrFrame.setPullToRefresh(false);
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                executeRefreshTask();
            }
        });
    }

    /**
     * 执行刷新任务
     */
    private void executeRefreshTask() {
//        ThreadManager.getThreadManagerInstance().longTaskExecute(new RefreshDataTask<List<HomeData.Data>>() {
//            @Override
//            protected void refreshComplete(List<HomeData.Data> data) {
//                HomeFragment.this.data = data;
//                HomeFragment.this.refreshComplete();
//            }
//
//            @Override
//            protected BaseDataProcessor<List<HomeData.Data>> getBaseDataProcressor() {
//                return mHomeDataProcressor;
//            }
//        });
//        ThreadManager.getThreadManagerInstance().longTaskExecute(new LoadBannerDataTask() {
//            @Override
//            protected void setBannerData(List<Banner> data) {
//                banners = data;
//                holder.refreshView(banners);
//            }
//        });
    }
}
