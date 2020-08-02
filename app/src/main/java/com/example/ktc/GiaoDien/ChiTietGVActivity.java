package com.example.ktc.GiaoDien;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ktc.DataBase.DBGiaoVien;
import com.example.ktc.Model.GiaoVien;
import com.example.ktc.R;

import java.util.ArrayList;

public class ChiTietGVActivity extends AppCompatActivity {
    EditText txtMaSV, txtHoTen, txtDiaChi;
    Button btnxoa,btnsua,btnclear, btntheodoi;
    ArrayList<GiaoVien> data_SV = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tietgv);
        setConTrol();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void setEvent() {
        String ma= getIntent().getExtras().getString("ma");
        DBGiaoVien dbGiaoVien = new DBGiaoVien(this);
        data_SV = dbGiaoVien.LayDL(ma);
        txtMaSV.setText(data_SV.get(0).getMagv());
        txtHoTen.setText(data_SV.get(0).getTengv());
        txtDiaChi.setText(data_SV.get(0).getSdt());

        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBGiaoVien dbSinhVien = new DBGiaoVien(getApplicationContext());
                GiaoVien giaoVien = getGiaoVien();
                dbSinhVien.Xoa(giaoVien);

                txtMaSV.setText("");
                txtHoTen.setText("");
                txtDiaChi.setText("");

                Intent intent = new Intent(ChiTietGVActivity.this, GiaoVienActivity.class);
                startActivity(intent);

            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBGiaoVien dbSinhVien = new DBGiaoVien(getApplicationContext());
                GiaoVien sinhVien = getGiaoVien();
                dbSinhVien.Sua(sinhVien);
                txtMaSV.setText("");
                txtHoTen.setText("");
                txtDiaChi.setText("");
                Intent intent = new Intent(ChiTietGVActivity.this, GiaoVienActivity.class);
                startActivity(intent);
            }
        });
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtHoTen.setText("");
                txtDiaChi.setText("");
            }
        });
        btntheodoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietGVActivity.this, TheodoithongtinCB.class);
                Bundle bundle = new Bundle();
                GiaoVien sinhVien = getGiaoVien();
                bundle.putString("tengv", sinhVien.getTengv());
                Bundle bundle2 = new Bundle();
                bundle2.putString("magv", sinhVien.getMagv());

                intent.putExtras(bundle);

                intent.putExtras(bundle2);
                startActivity(intent);
            }
        });

    }

    private GiaoVien getGiaoVien() {
        GiaoVien sinhVien = new GiaoVien();
        sinhVien.setMagv(txtMaSV.getText().toString());
        sinhVien.setTengv(txtHoTen.getText().toString());
        sinhVien.setSdt(txtDiaChi.getText().toString());
        return sinhVien;
    }

    private void setConTrol() {
        txtMaSV = findViewById(R.id.txtMa);
        txtHoTen = findViewById(R.id.txtHoTen);
        txtDiaChi = findViewById(R.id.txtDiaChi);

        btnxoa = findViewById(R.id.button);
        btnsua = findViewById(R.id.button2);
        btnclear = findViewById(R.id.button3);
        btntheodoi = findViewById(R.id.theodoi);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
