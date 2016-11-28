package com.jekyll.wu.widget.util;

import android.support.v7.util.DiffUtil;

import com.jekyll.wu.widget.model.DayModel;

import java.util.List;

/**
 * Created by jie on 2016/11/28.
 */

public class DateCallback extends DiffUtil.Callback {
    private List<DayModel> oldList;
    private List<DayModel> newList;

    public DateCallback(List<DayModel> oldList, List<DayModel> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList == null ? 0 : oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList == null ? 0 : newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
