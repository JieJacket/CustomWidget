package com.jekyll.commo.demo.calendar.util;

import com.jekyll.commo.demo.calendar.model.DayOfWeek;
import com.jekyll.commo.demo.calendar.model.WeekModel;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by jie on 2016/11/17.
 */
public class CalendarUtilsTest {
    @Test
    public void calculatorDate() throws Exception {
        CalendarUtils utils = new CalendarUtils();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, 7);
        WeekModel dayOfWeeks = utils.calculatorDate(calendar);
        for (DayOfWeek day : dayOfWeeks.getWeek()) {
            System.out.println(day);
        }
    }

    @Test
    public void getTheDateAfterWeek() throws Exception {
        CalendarUtils utils = new CalendarUtils();

        WeekModel dayOfWeeks = utils.getTheDateAfterWeek(Calendar.getInstance());
        for (DayOfWeek day : dayOfWeeks.getWeek()) {
            System.out.println(day);
        }
    }

    @Test
    public void getTheDateAfterWeeks() throws Exception {
        CalendarUtils utils = new CalendarUtils();
        List<WeekModel> theAfterWeeks = utils.getTheAfterWeeks(Calendar.getInstance(), 10);
        for (int i = 0; i < theAfterWeeks.size(); i++) {
            System.out.println(i);
            WeekModel weekModel = theAfterWeeks.get(i);
            for (DayOfWeek day : weekModel.getWeek()) {
                System.out.println(day);
            }
        }
    }

    @Test
    public void getTheDateBeforeWeeks() throws Exception {
        CalendarUtils utils = new CalendarUtils();
        List<WeekModel> theAfterWeeks = utils.getTheDateBeforeWeeks(Calendar.getInstance(), 10);
        for (int i = 0; i < theAfterWeeks.size(); i++) {
            System.out.println(i);
            WeekModel weekModel = theAfterWeeks.get(i);
            for (DayOfWeek day : weekModel.getWeek()) {
                System.out.println(day);
            }
        }
    }

    @Test
    public void getTheDateBeforeWeek() throws Exception {
        CalendarUtils utils = new CalendarUtils();
        WeekModel dayOfWeeks = utils.getTheDateBeforeWeek(Calendar.getInstance());
        for (DayOfWeek day : dayOfWeeks.getWeek()) {
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

}