package com.jekyll.commo.demo.calendar.util;

import android.content.Context;

/**
 * Created by jie on 2016/11/17.
 */

public class ViewUtils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
