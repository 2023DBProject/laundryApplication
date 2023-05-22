package com.example.login;


import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.util.Calendar
import java.util.GregorianCalendar

class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainpage)


        // 캘린더 관련
        var theDay = ""
        val today = GregorianCalendar()
        val year = today.get(Calendar.YEAR)
        val month = today.get(Calendar.MONTH)
        val day = today.get(Calendar.DATE)

        val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

                //theDay = "${year} + ${month} + ${dayOfMonth}"
                Log.d("day : ", theDay)
                val intent = Intent(this, DailyActivity::class.java)
                startActivity(intent)

            }

            private fun Intent(
                onDateSetListener: DatePickerDialog.OnDateSetListener,
                java: Class<DailyActivity>
            ): Intent? {
                TODO("Not yet implemented")
            }

        }, year, month, day)
        dlg.show()

        //추가 버튼 관련
        val addBtn = findViewById<Button>(R.id.addBtn)
        addBtn.setOnClickListener() {
            Toast.makeText(this, "내역 추가",Toast.LENGTH_LONG).show()

            val intent = Intent(this, AddBtnActivity::class.java)
            startActivity(intent)
        }

        //findViewById<TextView>(R.id.)
    }
}