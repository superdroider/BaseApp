package com.superdroid.base.adapters;

import com.superdroid.base.entities.ServiceData;
import com.superdroid.base.viewholders.BaseHolder;
import com.superdroid.base.viewholders.ServiceHolder;

import java.util.List;

/**
 * Created by GT on 2015/8/25.
 */
public class ServiceAdapter extends DefaultAdapter<ServiceData> {
    public ServiceAdapter(List<ServiceData> data) {
        super(data);
    }

    @Override
    protected BaseHolder<ServiceData> getBaseHolder() {
        return new ServiceHolder();
    }
}
