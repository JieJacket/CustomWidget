package com.jekyll.common;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jekyll.wu.widget.CustomCalendar;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private CalendarFragment monthStyle, weekStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        monthStyle = CalendarFragment.newInstance(CustomCalendar.MONTH_STYLE);
        weekStyle = CalendarFragment.newInstance(CustomCalendar.WEEK_STYLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, monthStyle, "Month").commit();
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

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_week:
                transaction.replace(R.id.fl_content, weekStyle, "Week");
                transaction.commit();
                break;
            case R.id.action_month:
                transaction.replace(R.id.fl_content, monthStyle, "Month");
                transaction.commit();
                break;
            case R.id.action_show_selections:
                if (getSupportFragmentManager().findFragmentByTag("Week") != null) {
                    CalendarFragment month = (CalendarFragment) getSupportFragmentManager().findFragmentByTag("Week");
                    Toast.makeText(getApplicationContext(), month.getSelections().toString(), Toast.LENGTH_SHORT).show();
                } else if (getSupportFragmentManager().findFragmentByTag("Month") != null) {
                    CalendarFragment month = (CalendarFragment) getSupportFragmentManager().findFragmentByTag("Month");
                    Toast.makeText(getApplicationContext(), month.getSelections().toString(), Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

        return true;
    }


}
