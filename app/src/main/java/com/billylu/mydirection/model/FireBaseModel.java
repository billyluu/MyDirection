package com.billylu.mydirection.model;

import android.util.Log;

import com.billylu.mydirection.bean.DirectionBean;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by BillyLu on 2017/7/12.
 */

public class FireBaseModel {
    private final String TAG = FireBaseModel.class.getSimpleName();
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public FireBaseModel(String imei) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(imei);
    }

    public void saveData(String KEY ,String value) {
        myRef.push().child(KEY).setValue(value);

    }

    public void readData(final String KEY, final FireBaseCallBack fireBaseCallBack) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<DirectionBean> list = new ArrayList<DirectionBean>();
                Log.i(TAG, dataSnapshot + "");
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    try {
                        Log.i(TAG, ds.getKey());
                        Log.i(TAG, ds.getValue()+ "");
                        HashMap map = (HashMap) ds.getValue();
                        DirectionBean bean = new DirectionBean();
                        bean.setId(ds.getKey());
                        bean.setDate((String) map.getOrDefault("Date", ""));
                        bean.setDirection((String) map.getOrDefault("Direction", ""));
                        list.add(bean);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                fireBaseCallBack.onGetData(list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void deleteData(String key){
        myRef.child(key).removeValue();
    }

    public interface FireBaseCallBack {
        void onGetData(List list);
    }
}
