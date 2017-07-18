package com.billylu.mydirection.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.billylu.mydirection.R;

/**
 * Created by BillyLu on 2017/7/10.
 */

public class MyDialog {
    private final String TAG = MyDialog.class.getSimpleName();
    private Context context;
    private AlertDialog.Builder builder;

    public MyDialog(Context context){
        this.context = context;
        builder = new AlertDialog.Builder(context);
    }

    public void normalDialog() {
        builder.setMessage("目前尚無資料。");
        builder.setPositiveButton(R.string.confirm, null);
        builder.show();
    }

    public void showAddDirectionDialog(final String imei) {
        final View layout = LayoutInflater.from(context).inflate(R.layout.add_direction_layout, null);
        builder.setTitle("新增目的地")
                .setView(layout)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            String direction = ((EditText)layout.findViewById(R.id.edit_direction)).getText().toString();
                            new FireBaseModel(imei).saveData(direction);

                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    public void deleteDialog(final String imei, final  String key) {
        builder.setMessage("確定刪除？")
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new FireBaseModel(imei).deleteData(key);
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

}
