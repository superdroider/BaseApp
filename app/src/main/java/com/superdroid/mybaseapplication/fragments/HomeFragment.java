package com.superdroid.mybaseapplication.fragments;

import android.view.View;
import android.widget.TextView;

import com.superdroid.mybaseapplication.FragmentPageContainer;
import com.superdroid.mybaseapplication.dataprocessor.HomeDataProcressor;
import com.superdroid.mybaseapplication.entities.HomeData;
import com.superdroid.mybaseapplication.utils.LogUtil;

import java.util.List;


public class HomeFragment extends BaseFragment {
    @Override
    protected View createSuccessPage() {
        LogUtil.i("---createSuccessPage---");
        if (getActivity() == null) {
            return null;
        }
        TextView tv = new TextView(getActivity());
        tv.setText("success");
        return tv;
    }

    @Override
    protected FragmentPageContainer.LoadResult loadData() {
        List<HomeData.Data> data = new HomeDataProcressor().loadData();
        if (data == null) {
            return FragmentPageContainer.LoadResult.error;
        } else if (data.size() == 0) {
            return FragmentPageContainer.LoadResult.empty;
        } else {

            return FragmentPageContainer.LoadResult.success;
        }
    }


}
