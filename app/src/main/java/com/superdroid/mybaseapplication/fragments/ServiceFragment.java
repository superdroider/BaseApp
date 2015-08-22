package com.superdroid.mybaseapplication.fragments;

import android.view.View;

import com.superdroid.mybaseapplication.FragmentPageContainer;


public class ServiceFragment extends BaseFragment {


    @Override
    protected View createSuccessPage() {
        return null;
    }

    @Override
    protected FragmentPageContainer.LoadResult loadData() {
        return FragmentPageContainer.LoadResult.empty;
    }


}
