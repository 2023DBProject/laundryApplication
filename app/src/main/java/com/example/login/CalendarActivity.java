package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
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
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DATE);

        DatePickerDialog dlg = new DatePickerDialog(CalendarActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                theDay = year + " " + month + " " + dayOfMonth;
                Log.d("day: ", theDay);
                Intent intent = new Intent(CalendarActivity.this, DailyActivity.class);
                startActivity(intent);
            }
        }, year, month, day);
        dlg.show();

        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(v -> {
            Toast.makeText(CalendarActivity.this, "내역 추가", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(CalendarActivity.this, AddBtnActivity.class);
//            startActivity(intent);
        });

        goBackBtn = findViewById(R.id.goBackBtn);
        goBackBtn.setOnClickListener(v -> {
            //Toast.makeText(CalendarActivity.this, "메인화면으로...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}