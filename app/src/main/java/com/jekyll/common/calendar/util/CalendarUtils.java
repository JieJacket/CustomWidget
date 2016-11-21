package com.jekyll.common.calendar.util;

import com.jekyll.common.calendar.model.DayOfWeek;
import com.jekyll.common.calendar.model.WeekModel;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jie on 2016/11/17.
 */

public class CalendarUtils {
    /**
     * 计算当前时间一周内的时间集合
     *
     * @param calendar
     * @return
     */
    public WeekModel calculatorDate(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(calendar.getTime());
        int dayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK);
        List<DayOfWeek> dayOfWeeks = new LinkedList<>();
        currentDate.add(Calendar.DAY_OF_WEEK, -dayOfWeek + 1);
        for (int i = 0; i < 7; i++) {
            currentDate.add(Calendar.DAY_OF_WEEK, 1);
            dayOfWeeks.add(dateToDayModel(currentDate.getTime()));
        }
        WeekModel weekModel = new WeekModel();
        weekModel.setWeek(dayOfWeeks);
        return weekModel;
    }

    /**
     * 计算当前时间下一周的时间集合
     *
     * @param calendar
     * @return
     */
    public WeekModel getTheDateAfterWeek(Calendar calendar) {
        WeekModel current = calculatorDate(calendar);
        if (current != null && current.getWeek() != null) {
            List<DayOfWeek> afterWeek = new LinkedList<>();
            for (DayOfWeek day : current.getWeek()) {
                Calendar c = Calendar.getInstance();
                c.setTime(day.getDate());
                c.add(Calendar.DAY_OF_WEEK, 7);
                afterWeek.add(dateToDayModel(c.getTime()));
            }
            WeekModel weekModel = new WeekModel();
            weekModel.setWeek(afterWeek);
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
    public WeekModel getTheDateBeforeWeek(Calendar calendar) {
        WeekModel current = calculatorDate(calendar);
        if (current != null && current.getWeek() != null) {
            List<DayOfWeek> beforeWeek = new LinkedList<>();
            for (DayOfWeek day : current.getWeek()) {
                Calendar c = Calendar.getInstance();
                c.setTime(day.getDate());
                c.add(Calendar.DAY_OF_WEEK, -7);
                beforeWeek.add(dateToDayModel(c.getTime()));
            }
            WeekModel weekModel = new WeekModel();
            weekModel.setWeek(beforeWeek);
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
    public List<WeekModel> getTheAfterWeeks(Calendar calendar, int count) {
        List<WeekModel> weekModels = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            calendar.add(Calendar.DAY_OF_WEEK, 7);
            WeekModel theDateAfterWeek = calculatorDate(calendar);
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
    public List<WeekModel> getTheDateBeforeWeeks(Calendar calendar, int count) {
        List<WeekModel> weekModels = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            calendar.add(Calendar.DAY_OF_WEEK, -7);
            WeekModel theDateAfterWeek = calculatorDate(calendar);
            weekModels.add(theDateAfterWeek);
        }
        if (!weekModels.isEmpty()) {
            Collections.reverse(weekModels);
        }
        return weekModels;
    }

    /**
     * 普通日期转为DayOfWeek对象
     *
     * @param date
     * @return
     */
    public DayOfWeek dateToDayModel(Date date) {
        if (date == null) {
            return null;
        }
        DayOfWeek dayOfWeek = new DayOfWeek();
        dayOfWeek.setDate(date);
        return dayOfWeek;
    }

}
