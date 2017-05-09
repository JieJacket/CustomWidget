package com.jekyll.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by jiewu on 2017/5/9.
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Custom Widget");
    }

    public void calendar(View view) {
        startActivity(new Intent(this, CalendarActivity.class));
    }

    public void freeSnackBar(View view) {
        startActivity(new Intent(this, FreeSnackBarActivity.class));
    }

    public void calculator(View view) {
        startActivity(new Intent(this, CalculatorActivity.class));
    }
}
