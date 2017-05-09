package com.jekyll.common;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by jiewu on 2017/5/9.
 */

public class CalculatorActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        getSupportActionBar().setTitle("Calculator");
    }
}
