package com.jekyll.commo.demo.calendar.listener;

import android.view.View;

import com.jekyll.commo.demo.calendar.model.DayOfWeek;

import java.util.Set;

/**
 * Created by jie on 2016/11/17.
 */

public interface OnDateCheckedListener {
    void onDateClicked(View view, DayOfWeek day);
}
