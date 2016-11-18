package com.jekyll.commo.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jekyll.commo.demo.calendar.listener.OnDateCheckedListener;
import com.jekyll.commo.demo.calendar.model.DayOfWeek;
import com.jekyll.commo.demo.calendar.model.WeekModel;
import com.jekyll.commo.demo.calendar.util.CalendarUtils;
import com.jekyll.commo.demo.calendar.widget.CustomCalendar;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

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
        models.addAll(utils.getTheDateBeforeWeeks(Calendar.getInstance(), 20));
        models.add(utils.calculatorDate(Calendar.getInstance()));
        models.addAll(utils.getTheAfterWeeks(Calendar.getInstance(), 20));

        customCalendar.setWeekModels(getSupportFragmentManager(), models);

        customCalendar.setCurrentWeek(20);

        customCalendar.setSelectedDates(Calendar.getInstance().getTime());

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
