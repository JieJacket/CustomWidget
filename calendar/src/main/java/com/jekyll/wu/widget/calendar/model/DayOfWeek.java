package com.jekyll.wu.widget.calendar.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jie on 2016/11/17.
 */

public class DayOfWeek implements Parcelable, Comparable<DayOfWeek> {
    private Date date;
    private boolean isSelected;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    public DayOfWeek() {
    }

    protected DayOfWeek(Parcel in) {
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.isSelected = in.readByte() != 0;
    }

    public static final Creator<DayOfWeek> CREATOR = new Creator<DayOfWeek>() {
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
                "date=" + date +
                ", isSelected=" + isSelected +
                '}';
    }


    @Override
    public int compareTo(DayOfWeek o) {
        return o.getDate().getMonth();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DayOfWeek) {
            DayOfWeek dayOfWeek = (DayOfWeek) obj;
            Date date = dayOfWeek.getDate();
            if (date == null || this.date == null){
                return false;
            }
            DateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            return format.format(this.date).equals(format.format(date));
        }

        return super.equals(obj);
    }
}