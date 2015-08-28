package com.superdroid.base.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.superdroid.base.viewholders.BaseHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/23.
 * 适配器基类
 */
public abstract class DefaultAdapter<T> extends BaseAdapter {

    private List<T> data;

    public DefaultAdapter(List<T> data) {
        if (data == null) {
            this.data = new ArrayList<T>();
            throw new NullPointerException(getClass().getSimpleName() + " 中的data不允许为空");
        }
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }


    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        BaseHolder<T> holder = null;
        if (convertView == null) {
            holder = getBaseHolder();

            view = holder.getContentView();
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (BaseHolder<T>) view.getTag();
        }
        holder.setData(data.get(position));
        holder.refreshView();
        return view;
    }


    /**
     * 刷新数据并通知listview
     *
     * @param data
     */
    public void refreshData(List<T> data) {
        if (data == null) {
            this.data = new ArrayList<T>();
            throw new NullPointerException(getClass().getSimpleName() + " 中的data不允许为空");
        } else {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    protected abstract BaseHolder<T> getBaseHolder();
}
