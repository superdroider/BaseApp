package com.superdroid.base.fragments;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.superdroid.base.R;
import com.superdroid.base.adapters.ServiceAdapter;
import com.superdroid.base.customuis.FragmentPageContainer;
import com.superdroid.base.customuis.ListViewForScrollView;
import com.superdroid.base.dataprocessor.ServiceDataProcressor;
import com.superdroid.base.entities.ServiceData;
import com.superdroid.base.events.RefreshAndLoadMoreEvent;
import com.superdroid.base.tasks.RefreshAndLoadMoreTask;
import com.superdroid.base.utils.Constants;
import com.superdroid.base.utils.UIUtil;
import com.superdroid.base.utils.ViewUtil;
import com.superdroid.base.viewholders.LoadMoreHolder;
import com.superdroid.base.viewholders.PflViewHolder;

import java.util.List;

import de.greenrobot.event.EventBus;


public class ServiceFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private View view;
    private PflViewHolder mPflViewHolder;
    private LoadMoreHolder mLoadMoreHolder;
    private List<ServiceData> data;
    private ServiceDataProcressor mServiceDataProcressor;
    private ServiceAdapter adapter;
    private boolean isLoading;
    private boolean isRefreshing;
    private int page;
    private boolean hasMoreData = true;
    private boolean canScrollToBottom = false;

    @Override
    protected View createSuccessPage() {
        EventBus.getDefault().register(this);
        view = ViewUtil.inflate(R.layout.fragment_service);
        mLoadMoreHolder = new LoadMoreHolder();
        mPflViewHolder = new PflViewHolder();
        ((ViewGroup) view).addView(mPflViewHolder.getContentView());
        LinearLayout contentView = (LinearLayout) ViewUtil.inflate(R.layout.content_service);
        FrameLayout contenFrame = (FrameLayout) contentView.findViewById(R.id.service_bottom);
        ListViewForScrollView mListViewForScrollView = (ListViewForScrollView) contentView.findViewById(R.id.service_lv);
        adapter = new ServiceAdapter(data);
        mListViewForScrollView.setAdapter(adapter);
        mListViewForScrollView.setOnItemClickListener(this);
        contenFrame.addView(mLoadMoreHolder.getContentView());
        mPflViewHolder.getScrollView().addView(contentView);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isLoading = false;
        hasMoreData = true;
        isRefreshing = false;
        canScrollToBottom = false;
        page = 0;
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected FragmentPageContainer.LoadResult loadData() {
        initServiceDataProcressor();
        mServiceDataProcressor.setRequestParameters("map=api_shop&flag=1&mc_id=24&page=0");
        data = mServiceDataProcressor.loadData();
        return getLoadDataResult(data);
    }

    public void onEventMainThread(RefreshAndLoadMoreEvent<List<ServiceData>> mRefreshAndLoadMoreEvent) {
        int type = mRefreshAndLoadMoreEvent.getType();
        int status = mRefreshAndLoadMoreEvent.getStatus();
        if (type == Constants.PALM_REFRESH) {
            if (status == Constants.PALM_REFRESH_BEGIN) {
                if (!isRefreshing) {
                    refreshBegin();
                }
            } else if (status == Constants.PALM_REFRESH_COMPLETE) {
                refreshComplete();
            } else if (status == Constants.PALM_LOAD_OK) {
                mPflViewHolder.onRefreshComplete();
                if (mRefreshAndLoadMoreEvent.getData() != null && mRefreshAndLoadMoreEvent.getData().size() > 0) {
                    ServiceFragment.this.data.clear();
                    this.data = mRefreshAndLoadMoreEvent.getData();
                }
            }
        } else if (type == Constants.PALM_LOADMORE) {
            if (status == Constants.PALM_LOADMORE_BEGIN) {
                if (!isLoading && hasMoreData) {
                    loadMoreDataBegin();
                }
            } else if (status == Constants.PALM_LOAD_OK) {
                loadMoreDataComplete(mRefreshAndLoadMoreEvent.getData());
            }
        }
    }

    private void refreshBegin() {
        isRefreshing = true;
        page = 0;
        mServiceDataProcressor.setRequestParameters("map=api_shop&flag=1&mc_id=24&page=0");
        new RefreshAndLoadMoreTask(mServiceDataProcressor, Constants.PALM_REFRESH).execute();
    }

    private void initServiceDataProcressor() {
        this.mServiceDataProcressor = new ServiceDataProcressor();
    }

    /**
     * 刷新完成时调用
     */
    private void refreshComplete() {
        refreshAdapterData();
        hasMoreData = true;
        isRefreshing = false;
    }

    /**
     * 开始加载更多数据
     */
    private void loadMoreDataBegin() {
        scrollDown();
        mServiceDataProcressor.setRequestParameters("map=api_shop&flag=1&mc_id=24&page=" + page);
        mLoadMoreHolder.getContentView().setVisibility(View.VISIBLE);
        new RefreshAndLoadMoreTask(mServiceDataProcressor, Constants.PALM_LOADMORE).execute();
        isLoading = true;
        canScrollToBottom = true;
    }

    /**
     * scrollView 滑动到底部
     */
    private void scrollDown() {
        if (canScrollToBottom)
            UIUtil.executeDelayedTask(new Runnable() {
                @Override
                public void run() {
                    mPflViewHolder.getScrollView().fullScroll(ScrollView.FOCUS_DOWN);
                }
            }, 10);
    }

    /**
     * 更多数据加载完成
     */
    private void loadMoreDataComplete(List<ServiceData> data) {
        int dataStatus = -1;
        if (data == null) {
            dataStatus = Constants.ERROR_STATUS;
        } else if (data.size() < 10) {
            hasMoreData = false;
            dataStatus = Constants.NO_DATA_STATUS;
            this.data.addAll(data);
            refreshAdapterData();
        } else {
            hasMoreData = true;
            page++;
            dataStatus = Constants.HAVE_MOREDATA_STATUS;
            this.data.addAll(data);
            refreshAdapterData();
        }
        mLoadMoreHolder.setData(dataStatus);
        mLoadMoreHolder.refreshView();
        isLoading = false;
    }

    /**
     * 刷新适配器中的数据
     */
    private void refreshAdapterData() {
        if (adapter != null) {
            adapter.refreshData(data);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UIUtil.showToast("position:" + position);
    }
}
