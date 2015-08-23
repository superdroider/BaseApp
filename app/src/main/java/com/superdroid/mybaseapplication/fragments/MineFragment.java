package com.superdroid.mybaseapplication.fragments;

import android.view.View;
import android.widget.ListView;

import com.superdroid.mybaseapplication.FragmentPageContainer;
import com.superdroid.mybaseapplication.adapters.MineListAdapter;
import com.superdroid.mybaseapplication.dataprocessor.MineDataProcressor;
import com.superdroid.mybaseapplication.entities.MineData;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

public class MineFragment extends BaseFragment {

    private PtrClassicFrameLayout mPtrFrame;

    private List<MineData> datas;


    @Override
    protected View createSuccessPage() {
        ListView mineList = new ListView(getActivity());
        mineList.setAdapter(new MineListAdapter(datas));
        mPtrFrame = new PtrClassicFrameLayout(getActivity());
//        / the following are default settings
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
// default is false
        mPtrFrame.setPullToRefresh(false);
// default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.addView(mineList);

        return mPtrFrame;
    }

    @Override
    protected FragmentPageContainer.LoadResult loadData() {
        datas = new MineDataProcressor().loadData();
        if (datas == null) {
            return FragmentPageContainer.LoadResult.error;
        } else if (datas.size() == 0) {
            return FragmentPageContainer.LoadResult.empty;
        } else {
            return FragmentPageContainer.LoadResult.success;
        }
    }
}
