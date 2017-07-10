package com.billylu.mydirection;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.billylu.mydirection.Adapter.RecycleAdapter;
import com.billylu.mydirection.model.CallBack;
import com.billylu.mydirection.model.MyApplication;
import com.billylu.mydirection.model.MyDialog;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private RecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "Size:" + MyApplication.directionBeanList.size());
        recyclerView = (RecyclerView) findViewById(R.id.recycle_listview);

        if(MyApplication.directionBeanList.size() != 0){
            setRecyclerView();
        } else {
            new MyDialog(this).normalDialog();
        }

    }



    public void btn_add_direction(View view){
        MyDialog dialog = new MyDialog(this);
        dialog.showAddDirectionDialog(new CallBack() {
            @Override
            public void changed() {
                setRecyclerView();
            }
        });

    }

    private void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecycleAdapter(this, R.layout.recycle_view_item);
        recyclerView.setAdapter(adapter);
    }



}
