package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SolutionActivity extends AppCompatActivity {

    TextView titleView;

    TextView rainSolView;

    TextView temperSolView;

    TextView holidaySolView;
    TextView adsSolView;

    TextView dustSolView;
    public String rain100 = "강수량이 100mm 이상인 날이 존재\n" +
            "그 주의 주말에는 매출이 상승할 것으로 예상\n" +
            "세탁기 및 건조기 점검 요망";

    public String aveTemp20 = "일 평균 온도가 20도 이상인 날들이 지속될 예정\n" +
            "음료수 매출이 증가할 것으로 예상\n" +
            "음료수 재고 관리 요망";

    public String aveTemp15 = "일 평균 온도가 15를 넘는 날이 많아짐\n" +
            "에어컨 필터 청소 및 점검 요망";

    public String aveTemp10 = "일 평균 온도가 10도 이하인 날들이 지속될 예정\n" +
            "난방기구 점검 요망";

    public String finedust100 = "미세먼지 농도가 100㎍/㎥ 이상인 날 존재\n" +
            "해당 일 매출 감소할 예정\n" +
            "수리 필요한 경우 이 날 수리 권고";

    public String holiday3 = "연휴가 3일 이상인 기간 존재\n" +
            "해당 연휴 기간 매출 상승 예상\n" +
            "연휴로 인해 매장내 장시간 손님이 머무를 것으로 예상\n" +
            "세탁기, 건조기, 자판기, 안마의자 점검 요망";

    public String noHoliday = "이번 달에는 연휴가 없음\n" +
            "매출 감소할 예정\n" +
            "광고 요망";

    public String adsO = "지난 달 광고로 인해 매출이 평소보다 20% 상승\n" +
            "지속적인 광고 권고";

    public String adsX = "지난 ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);

//        int month = LocalDate.now().getMonthValue();
//        String solutionString = month + "월 매장 관리 솔루션";

        titleView = findViewById(R.id.solutionTitle);
        titleView.setText("매장 관리 솔루션");

        Spinner spinner = (Spinner) findViewById(R.id.month_spinner);

        String[] months = new String[]{"22년 6월", "22년 7월", "22년 8월", "22년 9월", "22년 10월", "22년 11월", "22년 12월","23년 1월", "23년 2월", "23년 3월", "23년 4월", "23년 5월"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, months);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // position을 사용해 선택한 월을 가져옵니다.
                String selectedMonth = months[position];
                rainSolView = findViewById(R.id.rainSolution);
                temperSolView = findViewById(R.id.temperSolution);
                holidaySolView = findViewById(R.id.holidaysSolution);
                dustSolView = findViewById(R.id.dustSolution);
                adsSolView = findViewById(R.id.adsSolution);

                rainSolView.setText("해당사항 없음"); // 초기값으로 설정
                temperSolView.setText("해당사항 없음"); // 초기값으로 설정
                holidaySolView.setText("해당사항 없음"); // 초기값으로 설정
                dustSolView.setText("해당사항 없음"); // 초기값으로 설정
                adsSolView.setText("해당사항 없음"); // 초기값으로 설정
                switch (selectedMonth) {
                    case "22년 6월":
                        rainSolView.setText(rain100);
                        temperSolView.setText(aveTemp20);
                        holidaySolView.setText(holiday3);
                        break;
                    case "22년 7월":
                        rainSolView.setText(rain100);
                        temperSolView.setText(aveTemp20);
                        break;
                    case "22년 8월":
                        rainSolView.setText(rain100);
                        temperSolView.setText(aveTemp20);
                        holidaySolView.setText(holiday3);
                        break;
                    case "22년 9월":
                        holidaySolView.setText(holiday3);
                        break;
                    case "22년 10월":
                        holidaySolView.setText(holiday3);
                        adsSolView.setText(adsO);
                        break;
                    case "22년 11월":
                        temperSolView.setText(aveTemp10);
                        holidaySolView.setText(holiday3);
                        break;
                    case "22년 12월":
                        temperSolView.setText(aveTemp10);
                        dustSolView.setText(finedust100);
                        break;
                    case "23년 1월":
                        holidaySolView.setText(holiday3);
                        dustSolView.setText(finedust100);
                        break;
                    case "23년 2월":
                        holidaySolView.setText(noHoliday);
                        break;
                    case "23년 3월":
                        dustSolView.setText(finedust100);
                        holidaySolView.setText(noHoliday);
                        break;
                    case "23년 4월":
                        temperSolView.setText(aveTemp15);
                        break;
                    case "23년 5월":
                        holidaySolView.setText(holiday3);
                        break;
                }
                // Toast 메시지로 선택한 월을 표시합니다.
                Toast.makeText(getApplicationContext(), "Selected: " + selectedMonth, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                
                
            }
        });
        



    }
}