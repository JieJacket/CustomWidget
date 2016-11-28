package com.jekyll.wu.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.StyleRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jekyll.wu.widget.adapter.CalendarPagerAdapter;
import com.jekyll.wu.widget.calendar.R;
import com.jekyll.wu.widget.model.DateItemStyle;
import com.jekyll.wu.widget.model.DayModel;
import com.jekyll.wu.widget.model.PagerModel;
import com.jekyll.wu.widget.util.CalendarUtils;
import com.jekyll.wu.widget.util.ViewUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by jie on 2016/11/17.
 */

public class CustomCalendar extends LinearLayout implements ViewPager.OnPageChangeListener {

    private static final String[] WEEK = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    private static final String TAG = CustomCalendar.class.getSimpleName();
//    private static final String[] MONTHS = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};

    private WrapContentHeightViewPager calendarContainer;
    private CalendarPagerAdapter pagerAdapter;
    private List<PagerModel> pagerModels;

    private TextView tvDate;

    public static final int WEEK_STYLE = 1;

    public static final int MONTH_STYLE = 2;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM", Locale.getDefault());

    @IntDef({WEEK_STYLE, MONTH_STYLE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Style {
    }

    @Style
    int curStyle = WEEK_STYLE;


    private DateItemStyle itemStyle;

    public CustomCalendar(Context context) {
        this(context, null);
    }

    public CustomCalendar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        setOrientation(VERTICAL);
        addTopLabel(context);
        addCalendar(context);
        initData();
    }

    private void initData() {
        pagerModels = new LinkedList<>();
        itemStyle = new DateItemStyle();
    }

    /**
     * 添加上层的月份与星期
     *
     * @param context
     */
    private void addTopLabel(Context context) {
        tvDate = new TextView(context);
        LayoutParams dateParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewUtils.dp2px(context, 36));
        tvDate.setLayoutParams(dateParams);
        tvDate.setTextColor(Color.parseColor("#cccccc"));
        tvDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tvDate.setGravity(Gravity.CENTER);
        addView(tvDate);

        LinearLayout topContainer = new LinearLayout(context);
        topContainer.setOrientation(HORIZONTAL);
        topContainer.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewUtils.dp2px(context, 36)));
        for (String day : WEEK) {
            TextView tv = new TextView(context);
            LayoutParams lp = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            tv.setLayoutParams(lp);
            tv.setText(day);
            tv.setTextColor(Color.parseColor("#cccccc"));
            tv.setGravity(Gravity.CENTER);
            topContainer.addView(tv);
        }
        addView(topContainer);
    }

    /**
     * 添加日历
     *
     * @param context
     */
    private void addCalendar(Context context) {
        calendarContainer = new WrapContentHeightViewPager(context);
        calendarContainer.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        calendarContainer.addOnPageChangeListener(this);
        calendarContainer.setId(R.id.calendar_vp);
        addView(calendarContainer);
    }

    public void setPagerModels(FragmentManager fm, List<PagerModel> pagerModels) {
        this.pagerModels.clear();
        this.pagerModels.addAll(pagerModels);
        pagerAdapter = new CalendarPagerAdapter(fm, pagerModels, itemStyle);
        calendarContainer.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();
        if (pagerModels.size() > 0) {
            checkCurMonth(pagerModels.get(0));
        }
    }

    /**
     * 设置当前页数
     *
     * @param pager
     */
    public void setCurrentWeek(int pager) {
        if (calendarContainer != null) {
            pager = pager > 0 && pager <= pagerAdapter.getCount() - 1 ? pager : 0;
            calendarContainer.setCurrentItem(pager);
        }
    }


    public void setCalendarStyle(@Style int curStyle) {
        this.curStyle = curStyle;
    }

    /**
     * 设置日历的开始和结束日期
     *
     * @param start
     * @param end
     */
    public List<PagerModel> setCalendarRange(Calendar start, Calendar end) {
        List<PagerModel> models = new LinkedList<>();
        if (start == null || end == null || start.getTimeInMillis() > end.getTimeInMillis()) {
            return models;
        }

        while (true) {
            PagerModel model = new PagerModel();
            Log.i(TAG, String.format(Locale.getDefault(), "start:%s;end:%s", start.getTime().toString(), end.getTime().toString()));
            switch (curStyle) {
                case WEEK_STYLE:
                    model = CalendarUtils.calculatorWeek(start);
                    start.add(Calendar.DAY_OF_MONTH, 7);
                    break;
                case MONTH_STYLE:
                    model = CalendarUtils.calculatorMonth(start);
                    start.add(Calendar.MONTH, 1);
                    break;
                default:
                    break;
            }
            models.add(model);
            if (model.contain(end)) {
                return models;
            }
        }
    }

    /**
     * 设置选中的时间
     *
     * @param dates
     */
    public void setSelectedDates(Date... dates) {
        if (this.pagerModels.isEmpty() || dates == null || dates.length == 0) {
            return;
        }
        Iterator<PagerModel> iterator = this.pagerModels.iterator();
        while (iterator.hasNext()) {
            PagerModel weekModel = iterator.next();
            if (weekModel != null && weekModel.week != null) {
                Iterator<DayModel> weekIterator = weekModel.week.iterator();
                while (weekIterator.hasNext()) {
                    DayModel next = weekIterator.next();
                    for (Date date : dates) {
                        DayModel day = new DayModel();
                        day.setDate(date);
                        if (next.equals(day)) {
                            next.setSelected(true);
                        }
                    }
                }
            }

        }
        pagerAdapter.notifyDataSetChanged();

    }

    public void setDateCheckedBackgroundRes(@DrawableRes int resId) {
        this.itemStyle.setDateCheckedRes(resId);
        if (pagerAdapter != null) {
            pagerAdapter.notifyDataSetChanged();
        }
    }


    public void setDateDefaultBackgroundRes(@DrawableRes int resId) {
        this.itemStyle.setDateDefaultRes(resId);
        if (pagerAdapter != null) {
            pagerAdapter.notifyDataSetChanged();
        }
    }

    public void setDateTextSelectedRes(@DrawableRes int resId) {
        this.itemStyle.setDateTextCheckedRes(resId);
        if (pagerAdapter != null) {
            pagerAdapter.notifyDataSetChanged();
        }
    }

    public void setDateTextDefaultRes(@DrawableRes int resId) {
        this.itemStyle.setDateTextDefaultRes(resId);
        if (pagerAdapter != null) {
            pagerAdapter.notifyDataSetChanged();
        }
    }

    public void setDateTextAppearance(@StyleRes int resId) {
        this.itemStyle.setDateTextAppearance(resId);
        if (pagerAdapter != null) {
            pagerAdapter.notifyDataSetChanged();
        }
    }


    public void setDateCheckedTextAppearance(@StyleRes int resId) {
        this.itemStyle.setDateTextCheckedAppearance(resId);
        if (pagerAdapter != null) {
            pagerAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        checkCurMonth(pagerModels.get(position));
    }

    /**
     * 设置最上方显示的日期
     *
     * @param model
     */
    private void checkCurMonth(PagerModel model) {
        List<DayModel> week = model.week;
        DayModel dayOfWeek = CalendarUtils.getFirstValidDay(week);

        Calendar calendar = Calendar.getInstance();
        if (dayOfWeek.getDate() == null) {
            return;
        }
        calendar.setTime(dayOfWeek.getDate());

        if (dateFormat != null) {
            tvDate.setText(dateFormat.format(calendar.getTime()));
        }
    }


    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
        if (calendarContainer != null) {
            setCurrentWeek(calendarContainer.getCurrentItem());
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 获取选中的日期
     *
     * @return
     */
    public List<DayModel> getSelections() {
        List<DayModel> select = new LinkedList<>();
        if (pagerModels != null) {
            for (PagerModel week : pagerModels) {
                for (DayModel day : week.week) {
                    if (day.isSelected()) {
                        select.add(day);
                    }
                }
            }
        }
        return select;
    }
}
