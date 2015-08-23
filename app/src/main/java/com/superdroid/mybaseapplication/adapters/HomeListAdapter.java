package com.superdroid.mybaseapplication.adapters;

import com.superdroid.mybaseapplication.entities.HomeData;
import com.superdroid.mybaseapplication.viewholders.BaseHolder;
import com.superdroid.mybaseapplication.viewholders.HomeHolder;

import java.util.List;

/**
 * Created by Administrator on 2015/8/23.
 */
public class HomeListAdapter extends DefaultAdapter<HomeData.Data> {


    public HomeListAdapter(List<HomeData.Data> data) {
        super(data);
    }

    @Override
    protected BaseHolder<HomeData.Data> getBaseHolder() {
        return new HomeHolder();
    }
}
