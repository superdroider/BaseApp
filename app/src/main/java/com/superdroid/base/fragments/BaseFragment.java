package com.superdroid.base.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.superdroid.base.customuis.FragmentPageContainer;
import com.superdroid.base.utils.ViewUtil;

import java.util.List;

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
     * 根据请求的结果判断生成请求状态码
     *
     * @param data 请求结果
     * @return
     */
    protected <T> FragmentPageContainer.LoadResult getLoadDataResult(T data) {
        if (data == null) {
            return FragmentPageContainer.LoadResult.error;
        } else if (data instanceof List && ((List) data).size() == 0) {
            return FragmentPageContainer.LoadResult.empty;
        } else {
            return FragmentPageContainer.LoadResult.success;
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
