package com.superdroid.base.fragments;

import android.view.View;
import android.view.ViewGroup;

import com.superdroid.base.R;
import com.superdroid.base.adapters.MineListAdapter;
import com.superdroid.base.customuis.FragmentPageContainer;
import com.superdroid.base.customuis.ListViewForScrollView;
import com.superdroid.base.dataprocessor.MineDataProcressor;
import com.superdroid.base.entities.MineData;
import com.superdroid.base.tasks.RefreshAndLoadMoreDataTask;
import com.superdroid.base.utils.Constants;
import com.superdroid.base.utils.UIUtil;
import com.superdroid.base.utils.ViewUtil;
import com.superdroid.base.viewholders.RefreshAndLoadMoreViewHolder;

import java.util.List;

public class MineFragment extends BaseFragment implements RefreshAndLoadMoreViewHolder.OnLoadMoreListener<List<MineData>>, RefreshAndLoadMoreViewHolder.OnRefreshListener {

    private List<MineData> datas;
    private MineListAdapter adapter;
    private MineDataProcressor mMineDataProcressor;
    private RefreshAndLoadMoreViewHolder mRefreshAndLoadMoreViewHolder;

    private boolean isRefreshing;
    private boolean isLoading;
    private boolean hasMoreData = true;
    private int page;

    @Override
    protected View createSuccessPage() {
        View view = ViewUtil.inflate(R.layout.fragment_mine);
        ListViewForScrollView lv = new ListViewForScrollView(getActivity());
        mRefreshAndLoadMoreViewHolder = new RefreshAndLoadMoreViewHolder();
        mRefreshAndLoadMoreViewHolder.getLoadMoreHolder().refreshView(datas.size() > 10 ? Constants.HAVE_MOREDATA_STATUS : Constants.NO_DATA_STATUS);
        ((ViewGroup) view).addView(mRefreshAndLoadMoreViewHolder.getContentView());
        mRefreshAndLoadMoreViewHolder.getFl_center().addView(lv);
        adapter = new MineListAdapter(datas);
        lv.setAdapter(adapter);
        mRefreshAndLoadMoreViewHolder.setOnRefreshListener(this);
        mRefreshAndLoadMoreViewHolder.setOnLoadMoreListener(this);
        return view;
    }

    @Override
    protected FragmentPageContainer.LoadResult loadData() {
        initMineDataProcressor();
        datas = mMineDataProcressor.loadData();
        return getLoadDataResult(datas);
    }

    /**
     * 初始化数据处理器
     */
    private void initMineDataProcressor() {
        mMineDataProcressor = new MineDataProcressor();
    }

    @Override
    public void refreshBegin() {
        if (!isRefreshing) {
            new RefreshAndLoadMoreDataTask<List<MineData>>(mMineDataProcressor) {
                @Override
                protected void loadDataOk(List<MineData> data) {
                    if (data != null && data.size() > 0) {
                        MineFragment.this.datas = data;
                    }
                    mRefreshAndLoadMoreViewHolder.onRefreshComplete();
                }
            }.execute();
            isRefreshing = true;
        }

    }

    /**
     * 刷新完成时调用
     */
    @Override
    public void refreshComplete() {
        isRefreshing = false;
        if (adapter != null) {
            adapter.refreshData(datas);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loadMoreBegin() {
        if (!isLoading) {
            isLoading = true;
            if (hasMoreData)
                new RefreshAndLoadMoreDataTask<List<MineData>>(mMineDataProcressor) {
                    @Override
                    protected void loadDataOk(List<MineData> data) {
                        MineFragment.this.loadMoreComplete(data);
                    }
                }.execute();
            else
                UIUtil.showToast("无更多数据");
        }
    }

    @Override
    public void loadMoreComplete(List<MineData> data) {
        isLoading = false;
        if (data == null) {
            mRefreshAndLoadMoreViewHolder.getLoadMoreHolder().setData(Constants.ERROR_STATUS);
        } else if (data.size() == 0) {
            hasMoreData = false;
            mRefreshAndLoadMoreViewHolder.getLoadMoreHolder().setData(Constants.NO_DATA_STATUS);
        } else {
            hasMoreData = true;
            page++;
            mRefreshAndLoadMoreViewHolder.getLoadMoreHolder().setData(Constants.HAVE_MOREDATA_STATUS);
            mRefreshAndLoadMoreViewHolder.getLoadMoreHolder().getContentView().setVisibility(View.GONE);
            this.datas.addAll(data);
            adapter.refreshData(this.datas);
            adapter.notifyDataSetChanged();
        }
        mRefreshAndLoadMoreViewHolder.getLoadMoreHolder().refreshView();
    }


}
