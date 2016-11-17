package com.jekyll.commo.demo.calendar.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by jie on 2016/11/17.
 */

public class DayOfWeek implements Parcelable {
    private String date;
    private Date currentDate;
    private boolean isSelected;
    private boolean isToDay;

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isToDay() {
        return isToDay;
    }

    public void setToDay(boolean toDay) {
        isToDay = toDay;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeLong(this.currentDate != null ? this.currentDate.getTime() : -1);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isToDay ? (byte) 1 : (byte) 0);
    }

    public DayOfWeek() {
    }

    protected DayOfWeek(Parcel in) {
        this.date = in.readString();
        long tmpCurrentDate = in.readLong();
        this.currentDate = tmpCurrentDate == -1 ? null : new Date(tmpCurrentDate);
        this.isSelected = in.readByte() != 0;
        this.isToDay = in.readByte() != 0;
    }

    public static final Parcelable.Creator<DayOfWeek> CREATOR = new Parcelable.Creator<DayOfWeek>() {
        @Override
        public DayOfWeek createFromParcel(Parcel source) {
            return new DayOfWeek(source);
        }

        @Override
        public DayOfWeek[] newArray(int size) {
            return new DayOfWeek[size];
        }
    };

    @Override
    public String toString() {
        return "DayOfWeek{" +
                "date='" + date + '\'' +
                ", currentDate=" + currentDate +
                ", isSelected=" + isSelected +
                ", isToDay=" + isToDay +
                '}';
    }
}
