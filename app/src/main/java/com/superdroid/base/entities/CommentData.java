package com.superdroid.base.entities;

import java.util.List;

/**
 * Created by Administrator on 2015/8/23.
 */
public class CommentData<T> {
    private long timesec;
    private int ec;
    private String em;
    private List<T> data;

    public long getTimesec() {
        return timesec;
    }

    public void setTimesec(long timesec) {
        this.timesec = timesec;
    }

    public int getEc() {
        return ec;
    }

    public void setEc(int ec) {
        this.ec = ec;
    }

    public String getEm() {
        return em;
    }

    public void setEm(String em) {
        this.em = em;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
