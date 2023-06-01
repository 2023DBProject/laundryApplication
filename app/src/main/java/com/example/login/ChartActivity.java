package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ChartActivity extends AppCompatActivity {
    public static int[] ad = new int[1000];
    public static int[] cp = new int[1000];
    public static int[] sales = new int[1000];
    public static int[] net_profit = new int[100];
    private static int month;
    private static int[] average_daily_sales = new int[1000];
    public static int[] average_weekend_sales = new int[1000];
    private static int[] expenditure = new int[1000];
    public static int[] average_weekday_sales = new int[1000];
    public static int rep = 12; // 총 month 수 - 1 (0 부터 몇번까지 있는 지)
    Button btn_sales;
    Button btn2;
    Button btn_cp;
    Button btn_week;
    Button btn_ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        int n = 0;
        while(true){
            myRef.child("수익결산").child(String.format("%s",String.valueOf(n))).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) { //onDataChange() 메서드는 리스너가 연결될 때 한 번 트리거된 후 하위 항목을 포함하여 데이터가 변경될 때마다 다시 트리거됩니다
                    HashMap<String, Long> data = (HashMap<String, Long>) dataSnapshot.getValue();
                    if (data != null) {
                        month = data.get("월").intValue();
                        sales[month] = data.get("매출").intValue();
                        ad[month] = data.get("광고비").intValue();
                        net_profit[month] = data.get("순수익").intValue();
                        cp[month] = data.get("누적순수익").intValue();
                        expenditure[month] = data.get("지출").intValue();
                        average_weekday_sales[month] = data.get("일평균매출").intValue();
                        average_weekend_sales[month] = data.get("주말 매출 평균").intValue();



                        Log.d("ChartActivity", "수익결산 " + data);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Failed to read value
                    Log.w("ChartActivity", "Failed to read value.", error.toException());
                }
            });
            n++;
            if(n == rep) break;
        }
        System.out.println(sales[7]);
        btn_sales = findViewById(R.id.button1);

        btn_sales.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChartActivity_1.class);
                startActivity(intent);
            }
        });

        btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChartActivity_3.class);
                startActivity(intent);
            }
        });

        btn_cp = findViewById(R.id.button3);
        btn_cp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChartActivity_2.class);
                startActivity(intent);
            }
        });

        btn_week = findViewById(R.id.button4);
        btn_week.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChartActivity_4.class);
                startActivity(intent);
            }
        });

        btn_ad = findViewById(R.id.button5);
        btn_ad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChartActivity_5.class);
                startActivity(intent);
            }
        });


    }

}