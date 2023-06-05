package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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

        int month = LocalDate.now().getMonthValue();
        String solutionString = month + "월 매장 관리 솔루션";

        titleView = findViewById(R.id.solutionTitle);
        titleView.setText(solutionString);

        switch (month) {
            case 6:
                rainSolView = findViewById(R.id.rainSolution);
                rainSolView.setText(rain100);
                temperSolView = findViewById(R.id.temperSolution);
                temperSolView.setText(aveTemp20);
                holidaySolView = findViewById(R.id.holidaysSolution);
                holidaySolView.setText(holiday3);
                break;
            case 7:
                rainSolView = findViewById(R.id.rainSolution);
                rainSolView.setText(rain100);
                temperSolView = findViewById(R.id.temperSolution);
                temperSolView.setText(aveTemp20);
                break;
            case 8:
                rainSolView = findViewById(R.id.rainSolution);
                rainSolView.setText(rain100);
                temperSolView = findViewById(R.id.temperSolution);
                temperSolView.setText(aveTemp20);
                holidaySolView = findViewById(R.id.holidaysSolution);
                holidaySolView.setText(holiday3);
                break;
            case 9:
                holidaySolView = findViewById(R.id.holidaysSolution);
                holidaySolView.setText(holiday3);
                break;
            case 10:
                holidaySolView = findViewById(R.id.holidaysSolution);
                holidaySolView.setText(holiday3);
                adsSolView = findViewById(R.id.adsSolution);
                adsSolView.setText(adsO);
                break;
            case 11:
                temperSolView = findViewById(R.id.temperSolution);
                temperSolView.setText(aveTemp10);
                holidaySolView = findViewById(R.id.holidaysSolution);
                holidaySolView.setText(holiday3);
                break;
            case 12:
                temperSolView = findViewById(R.id.temperSolution);
                temperSolView.setText(aveTemp10);
                dustSolView = findViewById(R.id.dustSolution);
                dustSolView.setText(finedust100);
                break;
            case 1:
                holidaySolView = findViewById(R.id.holidaysSolution);
                holidaySolView.setText(holiday3);
                dustSolView = findViewById(R.id.dustSolution);
                dustSolView.setText(finedust100);
                break;
            case 2:
                holidaySolView = findViewById(R.id.holidaysSolution);
                holidaySolView.setText(noHoliday);
                break;
            case 3:
                dustSolView = findViewById(R.id.dustSolution);
                dustSolView.setText(finedust100);
                holidaySolView = findViewById(R.id.holidaysSolution);
                holidaySolView.setText(noHoliday);
                break;
            case 4:
                temperSolView = findViewById(R.id.temperSolution);
                temperSolView.setText(aveTemp15);
                break;
            case 5:
                holidaySolView = findViewById(R.id.holidaysSolution);
                holidaySolView.setText(holiday3);
                break;
        }



    }
}