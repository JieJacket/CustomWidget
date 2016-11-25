package com.jekyll.wu.widget.calendar.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jekyll.wu.widget.R;
import com.jekyll.wu.widget.calendar.adapter.DaysAdapter;
import com.jekyll.wu.widget.calendar.listener.OnDateCheckedListener;
import com.jekyll.wu.widget.calendar.listener.OnDateClickListener;
import com.jekyll.wu.widget.calendar.model.DayModel;
import com.jekyll.wu.widget.calendar.model.PagerModel;

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

    private PagerModel weekModel;
    private View view;

    private RecyclerView recyclerView;
    private DaysAdapter adapter;

    private List<DayModel> list;
    private Set<Date> selectedDates;
    private List<Date> validDates;

    private OnDateCheckedListener onDateCheckedListener;

    public static WeekFragment newInstance(PagerModel model) {
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
        if (getParentFragment() != null && getParentFragment() instanceof OnDateCheckedListener) {
            onDateCheckedListener = (OnDateCheckedListener) getParentFragment();
        } else if (getActivity() instanceof OnDateCheckedListener) {
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
            list = weekModel.week;
            adapter = new DaysAdapter(getActivity(), list);
            recyclerView.setAdapter(adapter);
            recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
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
            List<DayModel> week = weekModel.week;
            if (week != null && !week.isEmpty()) {
                for (DayModel day : week) {
                    if (day.isSelected()) {
                        selectedDates.add(day.getDate());
                    }
                }
            }
        }
        validDates = new LinkedList<>();
        for (DayModel dayOfWeek : list) {
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
        DayModel dayOfWeek = list.get(position);
        if (selectedDates.contains(dayOfWeek.getDate())) {
            removeSelection(dayOfWeek.getDate());
        } else {
            setSelectedDates(dayOfWeek.getDate());
        }
    }

    public void notifyDateSetChanged() {
        Iterator<DayModel> iterator = list.iterator();
        while (iterator.hasNext()) {
            DayModel day = iterator.next();
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
