package com.superdroid.base.viewholders;

import android.view.View;

/**
 * Created by Administrator on 2015/8/23.
 */
public abstract class BaseHolder<T> {

    protected View contentView;

    protected T data;

    public BaseHolder() {
        contentView = initView();
    }

    public View getContentView() {
        return contentView;
    }


    public void setData(T data) {
        this.data = data;
    }

    public void refreshView(T data) {
        this.data = data;
        refreshView();
    }

    public abstract View initView();

    public abstract void refreshView();



}
