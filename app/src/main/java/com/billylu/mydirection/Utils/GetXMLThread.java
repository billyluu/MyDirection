package com.billylu.mydirection.Utils;

import android.util.Log;

import com.billylu.mydirection.model.MyDialog;
import com.billylu.mydirection.model.OkHttp;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;

/**
 * Created by billylu on 2017/9/6.
 */

public class GetXMLThread extends Thread {

    private String url;
    private CallBack callBack;


    public GetXMLThread(String url, GetXMLThread.CallBack callBack) {
        this.url = url;
        this.callBack = callBack;
    }

    @Override
    public void run() {
        super.run();
        try {
            OkHttp okHttp = new OkHttp(url);
            String xmlPath = okHttp.get();
            ParserXML p = new ParserXML();
            String[] latlng = p.parseXML(xmlPath);
            callBack.getResult(latlng);
        }catch (IOException e){

        }

    }

    public interface CallBack{

        void getResult(String[] latLng);

    }
}
