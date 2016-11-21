package com.jekyll.common.calendar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jekyll.common.calendar.model.WeekModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jie on 2016/11/17.
 */

public class WeekView extends LinearLayout {

    private WeekModel weekModel = new WeekModel();

    private final static int VIEW_COUNT = 7;

    private List<DayView> dayOfWeeks;

    public WeekView(Context context) {
        this(context, null);
    }

    public WeekView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeekView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        dayOfWeeks = new LinkedList<>();
        for (int i = 0; i < VIEW_COUNT; i++) {
            DayView dayView = new DayView(context);
            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            dayView.setLayoutParams(params);
            addView(dayView);
            dayOfWeeks.add(dayView);
        }

    }

    public WeekModel getWeekModel() {
        return weekModel;
    }

    public void setWeekModel(WeekModel weekModel) {
        this.weekModel = weekModel;
        if (weekModel != null && weekModel.getWeek() != null && dayOfWeeks.size() == weekModel.getWeek().size()) {
            for (int i = 0; i < dayOfWeeks.size(); i++) {
                DayView dayView = dayOfWeeks.get(i);
                dayView.setDayOfWeek(weekModel.getWeek().get(i));
            }
        }
    }


}
