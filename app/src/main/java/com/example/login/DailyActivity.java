package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DailyActivity extends AppCompatActivity {

    EditText editCloth, editShoe, editThings, editEtc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        editCloth = findViewById(R.id.editCloth);
        editShoe = findViewById(R.id.editShoe);
        editThings = findViewById(R.id.editThings);
        editEtc = findViewById(R.id.editEtc);

        TextView textView = findViewById(R.id.textView);
        Intent getIntent = getIntent();
        textView.setText(getIntent.getStringExtra("date"));
        Button saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(v -> {
            Toast.makeText(DailyActivity.this, "저장 중", Toast.LENGTH_LONG).show();

            int Ecloth, Eshoe, Ethings, Eetc, day, month;
            Ecloth = Integer.parseInt(editCloth.getText().toString());
            Eshoe = Integer.parseInt(editShoe.getText().toString());
            Ethings = Integer.parseInt(editThings.getText().toString());
            Eetc = Integer.parseInt(editEtc.getText().toString());
            day = getIntent.getIntExtra("day", -1);
            month =  getIntent.getIntExtra("month", -1);
            boolean isWeekend = getIntent.getBooleanExtra("isWeekend", false);

            Map<String, Object> values = new HashMap<>();
            values.put("일", day);
            values.put("월", month);
            values.put("세탁건조", Ecloth);
            values.put("세탁용품", Ethings);
            values.put("운동화", Eshoe);
            values.put("기타", Eetc);
            values.put("주말여부", isWeekend);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("매출");

            String key = day + "-" + month;
            myRef.child(key).updateChildren(values);


            Intent intent = new Intent(DailyActivity.this, CalendarActivity.class);
            startActivity(intent);
        });
    }
}