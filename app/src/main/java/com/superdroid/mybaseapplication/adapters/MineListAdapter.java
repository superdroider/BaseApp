package com.superdroid.mybaseapplication.adapters;

import com.superdroid.mybaseapplication.entities.MineData;
import com.superdroid.mybaseapplication.viewholders.BaseHolder;
import com.superdroid.mybaseapplication.viewholders.MineHolder;

import java.util.List;

/**
 * Created by GT on 2015/8/23.
 */
public class MineListAdapter extends DefaultAdapter<MineData> {
    public MineListAdapter(List<MineData> data) {
        super(data);
    }

    @Override
    protected BaseHolder<MineData> getBaseHolder() {
        return new MineHolder();
    }
}
