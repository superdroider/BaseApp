package com.superdroid.mybaseapplication.fragments;

import android.view.View;
import android.widget.ListView;

import com.superdroid.mybaseapplication.R;
import com.superdroid.mybaseapplication.adapters.MineListAdapter;
import com.superdroid.mybaseapplication.customuis.FragmentPageContainer;
import com.superdroid.mybaseapplication.dataprocessor.MineDataProcressor;
import com.superdroid.mybaseapplication.entities.MineData;
import com.superdroid.mybaseapplication.utils.ViewUtil;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MineFragment extends BaseFragment {

    private PtrClassicFrameLayout mPtrFrame;
    private ListView base_lv;
    private List<MineData> datas;
    private MineListAdapter adapter;
    private MineDataProcressor mMineDataProcressor;


    @Override
    protected View createSuccessPage() {
        View view = ViewUtil.inflate(R.layout.base_list);
        initView(view);
        adapter = new MineListAdapter(datas);
        base_lv.setAdapter(adapter);
        setupPtrClassicFrameLayout();
        return view;
    }

    private void initMineDataProcressor() {
        mMineDataProcressor = new MineDataProcressor() {
            @Override
            protected void refreshComplete(List<MineData> data) {
                datas = data;
                MineFragment.this.refreshComplete();
            }
        };
    }

    private void setupPtrClassicFrameLayout() {
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
                mContainer.syncLoadData();
            }
        });
    }

    private void initView(View view) {
        mPtrFrame = (PtrClassicFrameLayout) view.findViewById(R.id.base_pfl);
        base_lv = (ListView) view.findViewById(R.id.base_lv);
    }

    @Override
    protected FragmentPageContainer.LoadResult loadData() {
        initMineDataProcressor();
        datas = mMineDataProcressor.loadData();
        return getLoadDataResult();
    }

    private FragmentPageContainer.LoadResult getLoadDataResult() {
        if (datas == null) {
            return FragmentPageContainer.LoadResult.error;
        } else if (datas.size() == 0) {
            return FragmentPageContainer.LoadResult.empty;
        } else {
            return FragmentPageContainer.LoadResult.success;
        }
    }

    private void refreshComplete() {
        if (mPtrFrame != null && mPtrFrame.isRefreshing()) {
            mPtrFrame.refreshComplete();
            if (adapter != null) {
                adapter.refreshData(datas);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
