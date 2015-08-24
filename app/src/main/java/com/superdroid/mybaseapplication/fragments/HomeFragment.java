package com.superdroid.mybaseapplication.fragments;

import android.widget.TextView;

import com.superdroid.mybaseapplication.adapters.DefaultAdapter;
import com.superdroid.mybaseapplication.adapters.HomeListAdapter;
import com.superdroid.mybaseapplication.dataprocessor.BaseDataProcessor;
import com.superdroid.mybaseapplication.dataprocessor.HomeDataProcressor;
import com.superdroid.mybaseapplication.entities.HomeData;

import java.util.List;


public class HomeFragment extends BaseListFragment<HomeData.Data> {

    @Override
    protected void addBottomeView() {
        TextView textView = new TextView(getActivity());
        textView.setText("addBottomeView");
        getBottomView().addView(textView);
    }

    @Override
    protected void addHeaderView() {
        TextView textView = new TextView(getActivity());
        textView.setText("addHeaderView");
        getHeaderView().addView(textView);
    }

    @Override
    protected DefaultAdapter<HomeData.Data> getDefaultAdapter() {
        return new HomeListAdapter(data);
    }

    @Override
    protected BaseDataProcessor<List<HomeData.Data>> getBaseDataProcessor() {
        return new HomeDataProcressor() {
            @Override
            protected void refreshComplete(List<HomeData.Data> data) {
                if (mPtrFrame != null && mPtrFrame.isRefreshing()) {
                    mPtrFrame.refreshComplete();
                    if (adapter != null && data != null) {
                        adapter.refreshData(data);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        };
    }
}
