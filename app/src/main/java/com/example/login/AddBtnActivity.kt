package com.example.login;


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class AddBtnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_btn)

        val saveBtn = findViewById<Button>(R.id.saveBtn)
        saveBtn.setOnClickListener() {
            Toast.makeText(this, "저장 중", Toast.LENGTH_LONG).show()

            //넘어가기전 데이터베이스에 데이터 추가

            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }
    }
}