package com.superdroid.mybaseapplication.fragments;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.superdroid.mybaseapplication.R;
import com.superdroid.mybaseapplication.adapters.HomeListAdapter;
import com.superdroid.mybaseapplication.customuis.FragmentPageContainer;
import com.superdroid.mybaseapplication.dataprocessor.BannerDataProcessor;
import com.superdroid.mybaseapplication.dataprocessor.BaseDataProcessor;
import com.superdroid.mybaseapplication.dataprocessor.HomeDataProcressor;
import com.superdroid.mybaseapplication.entities.Banner;
import com.superdroid.mybaseapplication.entities.HomeData;
import com.superdroid.mybaseapplication.manager.ThreadManager;
import com.superdroid.mybaseapplication.tasks.LoadBannerDataTask;
import com.superdroid.mybaseapplication.tasks.RefreshDataTask;
import com.superdroid.mybaseapplication.utils.LogUtil;
import com.superdroid.mybaseapplication.utils.ViewUtil;
import com.superdroid.mybaseapplication.viewholders.BannerViewHolder;

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
        View view = ViewUtil.inflate(R.layout.base_list);
        FrameLayout header = (FrameLayout) view.findViewById(R.id.base_header);
        ListView homeList = (ListView) view.findViewById(R.id.base_lv);
        mPtrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.base_pfl);
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
        mPtrFrame.setDurationToCloseHeader(1000);
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

    private void executeRefreshTask() {
        ThreadManager.getThreadManagerInstance().longTaskExecute(new RefreshDataTask<List<HomeData.Data>>() {
            @Override
            protected void refreshComplete(List<HomeData.Data> data) {
                LogUtil.i("data 数据更新完成");
                HomeFragment.this.data = data;
                HomeFragment.this.refreshComplete();
            }

            @Override
            protected BaseDataProcessor<List<HomeData.Data>> getBaseDataProcressor() {
                return mHomeDataProcressor;
            }
        });
        ThreadManager.getThreadManagerInstance().longTaskExecute(new LoadBannerDataTask() {
            @Override
            protected void setBannerData(List<Banner> data) {
                banners = data;
                holder.refreshView(banners);
            }
        });
    }

    private FragmentPageContainer.LoadResult getLoadDataResult(List<HomeData.Data> data) {
        if (data == null) {
            return FragmentPageContainer.LoadResult.error;
        } else if (data.size() == 0) {
            return FragmentPageContainer.LoadResult.empty;
        } else {
            return FragmentPageContainer.LoadResult.success;
        }
    }
}
