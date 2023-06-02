package com.example.login;

import static com.example.login.ChartActivity.cp;
import static com.example.login.ChartActivity.rep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity_3 extends AppCompatActivity {
    Button btn_draw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp2);

        btn_draw = findViewById(R.id.btn_draw);
        btn_draw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<Entry> entries = new ArrayList<>();

                int barnum = 0;
                for(int i = 6; i <= rep; i++){
                    entries.add(new Entry(barnum, cp[i]));
                    barnum++;
                }
                for(int i = 1; i <= 5; i++){
                    entries.add(new Entry(barnum, cp[i]));
                    barnum++;
                }

                LineDataSet dataset = new LineDataSet(entries, "누적 순수익 데이터");
                LineData data = new LineData(dataset);
                LineChart chart = findViewById(R.id.chart);

                XAxis xAxis = chart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setLabelCount(12); // x축 라벨 수
                xAxis.setGranularity(1); // 추가: 리프 라벨의 격자간격
                xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[] { "6월","7월", "8월", "9월", "10월", "11월",
                        "12월", "1월", "2월", "3월", "4월", "5월"}));
                xAxis.setTextSize(12f); // 라벨의 글자 크기 설정(12f라는 숫자는 변경 가능)

                dataset.setValueTextSize(10f);
                chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                    @Override
                    public void onValueSelected(Entry e, Highlight h) {
                        String selectedValue = String.valueOf(e.getY()); // 선택된 포인트의 y값을 문자열로 변환
                        Toast.makeText(getApplicationContext(), selectedValue, Toast.LENGTH_SHORT).show(); // 선택된 값 출력
                        dataset.setValueTextSize(14f);
                    }

                    @Override
                    public void onNothingSelected() {
                        dataset.setValueTextSize(10f);
                    }
                });

                chart.setData(data);
                chart.invalidate();
            }

        });
    }
}