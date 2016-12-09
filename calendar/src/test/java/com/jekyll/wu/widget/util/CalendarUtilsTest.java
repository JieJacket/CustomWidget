package com.jekyll.wu.widget.util;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by jie on 2016/12/7.
 */
public class CalendarUtilsTest {
    @Test
    public void calculatorWeek() throws Exception {
        Calendar start = Calendar.getInstance();
        int firstDayOfWeek = start.getFirstDayOfWeek();
        System.out.println(firstDayOfWeek);
    }

    @Test
    public void getTheDateAfterWeek() throws Exception {

    }

    @Test
    public void getTheDateBeforeWeek() throws Exception {

    }

}