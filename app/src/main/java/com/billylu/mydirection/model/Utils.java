package com.billylu.mydirection.model;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by billylu on 2017/7/18.
 */

public class Utils {
    private Context context;
    private static TelephonyManager telephonyManager;
    public Utils (Context context){
        this.context = context;
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public String getIMEI() {
        return telephonyManager.getDeviceId();
    }



}
