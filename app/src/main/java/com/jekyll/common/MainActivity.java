package com.jekyll.common;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jekyll.common.calendar.listener.OnDateCheckedListener;
import com.jekyll.common.calendar.model.DayOfWeek;
import com.jekyll.common.calendar.model.WeekModel;
import com.jekyll.common.calendar.util.CalendarUtils;
import com.jekyll.common.calendar.widget.CustomCalendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnDateCheckedListener {

    private static final String TAG = "MainActivity";
    private CustomCalendar customCalendar;
    private List<WeekModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        customCalendar = (CustomCalendar) findViewById(R.id.cc_test);

        models = new LinkedList<>();
        CalendarUtils utils = new CalendarUtils();

        Calendar start = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        try {
            Date startDate = format.parse("20161020");
            start.setTime(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar end = Calendar.getInstance();

        models = customCalendar.setCalendarLimit(start, end);//设置日期的开始结束位置

        customCalendar.setWeekModels(getSupportFragmentManager(), models);

        customCalendar.setCurrentWeek(models.size() - 1);//设置日期到最后一页

        try {
            customCalendar.setSelectedDates(format.parse("20161020"), format.parse("20161021"), format.parse("20161121"));//设置选中的时间集合
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        models.addAll(utils.getTheDateBeforeWeeks(Calendar.getInstance(), 20));
//        models.add(utils.calculatorDate(Calendar.getInstance()));
//        models.addAll(utils.getTheAfterWeeks(Calendar.getInstance(), 20));
//
//        customCalendar.setWeekModels(getSupportFragmentManager(), models);
//
//        customCalendar.setCurrentWeek(20);
//
//        customCalendar.setSelectedDates(Calendar.getInstance().getTime());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), customCalendar.getSelections().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDateClicked(View view, DayOfWeek day) {
        Toast.makeText(getApplicationContext(), day.toString(), Toast.LENGTH_SHORT).show();
    }
}
