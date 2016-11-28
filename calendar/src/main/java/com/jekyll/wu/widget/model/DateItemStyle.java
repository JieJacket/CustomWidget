package com.jekyll.wu.widget.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jie on 2016/11/28.
 */

public class DateItemStyle implements Parcelable {

    private int dateTextDefaultRes;
    private int dateTextCheckedRes;
    private int dateDefaultRes;
    private int dateCheckedRes;
    private int dateTextAppearance;
    private int dateTextCheckedAppearance;

    public int getDateTextDefaultRes() {
        return dateTextDefaultRes;
    }

    public void setDateTextDefaultRes(int dateTextDefaultRes) {
        this.dateTextDefaultRes = dateTextDefaultRes;
    }

    public int getDateTextCheckedRes() {
        return dateTextCheckedRes;
    }

    public void setDateTextCheckedRes(int dateTextCheckedRes) {
        this.dateTextCheckedRes = dateTextCheckedRes;
    }

    public int getDateDefaultRes() {
        return dateDefaultRes;
    }

    public void setDateDefaultRes(int dateDefaultRes) {
        this.dateDefaultRes = dateDefaultRes;
    }

    public int getDateCheckedRes() {
        return dateCheckedRes;
    }

    public void setDateCheckedRes(int dateCheckedRes) {
        this.dateCheckedRes = dateCheckedRes;
    }

    public int getDateTextAppearance() {
        return dateTextAppearance;
    }

    public void setDateTextAppearance(int dateTextAppearance) {
        this.dateTextAppearance = dateTextAppearance;
    }

    public int getDateTextCheckedAppearance() {
        return dateTextCheckedAppearance;
    }

    public void setDateTextCheckedAppearance(int dateTextCheckedAppearance) {
        this.dateTextCheckedAppearance = dateTextCheckedAppearance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.dateTextDefaultRes);
        dest.writeInt(this.dateTextCheckedRes);
        dest.writeInt(this.dateDefaultRes);
        dest.writeInt(this.dateCheckedRes);
        dest.writeInt(this.dateTextAppearance);
        dest.writeInt(this.dateTextCheckedAppearance);
    }

    public DateItemStyle() {
    }

    protected DateItemStyle(Parcel in) {
        this.dateTextDefaultRes = in.readInt();
        this.dateTextCheckedRes = in.readInt();
        this.dateDefaultRes = in.readInt();
        this.dateCheckedRes = in.readInt();
        this.dateTextAppearance = in.readInt();
        this.dateTextCheckedAppearance = in.readInt();
    }

    public static final Parcelable.Creator<DateItemStyle> CREATOR = new Parcelable.Creator<DateItemStyle>() {
        @Override
        public DateItemStyle createFromParcel(Parcel source) {
            return new DateItemStyle(source);
        }

        @Override
        public DateItemStyle[] newArray(int size) {
            return new DateItemStyle[size];
        }
    };
}
