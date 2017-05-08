package com.jekyll.common;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jekyll.wu.widget.FreeSnackBar;


/**
 * Created by jie on 2017/5/8.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        FrameLayout content = (FrameLayout) findViewById(android.R.id.content);
        if (content != null) {
            View top = findViewById(R.id.top_view);
            if (top == null) {
                top = new View(this);
                top.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
                content.addView(top, 0);
            }
            FreeSnackBar.make(top, "这是一个测试", FreeSnackBar.LENGTH_LONG)
//                    .setBackgroundColor(Color.parseColor("#123456"))
                    .setAction("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })
                    .setBackgroundColor(Color.parseColor("#123456"))
                    .bottom()
                    .show();
        }
    }
}
