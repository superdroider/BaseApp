package com.superdroid.base.entities;

import java.util.List;

/**
 * Created by GT on 2015/8/22.
 */
public class HomeData {
    private long timesec;
    private int ec;
    private String em;
    private List<Data> data;

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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data{
        private String id;
        private String name;
        private String type;
        private String position;
        private String icon;
        private String add;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getAdd() {
            return add;
        }

        public void setAdd(String add) {
            this.add = add;
        }
    }

}
