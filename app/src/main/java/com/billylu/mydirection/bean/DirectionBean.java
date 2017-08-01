package com.billylu.mydirection.bean;

import java.io.Serializable;

/**
 * Created by billylu on 2017/8/1.
 */

public class DirectionBean implements Serializable{

    private String id;
    private String direction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }



}
