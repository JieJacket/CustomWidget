package com.jekyll.wu.widget.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by jie on 2016/11/17.
 */

public class PagerModel implements Parcelable {
    public List<DayModel> week;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.week);
    }

    public PagerModel() {
    }

    protected PagerModel(Parcel in) {
        this.week = in.createTypedArrayList(DayModel.CREATOR);
    }

    public static final Creator<PagerModel> CREATOR = new Creator<PagerModel>() {
        @Override
        public PagerModel createFromParcel(Parcel source) {
            return new PagerModel(source);
        }

        @Override
        public PagerModel[] newArray(int size) {
            return new PagerModel[size];
        }
    };

    /**
     * 判断是否包含本周是否包含有此日期
     * @param dayOfWeek
     * @return
     */
    public boolean contain(DayModel dayOfWeek) {
        if (week == null || dayOfWeek == null) {
            return false;
        }
        List<DayModel> weeks = week;
        for (DayModel day : weeks) {
            if (dayOfWeek.equals(day)) {
                return true;
            }
        }
        return false;
    }

    public boolean contain(Calendar calendar){
        DayModel day = new DayModel();
        day.setDate(calendar.getTime());
        return contain(day);
    }

    public boolean contain(Date date){
        DayModel day = new DayModel();
        day.setDate(date);
        return contain(day);
    }

    @Override
    public String toString() {
        return "PagerModel{" +
                "week=" + week +
                '}';
    }
}
