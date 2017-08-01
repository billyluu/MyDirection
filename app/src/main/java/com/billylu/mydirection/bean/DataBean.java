package com.billylu.mydirection.bean;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by art-imac-02 on 2017/7/13.
 */

public class DataBean implements Serializable{
    private String id;
    private String date;
    private List<DirectionBean> directionList;

    public List<DirectionBean> getDirectionList() {
        return directionList;
    }

    public void setDirectionList(List<DirectionBean> directionList) {
        this.directionList = directionList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }






}
