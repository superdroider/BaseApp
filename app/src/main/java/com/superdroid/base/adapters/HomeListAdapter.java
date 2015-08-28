package com.superdroid.base.adapters;

import com.superdroid.base.entities.HomeData;
import com.superdroid.base.viewholders.BaseHolder;
import com.superdroid.base.viewholders.HomeHolder;

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
