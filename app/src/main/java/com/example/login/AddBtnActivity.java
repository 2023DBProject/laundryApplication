package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddBtnActivity extends AppCompatActivity {

    Button addMonthBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_btn);
        TextView textView = findViewById(R.id.title);
        Intent getIntent = getIntent();
        textView.setText(getIntent.getStringExtra("month"));
        addMonthBtn = findViewById(R.id.saveMonthBtn);
        addMonthBtn.setOnClickListener(v -> {
            Toast.makeText(AddBtnActivity.this, "저장 중", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AddBtnActivity.this, CalendarActivity.class);
            startActivity(intent);
        });
    }




}