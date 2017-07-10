package com.billylu.mydirection.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.billylu.mydirection.R;
import com.billylu.mydirection.model.DirectionBean;
import com.billylu.mydirection.model.MyApplication;

import java.util.List;

/**
 * Created by BillyLu on 2017/7/10.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private int layout;
    private List<DirectionBean> list;
    private Context mContext;

    public RecycleAdapter(Context context, int layout) {
        this.layout = layout;
        list = MyApplication.directionBeanList;
        mContext = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public Button send;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_direction);
            send = (Button) itemView.findViewById(R.id.btn_send);
        }
    }

    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(list.get(position).getDirection());
        holder.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dir = list.get(position).getDirection();
                Log.i("DIR", dir);
                startSearchDirection(dir);
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
        mContext.startActivity(intent);
    }
}
