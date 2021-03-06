package com.jekyll.commo.demo.calendar.util;


import com.jekyll.wu.widget.model.DayModel;
import com.jekyll.wu.widget.model.PagerModel;
import com.jekyll.wu.widget.util.CalendarUtils;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by jie on 2016/11/17.
 */
public class CalendarUtilsTest {
    @Test
    public void calculatorDate() throws Exception {
        CalendarUtils utils = new CalendarUtils();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, 7);
        PagerModel dayOfWeeks = utils.calculatorWeek(calendar);
        for (DayModel day : dayOfWeeks.week) {
            System.out.println(day);
        }
    }

    @Test
    public void getTheDateAfterWeek() throws Exception {
        CalendarUtils utils = new CalendarUtils();

        PagerModel dayOfWeeks = utils.getTheDateAfterWeek(Calendar.getInstance());
        for (DayModel day : dayOfWeeks.week) {
            System.out.println(day);
        }
    }

    @Test
    public void getTheDateAfterWeeks() throws Exception {
        CalendarUtils utils = new CalendarUtils();
        List<PagerModel> theAfterWeeks = utils.getTheAfterWeeks(Calendar.getInstance(), 10);
        for (int i = 0; i < theAfterWeeks.size(); i++) {
            System.out.println(i);
            PagerModel weekModel = theAfterWeeks.get(i);
            for (DayModel day : weekModel.week) {
                System.out.println(day);
            }
        }
    }

    @Test
    public void getTheDateBeforeWeeks() throws Exception {
        CalendarUtils utils = new CalendarUtils();
        List<PagerModel> theAfterWeeks = utils.getTheDateBeforeWeeks(Calendar.getInstance(), 10);
        for (int i = 0; i < theAfterWeeks.size(); i++) {
            System.out.println(i);
            PagerModel weekModel = theAfterWeeks.get(i);
            for (DayModel day : weekModel.week) {
                System.out.println(day);
            }
        }
    }

    @Test
    public void getTheDateBeforeWeek() throws Exception {
        CalendarUtils utils = new CalendarUtils();
        PagerModel dayOfWeeks = utils.getTheDateBeforeWeek(Calendar.getInstance());
        for (DayModel day : dayOfWeeks.week) {
            System.out.println(day);
        }
    }

    @Test
    public void dateToDayModel() throws Exception {
        CalendarUtils utils = new CalendarUtils();
        System.out.println(utils.dateToDayModel(new Date()));
    }

    @Test
    public void weekContainTheDay() throws Exception {
    }

    @Test
    public void calculatorMonth() {
        CalendarUtils utils = new CalendarUtils();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 6);
        System.out.println(utils.calculatorMonth(calendar));
    }

    @Test
    public void setDaysEnable() throws ParseException {
        Calendar start = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        Date startDate = format.parse("20161201");
        start.setTime(startDate);
        List<PagerModel> models = CalendarUtils.getTheAfterWeeks(start, 10);

        CalendarUtils.setUnableAfterToday(Calendar.getInstance(), models);
        System.out.println(models.toString());
    }

}