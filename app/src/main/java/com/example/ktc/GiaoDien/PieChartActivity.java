package com.example.ktc.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ktc.DataBase.DBMonHoc;
import com.example.ktc.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        PieChart pieChart = findViewById(R.id.pieChart);
        ArrayList<PieEntry> monHoc = new ArrayList<>();
        DBMonHoc dbMonHoc = new DBMonHoc(getApplicationContext());
        try{
            ArrayList<String> data_MonHocDangCham;
            data_MonHocDangCham = dbMonHoc.monHocDangCham();

            ArrayList<String> data_MaMonHoc;
            data_MaMonHoc = dbMonHoc.layMaMonHoc();

            monHoc.add(new PieEntry(data_MonHocDangCham.size(), "Môn học đang chấm"));
            monHoc.add(new PieEntry(data_MaMonHoc.size(), "Tổng môn học"));

            PieDataSet pieDataSet = new PieDataSet(monHoc, "");
            pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            pieDataSet.setValueTextColor(Color.BLACK);
            pieDataSet.setValueTextSize(16f);
            PieData pieData = new PieData(pieDataSet);

            pieChart.setData(pieData);
            pieChart.getDescription().setEnabled(false);
            pieChart.setCenterText("Chart môn học đang chấm");
            pieChart.animate();
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}