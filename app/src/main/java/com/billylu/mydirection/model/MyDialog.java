package com.billylu.mydirection.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.billylu.mydirection.DirectionActivity;
import com.billylu.mydirection.R;
import com.billylu.mydirection.bean.DataBean;
import com.billylu.mydirection.bean.DirectionBean;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by BillyLu on 2017/7/10.
 */

public class MyDialog {
    private final String TAG = MyDialog.class.getSimpleName();
    private Context context;
    private AlertDialog.Builder builder;
    private String IMEI;

    public MyDialog(Context context){
        this.context = context;
        builder = new AlertDialog.Builder(context);
        IMEI = new Utils(context).getIMEI();
    }

    public void normalDialog() {
        builder.setMessage("目前尚無資料。");
        builder.setPositiveButton(R.string.confirm, null);
        builder.show();
    }

    public void showDatePickerDialog(){
        final View layout = LayoutInflater.from(context).inflate(R.layout.add_date_layout, null);
        builder.setTitle("設定日期")
                .setView(layout)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatePicker datePicker = (DatePicker) layout.findViewById(R.id.date_picker);
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth()+1;
                        int day = datePicker.getDayOfMonth();
                        String date = year + "-" + month + "-" + day;
                        Intent intent = new Intent(context, DirectionActivity.class);
                        DataBean bean = new DataBean();
                        bean.setDate(date);
                        bean.setDirectionList(new ArrayList<DirectionBean>());
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("bean", bean);
                        intent.putExtras(bundle);
                        context.startActivity(intent);

                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();

    }

    public void showAddDirectionDialog(final String date, final CallBack callback) {
        final View layout = LayoutInflater.from(context).inflate(R.layout.add_direction_layout, null);
        builder.setTitle("新增目的地")
                .setView(layout)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            String direction = ((EditText)layout.findViewById(R.id.edit_direction)).getText().toString();
                            new FireBaseModel(IMEI).saveData(date, "Direction", direction);
                            callback.onSucc();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    public void deleteDialog(final  String key) {
        builder.setMessage("確定刪除？")
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new FireBaseModel(IMEI).deleteData(key);
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    public void deleteDirectionDialog(final String date, final  String key, final CallBack callback) {
        builder.setMessage("確定刪除？")
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new FireBaseModel(IMEI).deleteData(date, key);
                        callback.onSucc();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    public void showWarmDialog(final AppCompatActivity activity, String msg) {
        builder.setMessage(msg)
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                })
                .show();

    }

    public interface CallBack {
        void onSucc();
    }
}
