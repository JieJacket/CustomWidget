package com.jekyll.wu.widget.calendar.util;


import com.jekyll.wu.widget.calendar.model.DayModel;
import com.jekyll.wu.widget.calendar.model.PagerModel;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by jie on 2016/11/17.
 */

public class CalendarUtils {
    /**
     * 计算当前时间一周内的时间集合
     *
     * @param start
     * @return
     */
    public static PagerModel calculatorWeek(Calendar start) {
        if (start == null) {
            return null;
        }
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(start.getTime());
        int dayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK);
        List<DayModel> dayOfWeeks = new LinkedList<>();
        currentDate.add(Calendar.DAY_OF_WEEK, -dayOfWeek + 1);
        for (int i = 0; i < 7; i++) {
            currentDate.add(Calendar.DAY_OF_WEEK, 1);
            dayOfWeeks.add(dateToDayModel(currentDate.getTime()));
        }
        PagerModel weekModel = new PagerModel();
        weekModel.week = dayOfWeeks;
        return weekModel;
    }

    /**
     * 计算当前时间下一周的时间集合
     *
     * @param calendar
     * @return
     */
    public static PagerModel getTheDateAfterWeek(Calendar calendar) {
        PagerModel current = calculatorWeek(calendar);
        if (current != null && current.week != null) {
            List<DayModel> afterWeek = new LinkedList<>();
            for (DayModel day : current.week) {
                Calendar c = Calendar.getInstance();
                c.setTime(day.getDate());
                c.add(Calendar.DAY_OF_WEEK, 7);
                afterWeek.add(dateToDayModel(c.getTime()));
            }
            PagerModel weekModel = new PagerModel();
            weekModel.week = afterWeek;
            return weekModel;
        }
        return null;
    }

    /**
     * 计算当前时间前一周的时间集合
     *
     * @param calendar
     * @return
     */
    public static PagerModel getTheDateBeforeWeek(Calendar calendar) {
        PagerModel current = calculatorWeek(calendar);
        if (current != null && current.week != null) {
            List<DayModel> beforeWeek = new LinkedList<>();
            for (DayModel day : current.week) {
                Calendar c = Calendar.getInstance();
                c.setTime(day.getDate());
                c.add(Calendar.DAY_OF_WEEK, -7);
                beforeWeek.add(dateToDayModel(c.getTime()));
            }
            PagerModel weekModel = new PagerModel();
            weekModel.week = beforeWeek;
            return weekModel;
        }
        return null;
    }

    /**
     * 获取相对于当前一周后指定数量的时间集合
     *
     * @param calendar
     * @param count
     * @return
     */
    public static List<PagerModel> getTheAfterWeeks(Calendar calendar, int count) {
        List<PagerModel> weekModels = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            calendar.add(Calendar.DAY_OF_WEEK, 7);
            PagerModel theDateAfterWeek = calculatorWeek(calendar);
            weekModels.add(theDateAfterWeek);
        }
        return weekModels;
    }

    /**
     * 获取相对于当前一周前指定数量的时间集合
     *
     * @param calendar
     * @param count
     * @return
     */
    public static List<PagerModel> getTheDateBeforeWeeks(Calendar calendar, int count) {
        List<PagerModel> weekModels = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            calendar.add(Calendar.DAY_OF_WEEK, -7);
            PagerModel theDateAfterWeek = calculatorWeek(calendar);
            weekModels.add(theDateAfterWeek);
        }
        if (!weekModels.isEmpty()) {
            Collections.reverse(weekModels);
        }
        return weekModels;
    }


    public static PagerModel calculatorMonth(Calendar start) {
        if (start == null) {
            return null;
        }
        PagerModel model = new PagerModel();

        Calendar instance = Calendar.getInstance();
        instance.setTime(start.getTime());

        int day = instance.get(Calendar.DAY_OF_MONTH);
        instance.add(Calendar.DAY_OF_MONTH, -day + 1);

        int month = instance.get(Calendar.MONTH);
        int year = instance.get(Calendar.YEAR);
        int maxDate = getDaysByYearMonth(year, month);
        List<DayModel> days = new LinkedList<>();
        while (maxDate > 0) {
            DayModel dayOfWeek = new DayModel();
            dayOfWeek.setDate(instance.getTime());
            instance.add(Calendar.DAY_OF_MONTH, 1);
            days.add(dayOfWeek);
            maxDate--;
        }

        formatCurMonth(days);

        model.week = days;

        return model;
    }

    private static void formatCurMonth(List<DayModel> days) {
        DayModel dayModel = days.get(0);
        Calendar c = Calendar.getInstance();
        c.setTime(dayModel.getDate());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;//0-6：周日-周六

        if (dayOfWeek == 1){//第一天是周一不用处理
            return;
        }else if (dayOfWeek == 0){
            dayOfWeek = 7;
        }
        while (dayOfWeek > 1) {
            days.add(0, new DayModel());
            dayOfWeek--;
        }

    }

    /**
     * 根据年月获取当前月份的天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }

    /**
     * 普通日期转为DayOfWeek对象
     *
     * @param date
     * @return
     */
    public static DayModel dateToDayModel(Date date) {
        if (date == null) {
            return null;
        }
        DayModel dayOfWeek = new DayModel();
        dayOfWeek.setDate(date);
        return dayOfWeek;
    }

    public static DayModel getFirstValidDay(List<DayModel> models){
        if (models == null || models.isEmpty()){
            return null;
        }
        DayModel dayModel = new DayModel();

        for (DayModel day:models){
            if (day.getDate() == null){
                continue;
            }
            dayModel = day;
            break;
        }
        return dayModel;
    }

}
