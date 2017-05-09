package com.jekyll.common;

import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jekyll.wu.widget.FreeSnackBar;

/**
 * Created by jiewu on 2017/5/9.
 */

public class FreeSnackBarActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.free_snack_bar);
        getSupportActionBar().hide();
    }

    public void top(View view) {
        FreeSnackBar.make(view, "这是一个显示在顶部的SnackBar", FreeSnackBar.LENGTH_LONG)
                .top()
                .setBackgroundColor(Color.parseColor("#123456"))
                .setAction("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "呵呵", Toast.LENGTH_SHORT).show();
                    }
                })
                .setCallback(new FreeSnackBar.Callback() {
                    @Override
                    public void onDismissed(FreeSnackBar snackbar, @DismissEvent int event) {
                        super.onDismissed(snackbar, event);
                        setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
                    }

                    @Override
                    public void onShown(FreeSnackBar snackbar) {
                        super.onShown(snackbar);
                        setStatusBarColor(Color.parseColor("#123456"));
                    }
                })
                .show();
    }

    public void bottom(View view) {
        FreeSnackBar.make(view, "这是一个显示在底部的SnackBar,自定义背景以及右侧字体颜色", FreeSnackBar.LENGTH_LONG)
                .bottom()
                .setBackgroundColor(Color.parseColor("#876543"))
                .setActionTextColor(Color.BLACK)
                .setAction("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "呵呵", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (getWindow() != null) {
                getWindow().setStatusBarColor(color);
            }
        }
    }


}
