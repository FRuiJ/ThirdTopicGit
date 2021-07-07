package com.example.thirdtopic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.thirdtopic.Start.PieChartManagger;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ImageView zeroIv, oneIv, twoIv;
    private PieChart pieChart;
    private int ran;
    Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        show_pie_chart_1();
    }

    private void show_pie_chart_1() {
        ran = random.nextInt(40);
//设置每份所占数量
        List<PieEntry> yvals = new ArrayList<>();
        yvals.add(new PieEntry(20));
        yvals.add(new PieEntry(ran));

// 设置每份的颜色
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#6785f2"));
        colors.add(Color.parseColor("#00FF00"));

        PieChartManagger pieChartManagger = new PieChartManagger(pieChart);
        pieChartManagger.showRingPieChart(yvals, colors);
        pieChart.setCenterText(ran + "℃");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_iv0:
                UserActivity.actionStart(MainActivity.this);
                break;
            case R.id.main_iv1:
                SingActivity.actionStart(MainActivity.this);
                break;
            case R.id.main_iv2:
                AboutActivity.actionStart(MainActivity.this);
                break;
        }
    }

    private void initView() {
        zeroIv = findViewById(R.id.main_iv0);
        oneIv = findViewById(R.id.main_iv1);
        twoIv = findViewById(R.id.main_iv2);
        pieChart = findViewById(R.id.pie_chat);
        zeroIv.setOnClickListener(this);
        oneIv.setOnClickListener(this);
        twoIv.setOnClickListener(this);

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

}