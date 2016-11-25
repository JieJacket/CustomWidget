package com.jekyll.wu.widget.calendar.model;

import com.jekyll.wu.widget.calendar.util.CalendarUtils;

import org.junit.Test;

import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jie on 2016/11/25.
 */
public class DayModelTest {
    @Test
    public void compareTo() throws Exception {
        DayModel day = new DayModel();
        day.setDate(Calendar.getInstance().getTime());

        DayModel day1 = new DayModel();

        DayModel day2 = new DayModel();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        day2.setDate(c.getTime());

        List<DayModel> dayModels = new LinkedList<>();
        dayModels.add(day1);
        dayModels.add(day2);
        dayModels.add(day);
        System.out.println(day1);
        System.out.println(day2);
        System.out.println(day);

        System.out.println(Collections.max(dayModels));
        System.out.println(dayModels);
    }
    @Test
    public void getFirstValidDay(){
        PagerModel pagerModel = CalendarUtils.calculatorMonth(Calendar.getInstance());

        System.out.println(CalendarUtils.getFirstValidDay(pagerModel.week));

    }

}