package com.superdroid.mybaseapplication.fragments;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.superdroid.mybaseapplication.FragmentPageContainer;
import com.superdroid.mybaseapplication.adapters.HomeListAdapter;
import com.superdroid.mybaseapplication.dataprocessor.HomeDataProcressor;
import com.superdroid.mybaseapplication.entities.HomeData;
import com.superdroid.mybaseapplication.utils.LogUtil;

import java.util.List;


public class HomeFragment extends BaseFragment {

    List<HomeData.Data> data;

    @Override
    protected View createSuccessPage() {
        if (getActivity() == null) {
            return null;
        }
        ListView homeList = new ListView(getActivity());
        homeList.setAdapter(new HomeListAdapter(data));
        return homeList;
    }

    @Override
    protected FragmentPageContainer.LoadResult loadData() {
        data = new HomeDataProcressor().loadData();
        if (data == null) {
            return FragmentPageContainer.LoadResult.error;
        } else if (data.size() == 0) {
            return FragmentPageContainer.LoadResult.empty;
        } else {

            return FragmentPageContainer.LoadResult.success;
        }
    }


}
