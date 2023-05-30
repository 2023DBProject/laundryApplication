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

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarActivity extends AppCompatActivity {

    private String theDate = "";
    private String theMonth = "";
    private String selectedDay;
    private DatabaseReference mDatabase;
    Button addBtn;
    Button dailyBtn;
    Button goBackBtn;
    private int theDay;
    private int themonth;
    boolean isWeekend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Calendar today = new GregorianCalendar();
        CalendarView calendarView;
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DATE);


        calendarView = findViewById(R.id.calendarView);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                theDate = (month + 1) + "월 " + dayOfMonth + "일 매출 입력";
                theMonth = (month + 1) + "월 지출 입력";
                themonth = (month + 1);
                theDay = dayOfMonth;

                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);
                int dayOfWeek = selectedDate.get(Calendar.DAY_OF_WEEK);
                isWeekend = (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);

                selectedDay = theDate;

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("매출").child(theDate);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // 데이터가 있는 경우
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                String strMonth = snapshot.child("월").getValue().toString();
                                String strDay = snapshot.child("일").getValue().toString();
                                if (strMonth.equals(String.valueOf(month + 1)) && strDay.equals(String.valueOf(dayOfMonth))) {
                                    // Get data from snapshot
                                    Long laundry = snapshot.child("세탁/건조").getValue(Long.class);
                                    Long shoes = snapshot.child("운동화").getValue(Long.class);
                                    Long detergent = snapshot.child("세탁용품").getValue(Long.class);

                                    // TODO: Update your UI using the data
                                    TextView showEarn = findViewById(R.id.showEarn);
                                    showEarn.setText("세탁/건조: " + laundry + ", 운동화: " + shoes + ", 세탁용품: " + detergent);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("Firebase", "Failed to read value.", error.toException());
                        Toast.makeText(CalendarActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        dailyBtn = findViewById(R.id.dailyBtn);
        dailyBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CalendarActivity.this, DailyActivity.class);
            intent.putExtra("date", theDate);
            intent.putExtra("day", theDay);
            intent.putExtra("month", themonth);
            intent.putExtra("isWeekend", isWeekend);
            startActivity(intent);
        });

        addBtn = findViewById(R.id.addBtn);
            addBtn.setOnClickListener(v -> {
            Toast.makeText(CalendarActivity.this, "내역 추가", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CalendarActivity.this, AddBtnActivity.class);
            intent.putExtra("month", theMonth);
            intent.putExtra("month", themonth);
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