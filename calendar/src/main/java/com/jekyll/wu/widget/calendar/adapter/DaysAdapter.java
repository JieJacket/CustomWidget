package com.jekyll.wu.widget.calendar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jekyll.wu.widget.R;
import com.jekyll.wu.widget.calendar.listener.OnDateClickListener;
import com.jekyll.wu.widget.calendar.model.DayModel;
import com.jekyll.wu.widget.calendar.widget.DayView;

import java.util.List;


/**
 * Created by jie on 2016/11/17.
 */
public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.ViewHolder> {
    private Context context;
    private List<DayModel> days;
    private LayoutInflater inflater;
    private OnDateClickListener onDateClickListener;

    public DaysAdapter(Context context, List<DayModel> days) {
        this.context = context;
        this.days = days;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.day_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DayModel day = days.get(position);
        holder.dayView.setDayOfWeek(day);
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
            if (onDateClickListener != null) {
                onDateClickListener.onDateClicked(v, getAdapterPosition());
            }
        }
    }
}