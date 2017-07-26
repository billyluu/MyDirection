package com.billylu.mydirection.bean;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by art-imac-02 on 2017/7/13.
 */

public class DirectionBean implements Serializable{
    private String id;
    private String date;
    private String direction;

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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }





}
