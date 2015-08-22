package com.superdroid.mybaseapplication.fragments;

import android.view.View;
import android.widget.TextView;

import com.superdroid.mybaseapplication.FragmentPageContainer;
import com.superdroid.mybaseapplication.utils.LogUtil;


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
        LogUtil.i("---loadData---");
        return FragmentPageContainer.LoadResult.success;
    }


}
