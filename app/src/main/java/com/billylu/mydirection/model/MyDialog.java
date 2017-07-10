package com.billylu.mydirection.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.billylu.mydirection.R;

/**
 * Created by art-imac-02 on 2017/7/10.
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

    public void showAddDirectionDialog(final CallBack callBack) {
        final View layout = LayoutInflater.from(context).inflate(R.layout.add_direction_layout, null);
        builder.setTitle("新增目的地");
        builder.setView(layout);
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String direction = ((EditText)layout.findViewById(R.id.edit_direction)).getText().toString();
                Log.i(TAG, direction);
                DirectionBean bean = new DirectionBean();
                bean.setDirection(direction);
                MyApplication.directionBeanList.add(bean);
                Log.i(TAG, MyApplication.directionBeanList.size()+"");
                callBack.changed();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }


}
