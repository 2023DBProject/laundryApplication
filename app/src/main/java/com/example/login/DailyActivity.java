package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DailyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        TextView textView = findViewById(R.id.textView);
        Intent getIntent = getIntent();
        textView.setText(getIntent.getStringExtra("date"));
        Button saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(v -> {
            Toast.makeText(DailyActivity.this, "저장 중", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(DailyActivity.this, CalendarActivity.class);
            startActivity(intent);
        });
    }
}