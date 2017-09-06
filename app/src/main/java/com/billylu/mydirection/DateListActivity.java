package com.billylu.mydirection;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.billylu.mydirection.bean.DataBean;
import com.billylu.mydirection.Utils.BaseActivity;
import com.billylu.mydirection.Utils.Check;
import com.billylu.mydirection.model.FireBaseModel;
import com.billylu.mydirection.Utils.MyDialog;
import com.billylu.mydirection.Utils.Utils;

import java.util.List;

/**
 * Created by billylu on 2017/7/25.
 */

public class DateListActivity extends BaseActivity {
    private final String TAG = DateListActivity.class.getSimpleName();
    private static final int REQUEST_PHONE_STATE = 0;

    private RecyclerView recyclerView;
    private RecycleAdapter adapter;
    private String imei = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_list_layout);

        recyclerView = (RecyclerView) findViewById(R.id.date_list_recycle_listview);
        imei = new Utils(this).getIMEI();

        setToolbar(R.id.toolbar, false, R.menu.toolbar_menu);
        setMenuPoint(false);

        checkPermisson();
        checkNetWork();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!imei.equals(null)) {
            setView();
        }
    }

    private void setView() {
        new FireBaseModel(imei).readData(new FireBaseModel.FireBaseCallBack() {
            @Override
            public void onGetData(List list) {
                setRecyclerView(list);
            }
        });
    }

    private void setRecyclerView(List<DataBean> list) {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecycleAdapter(list, R.layout.date_list_item);
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void setMenuItemAction(int itemID) {
        super.setMenuItemAction(itemID);
        switch (itemID){
            case R.id.menu_add:
                new MyDialog(this).showDatePickerDialog();
                break;
            case R.id.menu_setting:
                Toast.makeText(DateListActivity.this, "設定", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_exit:
                Toast.makeText(DateListActivity.this, "離開", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
        private int layout;
        private List<DataBean> list;

        public RecycleAdapter(List<DataBean> list, int layout) {
            this.layout = layout;
            this.list = list;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public TextView textView;
            public ConstraintLayout viewitem;
            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.text_date);
                viewitem = (ConstraintLayout) itemView.findViewById(R.id.date_list_item);
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
            final DataBean bean =  list.get(position);
            holder.textView.setText(bean.getDate());

            holder.viewitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DateListActivity.this, DirectionActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("bean", bean);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });


            holder.viewitem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    String key = bean.getDate();
                    new MyDialog(DateListActivity.this).deleteDialog(key);
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

    }

    private void checkPermisson(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
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
                    new MyDialog(this).showWarmDialog(DateListActivity.this, "請開啟權限。");
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
