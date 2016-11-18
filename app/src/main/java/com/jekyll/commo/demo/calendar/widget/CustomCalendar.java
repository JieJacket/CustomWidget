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
import com.jekyll.commo.demo.calendar.model.DayOfWeek;
import com.jekyll.commo.demo.calendar.model.WeekModel;
import com.jekyll.commo.demo.calendar.util.ViewUtils;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jie on 2016/11/17.
 */

public class CustomCalendar extends LinearLayout implements ViewPager.OnPageChangeListener {

    private static final String[] WEEK = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    private static final String[] MONTHS = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};

    private LinearLayout topContainer;//顶部存放星期的容器
    private ViewPager calendarContainer;
    private CalendarPagerAdapter pagerAdapter;
    private List<WeekModel> weekModels;

    private TextView tvDate;

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
        tvDate = new TextView(context);
        LayoutParams dateParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewUtils.dp2px(context, 36));
        tvDate.setLayoutParams(dateParams);
        tvDate.setTextColor(Color.parseColor("#cccccc"));
        tvDate.setGravity(Gravity.CENTER);
        tvDate.setGravity(Gravity.CENTER);
        addView(tvDate);


        topContainer = new LinearLayout(context);
        topContainer.setOrientation(HORIZONTAL);
        topContainer.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        for (String day : WEEK) {
            TextView tv = new TextView(context);
            LayoutParams lp = new LayoutParams(0, ViewUtils.dp2px(context, 36));
            lp.weight = 1;
            tv.setLayoutParams(lp);
            tv.setText(day);
            tv.setTextColor(Color.parseColor("#cccccc"));
            tv.setGravity(Gravity.CENTER);
            topContainer.addView(tv);
        }
        addView(topContainer);
    }

    private void addCalendar(Context context) {
        calendarContainer = new ViewPager(context);
        calendarContainer.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        calendarContainer.addOnPageChangeListener(this);
        calendarContainer.setId(R.id.calendar_vp);
        addView(calendarContainer);
    }

    public void setWeekModels(FragmentManager fm, List<WeekModel> weekModels) {
        this.weekModels = weekModels;
        pagerAdapter = new CalendarPagerAdapter(fm, weekModels);
        calendarContainer.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();
    }

    public void setCurrentWeek(int pager) {
        if (calendarContainer != null) {
            calendarContainer.setCurrentItem(pager);
        }
    }

    public void setSelectedDates(Date... dates) {

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        checkCurMonth(weekModels.get(position));
    }

    private void checkCurMonth(WeekModel model) {
        List<DayOfWeek> week = model.getWeek();
        DayOfWeek dofWeek = Collections.max(week);
        tvDate.setText(MONTHS[dofWeek.getDate().getMonth()]);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 获取选中的日期
     * @return
     */
    public List<DayOfWeek> getSelections() {
        List<DayOfWeek> select = new LinkedList<>();
        if (weekModels != null) {
            for (WeekModel week : weekModels) {
                for (DayOfWeek day : week.getWeek()) {
                    if (day.isSelected()) {
                        select.add(day);
                    }
                }
            }
        }
        return select;
    }
}
