package com.jekyll.commo.demo.calendar.widget;

import android.content.Context;
import android.support.v4.widget.TextViewCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jekyll.commo.demo.R;
import com.jekyll.commo.demo.calendar.model.DayOfWeek;
import com.jekyll.commo.demo.calendar.util.ViewUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jie on 2016/11/17.
 */

public class DayView extends LinearLayout implements View.OnClickListener {
    private TextView dayText;
    private TextView monthText;
    private boolean isSelected;

    private DayOfWeek dayOfWeek;

    private static final String[] MONTHS = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};

    public DayView(Context context) {
        this(context, null);
    }

    public DayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        addDayView(context);
        addMonthView(context);
//        dayText.setText("1");
//        monthText.setText(MONTHS[8]);
        resetViewStatus(isSelected);
//        setOnClickListener(this);
    }

    private void addDayView(Context context) {
        dayText = new TextView(context);
        LayoutParams dayParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dayParams.gravity = Gravity.CENTER_HORIZONTAL;
        dayText.setLayoutParams(dayParams);
        dayText.setGravity(Gravity.CENTER);
        TextViewCompat.setTextAppearance(dayText, R.style.DefaultDayViewStyle);
        dayText.setBackgroundResource(R.drawable.day_view_content_background);
        dayParams.bottomMargin = ViewUtils.dp2px(context, 8);
        addView(dayText);
    }

    private void addMonthView(Context context) {
        monthText = new TextView(context);
        LayoutParams monthParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        monthParams.gravity = Gravity.CENTER_HORIZONTAL;
        monthText.setLayoutParams(monthParams);
        addView(monthText);
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        if (dayOfWeek != null) {
            dayText.setText(getDayLabel(dayOfWeek.getCurrentDate()));
            monthText.setText(getMonthLabel(dayOfWeek.getCurrentDate()));
            setSelected(dayOfWeek.isSelected());
        } else {
            dayText.setText(null);
            monthText.setText(null);
        }
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setSelected(boolean selected) {
        isSelected = selected;
        resetViewStatus(isSelected);
    }

    private String getDayLabel(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.format(Locale.getDefault(), "%d", calendar.get(Calendar.DAY_OF_MONTH));
    }

    private String getMonthLabel(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return MONTHS[calendar.get(Calendar.MONTH)];
    }

    @Override
    public void onClick(View v) {
        isSelected = !isSelected;
        resetViewStatus(isSelected);
    }

    private void resetViewStatus(boolean isSelected) {
        if (!isSelected) {
            TextViewCompat.setTextAppearance(dayText, R.style.DefaultDayViewStyle);
            dayText.setBackgroundResource(R.drawable.day_view_content_background);
        } else {
            TextViewCompat.setTextAppearance(dayText, R.style.SelectedDayViewStyle);
            dayText.setBackgroundResource(R.drawable.selected_day_view_content_background);
        }
    }
}
