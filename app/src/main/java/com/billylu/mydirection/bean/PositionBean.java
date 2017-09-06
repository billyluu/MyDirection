package com.billylu.mydirection.bean;

import java.io.Serializable;

/**
 * Created by billylu on 2017/9/6.
 */

public class PositionBean implements Serializable {

    private String address;
    private double lat;
    private double lng;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

}
