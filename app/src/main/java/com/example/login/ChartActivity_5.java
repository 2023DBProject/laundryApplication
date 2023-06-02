package com.example.login;

import static com.example.login.ChartActivity.ad;
import static com.example.login.ChartActivity.rep;
import static com.example.login.ChartActivity.sales;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class ChartActivity_5 extends AppCompatActivity {

    Button btn_draw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_chart);
        btn_draw = findViewById(R.id.btn_draw);
        btn_draw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BarChart barChart;
                ArrayList<BarEntry> entry_chart = new ArrayList<>(); // 데이터를 담을 Arraylist

                barChart = (BarChart) findViewById(R.id.chart);
                BarData barData = new BarData(); // 차트에 담길 데이터
                int is_ad = 0;
                int is_ad_count = 0;
                int is_not_ad = 0;
                int is_not_ad_count = 0;
                for(int i = 1; i <= rep; i++){
                    if(ad[i] > 0){
                        is_ad += sales[i];
                        is_ad_count ++;
                    }
                    else{
                        is_not_ad += sales[i];
                        is_not_ad_count++;
                    }
                }
                entry_chart.add(new BarEntry(0, is_ad / is_ad_count ));
                entry_chart.add(new BarEntry(1, is_not_ad / is_not_ad_count ));

                BarDataSet barDataSet = new BarDataSet(entry_chart, "광고 유무 차이"); // 데이터가 담긴 Arraylist 를 BarDataSet 으로 변환한다
                XAxis xAxis = barChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setLabelCount(2); // x축 라벨 수
                xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[] {"광고 O", "광고 X" }));
                xAxis.setTextSize(12f); // 라벨의 글자 크기 설정(12f라는 숫자는 변경 가능)

                barDataSet.setColor(Color.rgb(135, 206, 235)); // 해당 BarDataSet 색 설정 :: 각 막대 과 관련된 세팅은 여기서 설정한다.
                barData.setValueTextSize(10f);
                barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                    @Override
                    public void onValueSelected(Entry e, Highlight h) {
                        String selectedValue = String.valueOf((int) e.getY()); // 선택된 바의 값을 정수로 변환한 후 문자열로 변환합니다.
                        Toast.makeText(getApplicationContext(), selectedValue, Toast.LENGTH_SHORT).show(); // 선택된 값 출력
                        barData.setValueTextSize(15f);
                    }

                    @Override
                    public void onNothingSelected() {
                        barData.setValueTextSize(10f);
                    }
                });
                barData.addDataSet(barDataSet); // 해당 BarDataSet 을 적용될 차트에 들어갈 DataSet 에 넣는다.

                barChart.setData(barData); // 차트에 위의 DataSet 을 넣는다.
                barChart.invalidate();
                barChart.invalidate(); // 차트 업데이트
            }
        });
    }
}