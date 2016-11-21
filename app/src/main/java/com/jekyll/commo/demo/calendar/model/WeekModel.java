package com.jekyll.commo.demo.calendar.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by jie on 2016/11/17.
 */

public class WeekModel implements Parcelable {
    private List<DayOfWeek> week;

    public List<DayOfWeek> getWeek() {
        return week;
    }

    public void setWeek(List<DayOfWeek> week) {
        this.week = week;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.week);
    }

    public WeekModel() {
    }

    protected WeekModel(Parcel in) {
        this.week = in.createTypedArrayList(DayOfWeek.CREATOR);
    }

    public static final Creator<WeekModel> CREATOR = new Creator<WeekModel>() {
        @Override
        public WeekModel createFromParcel(Parcel source) {
            return new WeekModel(source);
        }

        @Override
        public WeekModel[] newArray(int size) {
            return new WeekModel[size];
        }
    };

    /**
     * 判断是否包含本周是否包含有此日期
     * @param dayOfWeek
     * @return
     */
    public boolean contain(DayOfWeek dayOfWeek) {
        if (this.getWeek() == null || dayOfWeek == null) {
            return false;
        }
        List<DayOfWeek> weeks = this.getWeek();
        for (DayOfWeek day : weeks) {
            if (dayOfWeek.equals(day)) {
                return true;
            }
        }
        return false;
    }

    public boolean contain(Calendar calendar){
        DayOfWeek day = new DayOfWeek();
        day.setDate(calendar.getTime());
        return contain(day);
    }

    public boolean contain(Date date){
        DayOfWeek day = new DayOfWeek();
        day.setDate(date);
        return contain(day);
    }
}
