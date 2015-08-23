package com.superdroid.mybaseapplication.fragments;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.superdroid.mybaseapplication.FragmentPageContainer;
import com.superdroid.mybaseapplication.dataprocessor.MineDataProcressor;
import com.superdroid.mybaseapplication.entities.MineData;

import java.util.List;

public class MineFragment extends BaseFragment {

    private List<MineData> datas;

    @Override
    protected View createSuccessPage() {
        ListView mineList = new ListView(getActivity());
mineList.setAdapter(new MineListAdapter());
        TextView tv = new TextView(getActivity());
        tv.setText("success");
        return tv;
    }

    @Override
    protected FragmentPageContainer.LoadResult loadData() {
        datas =  new MineDataProcressor().loadData();
        return FragmentPageContainer.LoadResult.error;
    }


}
