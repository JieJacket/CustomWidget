package com.jekyll.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jekyll.wu.widget.CustomCalendar;
import com.jekyll.wu.widget.listener.OnDateCheckedListener;
import com.jekyll.wu.widget.model.DayModel;
import com.jekyll.wu.widget.model.PagerModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by jie on 2016/11/25.
 */

public class CalendarFragment extends Fragment implements OnDateCheckedListener {

    private CustomCalendar customCalendar;
    private int style;
    private List<PagerModel> models;


    public static CalendarFragment newInstance(@CustomCalendar.Style int style) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle b = new Bundle();
        b.putInt("Style", style);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        style = getArguments().getInt("Style", CustomCalendar.WEEK_STYLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_calendar, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        try {
            customCalendar = (CustomCalendar) view.findViewById(R.id.cc_test);
            customCalendar.setDateFormat(new SimpleDateFormat("yyyy年MM月", Locale.getDefault()));
            Calendar start = Calendar.getInstance();
            DateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            Date startDate = format.parse("20151001");
            start.setTime(startDate);
            Calendar end = Calendar.getInstance();
            if (style == CustomCalendar.WEEK_STYLE) {
                customCalendar.setDateTextDefaultRes(R.drawable.date_text_default);
                customCalendar.setDateTextSelectedRes(R.drawable.date_text_selected);
                customCalendar.setDateTextAppearance(R.style.MyDefaultTextAppearance);
                customCalendar.setDateCheckedTextAppearance(R.style.MyCheckedTextAppearance);
                customCalendar.setDateCheckedBackgroundRes(R.drawable.date_checked_shape);
            }
            customCalendar.setCalendarStyle(style);
            models = new LinkedList<>();
            models = customCalendar.setCalendarRange(start, end);//设置日期的开始结束位置
            customCalendar.setPagerModels(getChildFragmentManager(), models);
            customCalendar.setCurrentWeek(models.size() - 1);//设置日期到最后一页

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDateClicked(View view, DayModel day) {
        Snackbar.make(customCalendar, String.format(Locale.getDefault(), "%s日期%s", day.isSelected() ? "选中" : "取消", day.getDate()), Snackbar.LENGTH_SHORT).show();
    }

    public List<DayModel> getSelections() {
        if (customCalendar != null) {
            return customCalendar.getSelections();
        }
        return null;
    }
}
