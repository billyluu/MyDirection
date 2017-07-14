package com.billylu.mydirection.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by art-imac-02 on 2017/7/13.
 */

public class Check {

    private static Context mContext;


    public Check(Context mContext) {
        this.mContext = mContext;
    }

    public Boolean checkNetWork() {
        Boolean result = false;
        ConnectivityManager mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        //如果未連線的話，mNetworkInfo會等於null
        if(mNetworkInfo != null) {
//            //網路是否已連線(true or false)
//            mNetworkInfo.isConnected();
//            //網路連線方式名稱(WIFI or mobile)
//            mNetworkInfo.getTypeName();
//            //網路連線狀態
//            mNetworkInfo.getState();
//            //網路是否可使用
//            mNetworkInfo.isAvailable();
//            //網路是否已連接or連線中
//            mNetworkInfo.isConnectedOrConnecting();
//            //網路是否故障有問題
//            mNetworkInfo.isFailover();
//            //網路是否在漫遊模式
//            mNetworkInfo.isRoaming();

            result = true;
        }

        return result;
    }




}
