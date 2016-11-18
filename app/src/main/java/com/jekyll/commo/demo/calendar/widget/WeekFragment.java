package com.jekyll.commo.demo.calendar.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jekyll.commo.demo.R;
import com.jekyll.commo.demo.calendar.adapter.WeekDaysAdapter;
import com.jekyll.commo.demo.calendar.listener.OnDateCheckedListener;
import com.jekyll.commo.demo.calendar.listener.OnDateClickListener;
import com.jekyll.commo.demo.calendar.model.DayOfWeek;
import com.jekyll.commo.demo.calendar.model.WeekModel;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by jie on 2016/11/17.
 */

public class WeekFragment extends Fragment implements OnDateClickListener {

    private WeekModel weekModel;
    private View view;

    private RecyclerView recyclerView;
    private WeekDaysAdapter adapter;

    private List<DayOfWeek> list;
    private Set<Date> selectedDates;
    private List<Date> validDates;

    private OnDateCheckedListener onDateCheckedListener;

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
        if (getActivity() instanceof OnDateCheckedListener) {
            onDateCheckedListener = (OnDateCheckedListener) getActivity();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.week_item, container, false);
            recyclerView = (RecyclerView) view.findViewById(R.id.rv_item);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 7));
            list = weekModel.getWeek();
            adapter = new WeekDaysAdapter(getActivity(), list);
            recyclerView.setAdapter(adapter);
            adapter.setOnDateClickListener(this);
            initSelectedDates();

        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        return view;
    }

    private void initSelectedDates() {
        selectedDates = new HashSet<>();
        if (weekModel != null) {
            List<DayOfWeek> week = weekModel.getWeek();
            if (week != null && !week.isEmpty()) {
                for (DayOfWeek day : week) {
                    if (day.isSelected()) {
                        selectedDates.add(day.getDate());
                    }
                }
            }
        }
        validDates = new LinkedList<>();
        for (DayOfWeek dayOfWeek : list) {
            validDates.add(dayOfWeek.getDate());
        }
    }

    public void setSelectedDates(Date... dates) {
        selectedDates.addAll(Arrays.asList(dates));
        notifyDateSetChanged();
    }

    public void setSelectedDates(List<Date> dates) {
        selectedDates.addAll(dates);
        notifyDateSetChanged();
    }

    public void removeSelection(Date date) {
        selectedDates.remove(date);
        notifyDateSetChanged();
    }

    private void checkItem(int position) {
        DayOfWeek dayOfWeek = list.get(position);
        if (selectedDates.contains(dayOfWeek.getDate())) {
            removeSelection(dayOfWeek.getDate());
        } else {
            setSelectedDates(dayOfWeek.getDate());
        }
    }

    public void notifyDateSetChanged() {
        Iterator<DayOfWeek> iterator = list.iterator();
        while (iterator.hasNext()) {
            DayOfWeek day = iterator.next();
            if (selectedDates.contains(day.getDate())) {
                day.setSelected(true);
            } else {
                day.setSelected(false);
            }
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onDateClicked(View view, int position) {
        checkItem(position);
        if (onDateCheckedListener != null) {
            onDateCheckedListener.onDateClicked(view, list.get(position));
        }
    }
}
