package com.jekyll.wu.widget.adapter;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jekyll.wu.widget.DayView;
import com.jekyll.wu.widget.calendar.R;
import com.jekyll.wu.widget.listener.OnDateClickListener;
import com.jekyll.wu.widget.model.DateItemStyle;
import com.jekyll.wu.widget.model.DayModel;
import com.jekyll.wu.widget.util.DateCallback;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by jie on 2016/11/17.
 */
public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.ViewHolder> {
    private Context context;
    private List<DayModel> days;
    private LayoutInflater inflater;
    private OnDateClickListener onDateClickListener;
    private DateItemStyle itemStyle;

    public DaysAdapter(Context context, List<DayModel> days, DateItemStyle itemStyle) {
        this.context = context;
        this.days = days;
        this.inflater = LayoutInflater.from(context);
        this.itemStyle = itemStyle;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.day_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DayModel day = days.get(position);
        holder.dayView.setDayOfWeek(day,itemStyle);
        holder.dayView.setEnabled(day.isEnable());
    }

    @Override
    public int getItemCount() {
        return days == null ? 0 : days.size();
    }

    public void setOnDateClickListener(OnDateClickListener onDateClickListener) {
        this.onDateClickListener = onDateClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private DayView dayView;

        public ViewHolder(View itemView) {
            super(itemView);
            dayView = (DayView) itemView.findViewById(R.id.dv_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            List<DayModel> newList = new LinkedList<>();
            newList.addAll(days);
            DayModel dayModel = newList.get(getAdapterPosition());
            dayModel.setSelected(!dayModel.isSelected());

            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DateCallback(days,newList),true);
            result.dispatchUpdatesTo(DaysAdapter.this);
            days = newList;
            if (onDateClickListener != null) {
                onDateClickListener.onDateClicked(v, getAdapterPosition());
            }
        }
    }
}