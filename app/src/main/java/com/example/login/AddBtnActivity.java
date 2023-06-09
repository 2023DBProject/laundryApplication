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

public class AddBtnActivity extends AppCompatActivity {

    Button addMonthBtn;
    EditText watertaxTextbox, electricityfeeTextbox, adTextbox, detergentTextbox, fabricSoftenerTextbox, bounceTextbox, vinylTextbox, refairTextbox, etcTextbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_btn);

        addMonthBtn = findViewById(R.id.saveMonthBtn);
        watertaxTextbox = findViewById(R.id.watertaxTextbox);
        electricityfeeTextbox = findViewById(R.id.electricityfeeTextbox);
        adTextbox = findViewById(R.id.adTextbox);
        detergentTextbox = findViewById(R.id.detergentTextbox);
        fabricSoftenerTextbox = findViewById(R.id.fabricSoftenerTextbox);
        bounceTextbox = findViewById(R.id.bounceTextbox);
        vinylTextbox = findViewById(R.id.vinylTextbox);
        refairTextbox = findViewById(R.id.refairTextbox);
        etcTextbox = findViewById(R.id.etcTextbox);

        TextView textView = findViewById(R.id.title);
        Intent getIntent = getIntent();
        textView.setText(getIntent.getStringExtra("month_string"));
        Button addMonthBtn = findViewById(R.id.saveMonthBtn);

        addMonthBtn.setOnClickListener(v -> {
            Toast.makeText(AddBtnActivity.this, "저장 중", Toast.LENGTH_LONG).show();

            int waterTax, electricityFee, adFee, detergentFee, fabricSoftenerFee, bounceFee, vinylFee, repairFee, etcFee, month, FixedFee;
            FixedFee = 1428667;
            waterTax = Integer.parseInt(watertaxTextbox.getText().toString());
            electricityFee = Integer.parseInt(electricityfeeTextbox.getText().toString());
            adFee = Integer.parseInt(adTextbox.getText().toString());
            detergentFee = Integer.parseInt(detergentTextbox.getText().toString());
            fabricSoftenerFee = Integer.parseInt(fabricSoftenerTextbox.getText().toString());
            bounceFee = Integer.parseInt(bounceTextbox.getText().toString());
            vinylFee = Integer.parseInt(vinylTextbox.getText().toString());
            repairFee = Integer.parseInt(refairTextbox.getText().toString());
            etcFee = Integer.parseInt(etcTextbox.getText().toString());
            month =  getIntent.getIntExtra("month", -1);

            Map<String, Object> values = new HashMap<>();
            values.put("월", month);
            values.put("고정비용", FixedFee);
            values.put("수도세", waterTax);
            values.put("전기세", electricityFee);
            values.put("광고비", adFee);
            values.put("비품총액", detergentFee + fabricSoftenerFee + bounceFee + vinylFee);
            values.put("지출총액", detergentFee + fabricSoftenerFee + bounceFee + vinylFee + adFee + electricityFee + waterTax);

            Map<String, Object> values_fixtures = new HashMap<>();
            values_fixtures.put("월", month);
            values_fixtures.put("비품총액", detergentFee + fabricSoftenerFee + bounceFee + vinylFee);
            values_fixtures.put("세제", detergentFee);
            values_fixtures.put("섬유유연제", fabricSoftenerFee);
            values_fixtures.put("바운스", bounceFee);
            values_fixtures.put("세탁비닐", vinylFee);
            values_fixtures.put("수리비", repairFee);
            values_fixtures.put("기타", etcFee);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("지출");
            DatabaseReference myRef_fixtures = database.getReference("비품");

            if (month >= 6) {
                String key = String.valueOf(month - 6) + "00" ;
                myRef.child(key).updateChildren(values);
                myRef_fixtures.child(key).updateChildren(values_fixtures);
            } else {
                String key = String.valueOf(month + 6) + "00" ;
                myRef.child(key).updateChildren(values);
                myRef_fixtures.child(key).updateChildren(values_fixtures);

            }


            Intent intent = new Intent(AddBtnActivity.this, CalendarActivity.class);
            startActivity(intent);
        });
    }




}