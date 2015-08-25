package com.superdroid.mybaseapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.superdroid.mybaseapplication.customuis.FragmentPageContainer;
import com.superdroid.mybaseapplication.utils.LogUtil;
import com.superdroid.mybaseapplication.utils.ViewUtil;

/**
 * A simple {@link Fragment} subclass.
 * 所有fragment页面共有四种状态：加载中、加载出错、内容为空、加载成功
 */
public abstract class BaseFragment extends Fragment {

    protected FragmentPageContainer mContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mContainer == null) {
            initFragmentPageContainer();
        } else {
            ViewUtil.removeView(mContainer);
        }
        show();
        return mContainer;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mContainer = null;
    }

    private void initFragmentPageContainer() {
        mContainer = new FragmentPageContainer(getActivity()) {
            @Override
            protected View createSuccessPage() {
                if (getActivity() == null) {
                    return null;
                }
                return BaseFragment.this.createSuccessPage();
            }

            @Override
            protected LoadResult loadData() {
                return BaseFragment.this.loadData();
            }
        };
    }

    public void show() {
        if (mContainer != null) {
            mContainer.show();
        }
    }

    /**
     * 创建成功页面
     *
     * @return
     */
    protected abstract View createSuccessPage();

    /**
     * 加载数据
     *
     * @return
     */
    protected abstract FragmentPageContainer.LoadResult loadData();

}
