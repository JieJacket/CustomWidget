package com.jekyll.wu.widget.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.jekyll.wu.widget.WeekFragment;
import com.jekyll.wu.widget.model.DateItemStyle;
import com.jekyll.wu.widget.model.PagerModel;

import java.util.List;

/**
 * Created by jie on 2016/11/17.
 */

public class CalendarPagerAdapter extends FragmentPagerAdapter {
    private List<PagerModel> weekModels;
    public WeekFragment currentFragment;
    private DateItemStyle itemStyle;

    public CalendarPagerAdapter(FragmentManager fm, List<PagerModel> weekModels, DateItemStyle itemStyle) {
        super(fm);
        this.weekModels = weekModels;
        this.itemStyle = itemStyle;
    }

    @Override
    public Fragment getItem(int position) {
        return currentFragment = WeekFragment.newInstance(weekModels.get(position), itemStyle);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return weekModels == null ? 0 : weekModels.size();
    }
}