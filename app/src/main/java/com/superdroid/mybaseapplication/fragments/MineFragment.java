package com.superdroid.mybaseapplication.fragments;

import android.view.View;
import android.view.ViewGroup;

import com.superdroid.mybaseapplication.R;
import com.superdroid.mybaseapplication.adapters.MineListAdapter;
import com.superdroid.mybaseapplication.customuis.FragmentPageContainer;
import com.superdroid.mybaseapplication.customuis.ListViewForScrollView;
import com.superdroid.mybaseapplication.dataprocessor.BaseDataProcessor;
import com.superdroid.mybaseapplication.dataprocessor.MineDataProcressor;
import com.superdroid.mybaseapplication.entities.MineData;
import com.superdroid.mybaseapplication.manager.ThreadManager;
import com.superdroid.mybaseapplication.tasks.RefreshDataTask;
import com.superdroid.mybaseapplication.utils.ViewUtil;
import com.superdroid.mybaseapplication.viewholders.PflViewHolder;

import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

public class MineFragment extends BaseFragment {

    private List<MineData> datas;
    private MineListAdapter adapter;
    private MineDataProcressor mMineDataProcressor;
    private PflViewHolder pflViewHolder;

    @Override
    protected View createSuccessPage() {
        View view = ViewUtil.inflate(R.layout.fragment_mine);
        ListViewForScrollView lv = new ListViewForScrollView(getActivity());
        pflViewHolder = new PflViewHolder() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                executeRefreshTask();
            }

            @Override
            public void onRefreshComplete(PtrFrameLayout frame) {
                refreshComplete();
            }
        };
        ((ViewGroup) view).addView(pflViewHolder.getContentView());
        pflViewHolder.getScrollView().addView(lv);
        adapter = new MineListAdapter(datas);
        lv.setAdapter(adapter);
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

    /**
     * 执行刷新任务
     */
    private void executeRefreshTask() {
        ThreadManager.getThreadManagerInstance().longTaskExecute(new RefreshDataTask<List<MineData>>() {
            @Override
            protected void refreshComplete(List<MineData> data) {
                MineFragment.this.datas = data;
                pflViewHolder.onRefreshComplete();
            }

            @Override
            protected BaseDataProcessor<List<MineData>> getBaseDataProcressor() {
                return mMineDataProcressor;
            }
        });
    }


    /**
     * 刷新完成时调用
     */
    private void refreshComplete() {
        if (adapter != null && datas != null) {
            adapter.refreshData(datas);
            adapter.notifyDataSetChanged();
        }
    }
}
