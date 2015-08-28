package com.superdroid.base.adapters;

import com.superdroid.base.entities.MineData;
import com.superdroid.base.viewholders.BaseHolder;
import com.superdroid.base.viewholders.MineHolder;

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
