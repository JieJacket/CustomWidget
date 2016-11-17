package com.jekyll.commo.demo.calendar.model;

import android.os.Parcel;
import android.os.Parcelable;

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
}
