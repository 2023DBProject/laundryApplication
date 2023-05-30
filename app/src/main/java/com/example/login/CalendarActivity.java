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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarActivity extends AppCompatActivity {

    private String theDay = "";
    private String selectedDay;
    private int clickCount = 0;
    private DatabaseReference mDatabase;
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


        calendarView = findViewById(R.id.calendarView);



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                clickCount++;
                theDay = month+1 + "월 " + day + "일 매출 입력";

                if (clickCount == 1) {
                    selectedDay = theDay;
                    //날에 해당하는 매출 나옴

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("매출").child(theDay);
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // 데이터가 있는 경우
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    String strMonth = snapshot.child("월").getValue().toString();
                                    String strDay = snapshot.child("일").getValue().toString();
                                    if (strMonth.equals(month) && strDay.equals(day)) {
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
                        }
                    });
                }

                    if (clickCount == 2 && selectedDay.equals(theDay)) {
                    Intent intent = new Intent(CalendarActivity.this, DailyActivity.class);
                    intent.putExtra("date", theDay);
                    startActivity(intent);
                    clickCount = 0;
                } else {
                    clickCount = 0;
                }
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