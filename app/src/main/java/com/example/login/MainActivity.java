package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.login.CalendarActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button btn_sales;
    Button btn_dash;
    Button btn_solu;
    //Button btn_logout;
    //TextView textView;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        auth = FirebaseAuth.getInstance();
        btn_sales = findViewById(R.id.button_sales_management);
        btn_dash = findViewById(R.id.button_dashboard);
        btn_solu = findViewById(R.id.button_solution);
        //btn_logout = findViewById(R.id.)
        //textView = findViewById(R.id.button_dashboard);
        user = auth.getCurrentUser();   // 현재 user 정보를 user에 넣음

        if (user == null) {
            // 현재 user가 없으면 로그인 activity를 열어
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
//        else {
//            textView.setText(user.getEmail());
//        }

        btn_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // user를 firebase에서 로그아웃 시킴
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        btn_sales.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}