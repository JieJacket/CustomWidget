package com.jekyll.wu.widget.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jie on 2016/11/17.
 */

public class DayModel implements Parcelable, Comparable<DayModel> {
    private Date date;
    private boolean isSelected;
    private boolean isEnable = true;


    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

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

    public DayModel() {
    }

    @Override
    public String toString() {
        return "DayModel{" +
                "date=" + date +
                ", isSelected=" + isSelected +
                ", isEnable=" + isEnable +
                '}';
    }

    @Override
    public int compareTo(DayModel o) {
        int compared, cur;
        if (o == null || o.getDate() == null) {
            compared = -1;
        } else {
            compared = o.getDate().getYear() * 100 + o.getDate().getMonth();
        }
        if (this.date == null) {
            cur = 0;
        } else {
            cur = this.date.getYear() * 100 + this.date.getMonth();
        }
        return cur - compared;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DayModel) {
            DayModel dayOfWeek = (DayModel) obj;
            Date date = dayOfWeek.getDate();
            if (date == null || this.date == null) {
                return false;
            }
            DateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            return format.format(this.date).equals(format.format(date));
        }

        return super.equals(obj);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isEnable ? (byte) 1 : (byte) 0);
    }

    protected DayModel(Parcel in) {
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.isSelected = in.readByte() != 0;
        this.isEnable = in.readByte() != 0;
    }

    public static final Creator<DayModel> CREATOR = new Creator<DayModel>() {
        @Override
        public DayModel createFromParcel(Parcel source) {
            return new DayModel(source);
        }

        @Override
        public DayModel[] newArray(int size) {
            return new DayModel[size];
        }
    };
}
