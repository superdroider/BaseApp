package com.superdroid.mybaseapplication.fragments;

import android.view.View;
import android.widget.TextView;

import com.superdroid.mybaseapplication.FragmentPageContainer;

public class MineFragment extends BaseFragment {

    @Override
    protected View createSuccessPage() {
        TextView tv = new TextView(getActivity());
        tv.setText("success");
        return tv;
    }

    @Override
    protected FragmentPageContainer.LoadResult loadData() {
        return FragmentPageContainer.LoadResult.error;
    }


}
