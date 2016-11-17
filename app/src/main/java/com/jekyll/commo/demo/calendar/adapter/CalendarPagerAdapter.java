package com.jekyll.commo.demo.calendar.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.jekyll.commo.demo.calendar.model.WeekModel;
import com.jekyll.commo.demo.calendar.widget.WeekFragment;

import java.util.List;

/**
 * Created by jie on 2016/11/17.
 */

public class CalendarPagerAdapter extends FragmentPagerAdapter {
    private List<WeekModel> weekModels;

    public CalendarPagerAdapter(FragmentManager fm, List<WeekModel> weekModels) {
        super(fm);
        this.weekModels = weekModels;
    }

    @Override
    public Fragment getItem(int position) {
        return WeekFragment.newInstance(weekModels.get(position));
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
