package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarActivity extends AppCompatActivity {

    private String theDay = "";
    Button addBtn;
    Button goBackBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Calendar today = new GregorianCalendar();
        CalendarView calendarView;
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DATE);
        
        // Calendar 들어오면 바로 날짜 선택 켜지고 "확인"누르면 바로 일별 매출 기록 page가 켜짐
//        DatePickerDialog dlg = new DatePickerDialog(CalendarActivity.this, new DatePickerDialog.OnDateSetListener() {
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                theDay = year + " " + month + " " + dayOfMonth;
//                Log.d("day: ", theDay);
//
//            }
//        }, year, month, day);
//        dlg.show();

        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Intent intent = new Intent(CalendarActivity.this, DailyActivity.class);
                theDay = month+1 + "월 " + day + "일 매출 입력";
                intent.putExtra("date", theDay);

                startActivity(intent);
            }
        });


        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(v -> {
            Toast.makeText(CalendarActivity.this, "내역 추가", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CalendarActivity.this, AddBtnActivity.class);
            theDay = month+1 + "월 지출 입력";
            intent.putExtra("month", theDay);
            startActivity(intent);
        });

        goBackBtn = findViewById(R.id.goBackBtn);
        goBackBtn.setOnClickListener(v -> {
            //Toast.makeText(CalendarActivity.this, "메인화면으로...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}