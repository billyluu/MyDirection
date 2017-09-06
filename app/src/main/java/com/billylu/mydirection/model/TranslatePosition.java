package com.billylu.mydirection.model;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.Locale;

/**
 * Created by billylu on 2017/8/31.
 */

public class TranslatePosition {

    private Context mContext;

    public TranslatePosition(Context mContext) {
        this.mContext = mContext;
    }


    public LatLng getLatLng(String placeName) {
        double latitude = 0;
        double longitude = 0;

        try {
            Geocoder geocoder = new Geocoder(mContext, new Locale("zh", "TW"));
            List<Address> addressLocation = geocoder.getFromLocationName(placeName, 1);
            latitude = addressLocation.get(0).getLatitude();
            longitude = addressLocation.get(0).getLongitude();
        }catch (Exception e){
            e.printStackTrace();
        }



        return new LatLng(latitude, longitude);
    }

}
