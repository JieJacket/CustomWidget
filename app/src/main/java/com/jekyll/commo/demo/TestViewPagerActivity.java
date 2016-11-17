package com.jekyll.commo.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.jekyll.commo.demo.calendar.adapter.CalendarPagerAdapter;
import com.jekyll.commo.demo.calendar.model.WeekModel;
import com.jekyll.commo.demo.calendar.util.CalendarUtils;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jie on 2016/11/17.
 */

public class TestViewPagerActivity extends AppCompatActivity {

    private List<WeekModel> models;
    private ViewPager vp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_vp_layout);
        vp = (ViewPager) findViewById(R.id.vp);

        models = new LinkedList<>();
        models.addAll(new CalendarUtils().getTheAfterWeeks(Calendar.getInstance(),10));
        CalendarPagerAdapter adapter = new CalendarPagerAdapter(getSupportFragmentManager(),models);
        vp.setAdapter(adapter);
    }
}
