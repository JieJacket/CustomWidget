package com.jekyll.wu.widget.calendar.listener;

import android.view.View;

import com.jekyll.wu.widget.calendar.model.DayModel;


/**
 * Created by jie on 2016/11/17.
 */

public interface OnDateCheckedListener {
    void onDateClicked(View view, DayModel day);
}
