package com.billylu.mydirection;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.billylu.mydirection.model.BaseActivity;
import com.billylu.mydirection.model.Check;
import com.billylu.mydirection.bean.DirectionBean;
import com.billylu.mydirection.model.FireBaseModel;
import com.billylu.mydirection.model.MyDialog;
import com.billylu.mydirection.model.Utils;

import java.util.List;

public class MainActivity extends BaseActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    private static final int REQUEST_PHONE_STATE = 0;

    private RecyclerView recyclerView;
    private RecycleAdapter adapter;
    private String imei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar(R.id.toolbar, true, R.menu.toolbar_menu);
        checkPermisson();
        checkNetWork();

        imei = new Utils(this).getIMEI();
        recyclerView = (RecyclerView) findViewById(R.id.recycle_listview);

        if (!imei.equals(null)) {
            setView();
        }
    }

    @Override
    protected void setMenuItemAction(int itemID) {
        super.setMenuItemAction(itemID);
        switch (itemID){
            case R.id.menu_add:
                Toast.makeText(MainActivity.this, "新增", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_setting:
                Toast.makeText(MainActivity.this, "設定", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_exit:
                Toast.makeText(MainActivity.this, "離開", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void btn_add_direction(View view){
        MyDialog dialog = new MyDialog(this);
        dialog.showAddDirectionDialog(imei);
    }

    private void setView() {
        new FireBaseModel(imei).readData(new FireBaseModel.FireBaseCallBack(){
            @Override
            public void onGetData(List<DirectionBean> list) {
                setRecyclerView(list);
            }
        });
    }

    private void setRecyclerView(List<DirectionBean> dataList) {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecycleAdapter(dataList, R.layout.recycle_view_item);
        recyclerView.setAdapter(adapter);
    }

    class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
        private int layout;
        private List<DirectionBean> list;

        public RecycleAdapter(List<DirectionBean> list, int layout) {
            this.layout = layout;
            this.list = list;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public TextView textView;
            public Button send;
            public ConstraintLayout viewitem;
            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.text_direction);
                send = (Button) itemView.findViewById(R.id.btn_send);
                viewitem = (ConstraintLayout) itemView.findViewById(R.id.viewitem);
            }
        }

        @Override
        public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
            RecycleAdapter.ViewHolder holder = new RecycleAdapter.ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecycleAdapter.ViewHolder holder, final int position) {
            final DirectionBean bean = list.get(position);
            holder.textView.setText(bean.getDirection());
            holder.send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String dir = bean.getDirection();
                    Log.i("DIR", dir);
                    startSearchDirection(dir);
                }
            });

            holder.viewitem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    String key = bean.getKey();
                    new MyDialog(MainActivity.this).deleteDialog(imei, key);
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public void startSearchDirection(String dir) {
            Uri intentUri = Uri.parse("geo:0,0?q="+dir);
            Intent intent = new Intent(Intent.ACTION_VIEW, intentUri);
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        }
    }

    private void checkPermisson(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.READ_PHONE_STATE}, REQUEST_PHONE_STATE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 取得權限
                    setView();
                } else {
                    // 未取得權限
                    new MyDialog(this).showWarmDialog(MainActivity.this, "請開啟權限。");
                }
                break;
        }
    }

    private void checkNetWork() {
        if (!new Check(this).checkNetWork()) {
            new MyDialog(this).showWarmDialog(this, "請開啟網路。");
            return;
        }
    }
}
