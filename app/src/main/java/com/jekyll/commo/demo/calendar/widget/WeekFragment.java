package com.jekyll.commo.demo.calendar.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jekyll.commo.demo.R;
import com.jekyll.commo.demo.calendar.model.WeekModel;

import java.util.List;

/**
 * Created by jie on 2016/11/17.
 */

public class WeekFragment extends Fragment {

    private WeekModel weekModel;
    private WeekView weekView;
    private View view;

    private DayView one, two, three, four, five, six, seven;
    private List<DayView> dayViews;


    public static WeekFragment newInstance(WeekModel model) {
        WeekFragment instance = new WeekFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("Week", model);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weekModel = getArguments().getParcelable("Week");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.week_item, container, false);
            weekView = (WeekView) view.findViewById(R.id.wv_item);
            weekView.setWeekModel(weekModel);
        } else {

            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        return view;
    }

}
