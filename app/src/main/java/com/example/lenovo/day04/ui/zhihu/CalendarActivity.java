package com.example.lenovo.day04.ui.zhihu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.Constants;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";

    private TextView tv_calender_enter;
    private MaterialCalendarView calendarView;
    private String format;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initView();
    }

    private void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("选择日期");
        tv_calender_enter = (TextView) findViewById(R.id.tv_calender_enter);

        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);

        intent = getIntent();
        String today = intent.getStringExtra(Constants.DATE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date parse = simpleDateFormat.parse(today);
            calendarView.setDateSelected(parse,true);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Date dateDate = date.getDate();
                String pattern="yyyyMMdd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                format = simpleDateFormat.format(dateDate);

            }
        });

        tv_calender_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(Constants.DATE,format);
                Log.e(TAG, "onDateSelected: " + format);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }


}
