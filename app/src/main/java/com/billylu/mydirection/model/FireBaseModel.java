package com.billylu.mydirection.model;

import android.util.Log;

import com.billylu.mydirection.bean.DirectionBean;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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

    public void saveData(String direction) {
        myRef.push().child("Direction").setValue(direction);
        Log.i(TAG, "saveData");
    }

    public void readData(final FireBaseCallBack fireBaseCallBack) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<DirectionBean> list = new ArrayList<DirectionBean>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    DirectionBean bean = new DirectionBean();
                    bean.setKey(ds.getKey());
                    bean.setDirection(ds.child("Direction").getValue().toString());
                    list.add(bean);
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
        void onGetData(List<DirectionBean> list);
    }

}
