package com.billylu.mydirection;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by billylu on 2017/7/20.
 */

public class BaseActivity extends AppCompatActivity {
    private Toolbar mToorbar;

    protected void setToorbar(int toolBarID, boolean setNavigationIcon){
        mToorbar = (Toolbar) findViewById(toolBarID);
        mToorbar.setTitle(R.string.app_name01);
        mToorbar.setTitleTextColor(Color.WHITE);
        mToorbar.inflateMenu(R.menu.toolbar_menu);
        mToorbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_add:
                        break;
                    case R.id.menu_setting:
                        break;
                    case R.id.menu_exit:
                        break;
                }
                return true;
            }
        });
        if (setNavigationIcon) {
            mToorbar.setNavigationIcon(R.drawable.btn_back);
        }
    }

















}
