package com.jekyll.commo.demo.calendar.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jekyll.commo.demo.R;
import com.jekyll.commo.demo.calendar.adapter.CalendarPagerAdapter;
import com.jekyll.commo.demo.calendar.model.WeekModel;
import com.jekyll.commo.demo.calendar.util.ViewUtils;

import java.util.List;

/**
 * Created by jie on 2016/11/17.
 */

public class CustomCalendar extends LinearLayout implements ViewPager.OnPageChangeListener {

    private static final String[] WEEK = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    private LinearLayout topContainer;//顶部存放星期的容器
    private ViewPager calendarContainer;
    private CalendarPagerAdapter pagerAdapter;
    private List<WeekModel> weekModels;


    public CustomCalendar(Context context) {
        this(context, null);
    }

    public CustomCalendar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        addTopLabel(context);
        addCalendar(context);
    }

    private void addTopLabel(Context context) {
        topContainer = new LinearLayout(context);
        topContainer.setOrientation(HORIZONTAL);
        topContainer.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        for (String day : WEEK) {
            TextView tv = new TextView(context);
            LayoutParams lp = new LayoutParams(0, ViewUtils.dp2px(context, 36));
            lp.weight = 1;
            tv.setLayoutParams(lp);
            tv.setText(day);
            tv.setTextColor(Color.parseColor("#666666"));
            tv.setGravity(Gravity.CENTER);
            topContainer.addView(tv);
        }
        addView(topContainer);
    }

    private void addCalendar(Context context) {
        calendarContainer = new ViewPager(context);
        calendarContainer.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        calendarContainer.addOnPageChangeListener(this);
        calendarContainer.setId(R.id.caleandar_vp);
        addView(calendarContainer);
    }

    public void setWeekModels(FragmentManager fm, List<WeekModel> weekModels) {
        this.weekModels = weekModels;
        pagerAdapter = new CalendarPagerAdapter(fm, weekModels);
        calendarContainer.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();
    }

    public void setCurrentWeek(int pager){
        if (calendarContainer != null){
            calendarContainer.setCurrentItem(pager);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
