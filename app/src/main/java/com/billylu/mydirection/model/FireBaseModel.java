package com.billylu.mydirection.model;

import android.util.Log;

import com.billylu.mydirection.bean.DataBean;
import com.billylu.mydirection.bean.DirectionBean;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void saveData(String date, String key, String value) {
        myRef.child(date).push().child(key).setValue(value);

    }


    public void readData(final FireBaseCallBack fireBaseCallBack) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<DataBean> list = new ArrayList<DataBean>();

                Log.i(TAG, dataSnapshot.getKey() + "");
                Log.i(TAG, dataSnapshot.getValue() + "");
                if (dataSnapshot.getValue() != null) {
                    HashMap map = new HashMap((Map) dataSnapshot.getValue());
                    Log.i(TAG, map.keySet() + "");
                    for (Object d : map.keySet()) {
                        DataBean dataBean = new DataBean();
                        dataBean.setDate(d + "");
                        List<DirectionBean> dList = new ArrayList<DirectionBean>();
                        HashMap m = (HashMap) map.get(d);
                        for (Object b : m.keySet()) {
                            HashMap p = (HashMap) m.get(b);
                            DirectionBean dirBean = new DirectionBean();
                            dirBean.setId(b + "");
                            dirBean.setDirection(p.get("Direction") + "");
                            dList.add(dirBean);
                        }
                        dataBean.setDirectionList(dList);
                        list.add(dataBean);
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


    public void deleteData(String date, String child){
        Log.i(TAG, date);
        Log.i(TAG, child);
        myRef.child(date).child(child).removeValue();
    }

    public interface FireBaseCallBack {
        void onGetData(List list);
    }
}
