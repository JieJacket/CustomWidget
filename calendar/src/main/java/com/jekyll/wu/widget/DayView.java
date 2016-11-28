package com.jekyll.wu.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.widget.TextViewCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jekyll.wu.widget.calendar.R;
import com.jekyll.wu.widget.model.DateItemStyle;
import com.jekyll.wu.widget.model.DayModel;
import com.jekyll.wu.widget.util.ViewUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jie on 2016/11/17.
 */

public class DayView extends LinearLayout {
    private TextView dayText;
    private TextView monthText;
    private boolean isSelected;

    private DayModel dayOfWeek;

    private static final String[] MONTHS = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};

    private DateItemStyle itemStyle;

    public DayView(Context context) {
        this(context, null);
    }

    public DayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        addDayView(context);
        addMonthView(context);
        resetViewStatus(isSelected);
    }

    private void addDayView(Context context) {

        dayText = new TextView(context);
        LayoutParams dayParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dayParams.gravity = Gravity.CENTER_HORIZONTAL;
        dayText.setLayoutParams(dayParams);
        dayText.setGravity(Gravity.CENTER);
        TextViewCompat.setTextAppearance(dayText, R.style.DefaultDayViewStyle);
        dayText.setBackgroundResource(R.drawable.day_view_content_background);
        dayText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        dayParams.bottomMargin = ViewUtils.dp2px(context, 2);
        addView(dayText);
    }

    private void addMonthView(Context context) {
        monthText = new TextView(context);
        LayoutParams monthParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        monthParams.gravity = Gravity.CENTER_HORIZONTAL;
        monthText.setLayoutParams(monthParams);
        monthText.setTextColor(Color.parseColor("#cccccc"));
        monthText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        addView(monthText);
    }

    public DayModel getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayModel dayOfWeek, DateItemStyle itemStyle) {
        this.dayOfWeek = dayOfWeek;
        if (dayOfWeek != null && dayOfWeek.getDate() != null) {
            dayText.setText(getDayLabel(dayOfWeek.getDate()));
            monthText.setText(getMonthLabel(dayOfWeek.getDate()));
            this.itemStyle = itemStyle;
            setSelected(dayOfWeek.isSelected());
        } else {
            dayText.setText(null);
            dayText.setBackgroundDrawable(null);
            monthText.setText(null);
        }
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setSelected(boolean selected) {
        isSelected = selected;
        resetViewStatus(isSelected);
    }

    @NonNull
    private String getDayLabel(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.format(Locale.getDefault(), "%d", calendar.get(Calendar.DAY_OF_MONTH));
    }

    private String getMonthLabel(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return MONTHS[calendar.get(Calendar.MONTH)];
    }

//    @Override
//    public void onClick(View v) {
//        if (dayOfWeek.getDate() == null) {
//            return;
//        }
//        isSelected = !isSelected;
//        resetViewStatus(isSelected);
//    }

    private void resetViewStatus(boolean isSelected) {
        setDateTextAppearance(isSelected);
        setDateTextBackground(isSelected);
        setDateBackground(isSelected);

    }

    /**
     * 设置View的background
     *
     * @param isSelected
     */
    public void setDateBackground(boolean isSelected) {
        if (isSelected) {
            if (itemStyle != null && itemStyle.getDateCheckedRes() > 0) {
                setBackgroundResource(itemStyle.getDateCheckedRes());
            } else {
                setBackgroundDrawable(null);
            }
        } else {
            if (itemStyle != null && itemStyle.getDateDefaultRes() > 0) {
                setBackgroundResource(itemStyle.getDateDefaultRes());
            } else {
                setBackgroundDrawable(null);
            }
        }
    }

    /**
     * 设置日期天数background
     *
     * @param isSelected
     */
    public void setDateTextBackground(boolean isSelected) {
        if (isSelected) {
            if (itemStyle != null && itemStyle.getDateTextCheckedRes() > 0) {
                dayText.setBackgroundResource(itemStyle.getDateTextCheckedRes());
            } else {
                dayText.setBackgroundResource(R.drawable.selected_day_view_content_background);
            }
        } else {
            if (itemStyle != null && itemStyle.getDateTextDefaultRes() > 0) {
                dayText.setBackgroundResource(itemStyle.getDateTextDefaultRes());
            } else {
                dayText.setBackgroundResource(R.drawable.day_view_content_background);
            }
        }
    }

    /**
     * 设置日期天数字体样式
     *
     * @param isSelected
     */
    public void setDateTextAppearance(boolean isSelected) {
        if (isSelected) {
            if (itemStyle != null && itemStyle.getDateTextCheckedAppearance() > 0) {
                TextViewCompat.setTextAppearance(dayText, itemStyle.getDateTextCheckedAppearance());
            } else {
                TextViewCompat.setTextAppearance(dayText, R.style.SelectedDayViewStyle);
            }
        } else {
            if (itemStyle != null && itemStyle.getDateTextAppearance() > 0) {
                TextViewCompat.setTextAppearance(dayText, itemStyle.getDateTextAppearance());
            } else {
                TextViewCompat.setTextAppearance(dayText, R.style.DefaultDayViewStyle);
            }
        }
    }
}
