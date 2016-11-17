package com.jekyll.commo.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.jekyll.commo.demo.calendar.model.WeekModel;
import com.jekyll.commo.demo.calendar.util.CalendarUtils;
import com.jekyll.commo.demo.calendar.widget.WeekView;

import java.util.Calendar;

/**
 * Created by jie on 2016/11/17.
 */

public class TestWeekViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WeekView weekView = new WeekView(this);
        weekView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        CalendarUtils utils = new CalendarUtils();
        WeekModel weekModel = utils.calculatorDate(Calendar.getInstance());
        weekView.setWeekModel(weekModel);
        setContentView(weekView);
    }
}
