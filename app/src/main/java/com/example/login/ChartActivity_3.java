package com.example.login;

import static com.example.login.ChartActivity.net_profit;
import static com.example.login.ChartActivity.rep;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class ChartActivity_3 extends AppCompatActivity {
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
                int barnum = 0;
                for(int i = 6; i <= rep; i++){
                    entry_chart.add(new BarEntry(barnum, net_profit[i]));
                    barnum++;
                }
                for(int i = 1; i <= 5; i++){
                    entry_chart.add(new BarEntry(barnum, net_profit[i]));
                    barnum++;
                }
                BarDataSet barDataSet = new BarDataSet(entry_chart, "월별 순수익"); // 데이터가 담긴 Arraylist 를 BarDataSet 으로 변환한다.

                XAxis xAxis = barChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setLabelCount(12); // x축 라벨 수
                xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[] { "6월","7월", "8월", "9월", "10월", "11월",
                        "12월", "1월", "2월", "3월", "4월", "5월"}));

                barDataSet.setColor(Color.BLUE); // 해당 BarDataSet 색 설정 :: 각 막대 과 관련된 세팅은 여기서 설정한다.

                barData.addDataSet(barDataSet); // 해당 BarDataSet 을 적용될 차트에 들어갈 DataSet 에 넣는다.

                barChart.setData(barData); // 차트에 위의 DataSet 을 넣는다.
                barChart.invalidate();
                barChart.invalidate(); // 차트 업데이트
            }
        });

    }
}