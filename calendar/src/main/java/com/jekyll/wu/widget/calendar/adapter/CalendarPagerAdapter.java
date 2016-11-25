package com.jekyll.wu.widget.calendar.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.jekyll.wu.widget.calendar.model.PagerModel;
import com.jekyll.wu.widget.calendar.widget.WeekFragment;

import java.util.List;

/**
 * Created by jie on 2016/11/17.
 */

public class CalendarPagerAdapter extends FragmentPagerAdapter {
    private List<PagerModel> weekModels;
    public WeekFragment currentFragment;

    public CalendarPagerAdapter(FragmentManager fm, List<PagerModel> weekModels) {
        super(fm);
        this.weekModels = weekModels;
    }

    @Override
    public Fragment getItem(int position) {
        return currentFragment = WeekFragment.newInstance(weekModels.get(position));
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