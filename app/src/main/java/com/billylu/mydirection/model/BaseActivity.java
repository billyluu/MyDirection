package com.billylu.mydirection.model;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.billylu.mydirection.R;

/**
 * Created by BillyLu on 2017/7/24.
 */

public class BaseActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    protected void setToolbar(int toolBarID, boolean setNavigationIcon) {
        mToolbar = (Toolbar) findViewById(toolBarID);
        if (setNavigationIcon) {
            mToolbar.setNavigationIcon(R.drawable.btn_back_style);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BaseActivity.super.onBackPressed();
                }
            });
        }
    }

    protected void setToolbar(int toolBarID, boolean setNavigationIcon, int menuID) {
        mToolbar = (Toolbar) findViewById(toolBarID);
        if (setNavigationIcon) {
            mToolbar.setNavigationIcon(R.drawable.btn_back_style);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BaseActivity.super.onBackPressed();
                }
            });
        }
        mToolbar.inflateMenu(menuID);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                setMenuItemAction(item.getItemId());
                return true;
            }
        });


    }

    protected void setMenuAddButton(Boolean show){
        if (!show) {
            mToolbar.getMenu().getItem(0).setVisible(false);
        } else {
            return;
        }
    }

    protected void setMenuPoint(Boolean show){
        if (!show) {
            mToolbar.getMenu().getItem(1).setVisible(false);
            mToolbar.getMenu().getItem(2).setVisible(false);
        } else {
            return;
        }
    }
    
    protected void setMenuItemAction(int itemID){

    }
}
