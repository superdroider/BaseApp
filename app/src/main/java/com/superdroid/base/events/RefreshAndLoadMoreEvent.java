package com.superdroid.base.events;

/**
 * Created by GT on 2015/8/27.
 */
public class RefreshAndLoadMoreEvent<T> {
    private int type;
    private int status;
    private T data;

    public RefreshAndLoadMoreEvent(int type, int status) {
        this.type = type;
        this.status = status;
    }

    public RefreshAndLoadMoreEvent(int type, int status, T data) {
        this.type = type;
        this.status = status;
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }
}
