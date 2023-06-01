package com.example.login;

import static com.example.login.ChartActivity.cp;
import static com.example.login.ChartActivity.rep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity_2 extends AppCompatActivity {
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

                chart.setData(data);
                chart.invalidate();
            }

        });
    }
}