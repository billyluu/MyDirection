package com.billylu.mydirection;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.billylu.mydirection.model.CallBack;
import com.billylu.mydirection.model.DirectionBean;
import com.billylu.mydirection.model.MyApplication;
import com.billylu.mydirection.model.MyDialog;

import java.util.List;

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
        adapter = new RecycleAdapter(R.layout.recycle_view_item);
        recyclerView.setAdapter(adapter);
    }

    class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
        private int layout;
        private List<DirectionBean> list;

        public RecycleAdapter(int layout) {
            this.layout = layout;
            list = MyApplication.directionBeanList;

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
            holder.textView.setText(list.get(position).getDirection());
            holder.send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String dir = list.get(position).getDirection();
                    Log.i("DIR", dir);
                    startSearchDirection(dir);
                }
            });

            holder.viewitem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    new MyDialog(MainActivity.this).deleteDialog(position, new CallBack() {
                        @Override
                        public void changed() {
                            setRecyclerView();
                        }
                    });
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
}
