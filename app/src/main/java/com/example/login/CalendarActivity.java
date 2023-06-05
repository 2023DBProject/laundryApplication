package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.text.NumberFormat;

public class CalendarActivity extends AppCompatActivity {

    // Declare variables at the class level
    private ArrayList<ArrayList<String>> revenueData = new ArrayList<>();
    private ArrayList<ArrayList<String>> profitData = new ArrayList<>();
    private String theDate = "";
    private String theMonth = "";
    private int theDay;
    private int themonth;
    boolean isWeekend;
    private int dataReadyCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Initialize database reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference revenueRef = database.getReference("매출");
        DatabaseReference profitRef = database.getReference("수익결산");

        // Read data from "매출" table
        revenueRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get data for all days of the month
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HashMap<String, Object> data = (HashMap<String, Object>) snapshot.getValue();
                    String month = data.get("월") != null ? data.get("월").toString() : "0";
                    String day = data.get("일") != null ? data.get("일").toString() : "0";
                    String revenue = data.get("일 매출") != null ? data.get("일 매출").toString() : "0";
                    ArrayList<String> rowData = new ArrayList<>();
                    rowData.add(month);
                    rowData.add(day);
                    rowData.add(revenue);
                    revenueData.add(rowData);
                }
                Log.d("Firebase", "Data: " + revenueData);
                dataReadyCounter++;
                tryUpdateData();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Firebase", "Failed to read value.", error.toException());
                Toast.makeText(CalendarActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

        // Read data from "수익결산" table
        profitRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get data for all days of the month
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HashMap<String, Object> data = (HashMap<String, Object>) snapshot.getValue();
                    String month = data.get("월") != null ? data.get("월").toString() : "0";
                    String expense = data.get("지출") != null ? data.get("지출").toString() : "0";
                    String profit = data.get("순수익") != null ? data.get("순수익").toString() : "0";
                    ArrayList<String> rowData = new ArrayList<>();
                    rowData.add(month);
                    rowData.add(expense);
                    rowData.add(profit);
                    profitData.add(rowData);
                }
                ArrayList<String> data = (ArrayList<String>) dataSnapshot.getValue();
                Log.d("Firebase", "Data: " + data);
                dataReadyCounter++;
                tryUpdateData();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Firebase", "Failed to read value.", error.toException());
                Toast.makeText(CalendarActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

        Calendar today = new GregorianCalendar();
        CalendarView calendarView;
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DATE);

        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                theDate = (month + 1) + "월 " + dayOfMonth + "일 매출";
                theMonth = (month + 1) + "월 지출 입력";
                themonth = (month + 1);
                theDay = dayOfMonth;

                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);
                int dayOfWeek = selectedDate.get(Calendar.DAY_OF_WEEK);
                isWeekend = (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);

                TextView textView = findViewById(R.id.dailyText);
                textView.setText(theDate);

                tryUpdateData();
            }
        });

        Button dailyBtn = findViewById(R.id.dailyBtn);
        dailyBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CalendarActivity.this, DailyActivity.class);
            intent.putExtra("date", theDate + "입력");
            intent.putExtra("day", theDay);
            intent.putExtra("month", themonth);
            intent.putExtra("isWeekend", isWeekend);
            startActivity(intent);
        });

        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(v -> {
            Toast.makeText(CalendarActivity.this, "내역 추가", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CalendarActivity.this, AddBtnActivity.class);
            intent.putExtra("month_string", theMonth);
            intent.putExtra("month", themonth);
            startActivity(intent);
        });

        Button goBackBtn = findViewById(R.id.goBackBtn);
        goBackBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void tryUpdateData() {
        if (dataReadyCounter < 2) {
            return;
        }

        NumberFormat nf = NumberFormat.getNumberInstance();

        for (ArrayList<String> row : revenueData) {
            if (row.get(0).equals(String.valueOf(themonth)) && row.get(1).equals(String.valueOf(theDay))) {
                TextView showEarn = findViewById(R.id.showEarn);
                int revenue = Integer.parseInt(row.get(2));
                showEarn.setText("매출: " + nf.format(revenue));
                break;
            }
        }
        for (ArrayList<String> row : profitData) {
            if (row.get(0).equals(String.valueOf(themonth))) {
                TextView showExpense = findViewById(R.id.showPaid);
                int expense = Integer.parseInt(row.get(1));
                showExpense.setText("지출금액: " + nf.format(expense));
                break;
            }
        }
        for (ArrayList<String> row : profitData) {
            if (row.get(0).equals(String.valueOf(themonth))) {
                TextView showProfit = findViewById(R.id.showProfit);
                int profit = Integer.parseInt(row.get(2));
                showProfit.setText("순수익금액: " + nf.format(profit));
                break;
            }
        }
    }
}
