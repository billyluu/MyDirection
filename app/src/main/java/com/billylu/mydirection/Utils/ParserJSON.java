package com.billylu.mydirection.Utils;

import android.provider.DocumentsContract;
import android.util.Log;

import com.billylu.mydirection.bean.PositionBean;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by billylu on 2017/9/6.
 */

public class ParserJSON implements Serializable{
    private final String TAG = ParserJSON.class.getSimpleName();

    public ParserJSON() {

    }

    public PositionBean parseJson(String JsonContent) {
        PositionBean positionBean = new PositionBean();
        try {
            JSONObject jsonObject = new JSONObject(JsonContent);
            JSONObject obj = jsonObject.getJSONArray("results").getJSONObject(0);
            JSONObject geometry = obj.getJSONObject("geometry");
            JSONObject location = geometry.getJSONObject("location");
            positionBean.setAddress(obj.getString("formatted_address"));
            positionBean.setLat(Double.valueOf(location.getString("lat")));
            positionBean.setLng(Double.valueOf(location.getString("lng")));
        }catch (Exception e){
            e.printStackTrace();
        }

        return positionBean;
    }

}
