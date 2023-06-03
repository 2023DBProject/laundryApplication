package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SolutionActivity extends AppCompatActivity {

    TextView titleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);

        int month = LocalDate.now().getMonthValue();
        String solutionString = month + "월 매장 관리 솔루션";

        titleView = findViewById(R.id.solutionTitle);
        titleView.setText(solutionString);


    }
}