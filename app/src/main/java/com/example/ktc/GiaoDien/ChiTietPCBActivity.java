package com.example.ktc.GiaoDien;


import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ktc.DataBase.DBGiaoVien;
import com.example.ktc.DataBase.DBPCB;
import com.example.ktc.Model.PCB;
import com.example.ktc.R;

import java.util.ArrayList;

public class ChiTietPCBActivity extends AppCompatActivity {
    EditText txtMaSV, txtHoTen;
    TextView textView;
    Spinner  txtDiaChi;
    Button btnxoa,btnsua,btnclear;
    ArrayList<PCB> data_SV = new ArrayList<>();
    ArrayList<String> data_mgv = new ArrayList<>();
    ArrayAdapter adapter_MGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_pcb);
        setConTrol();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void setEvent() {
        DBGiaoVien dbSinhVien = new DBGiaoVien(this);
        data_mgv = dbSinhVien.LayDLMGV();
        adapter_MGV = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data_mgv);
        txtDiaChi.setAdapter(adapter_MGV);
        String ma = getIntent().getExtras().getString("ma");
        DBPCB dbPCB = new DBPCB(this);
        data_SV = dbPCB.LayDL(ma);
        txtMaSV.setText(data_SV.get(0).getSophieu());
        txtHoTen.setText(data_SV.get(0).getNgaygiaobai());
        textView.setText(data_SV.get(0).getMagv());
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBPCB dbpcb = new DBPCB(getApplicationContext());
                PCB pcb = getGiaoVienXoa();
                dbpcb.Xoa(pcb);

                txtMaSV.setText("");
                txtHoTen.setText("");
                txtDiaChi.getSelectedItem();

                Intent intent = new Intent(ChiTietPCBActivity.this, PCBActivity.class);
                startActivity(intent);

            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBPCB dbpcb = new DBPCB(getApplicationContext());
                PCB pcb = getGiaoVien();
                dbpcb.Sua(pcb);
                Intent intent = new Intent(ChiTietPCBActivity.this, PCBActivity.class);
                startActivity(intent);
            }
        });
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtHoTen.setText("");
                textView.setText("");
            }
        });
    }
    private PCB getGiaoVienXoa() {
        PCB pcb = new PCB();
        pcb.setSophieu(txtMaSV.getText().toString());
        pcb.setNgaygiaobai(txtHoTen.getText().toString());
        pcb.setMagv(textView.getText().toString());
        return pcb;
    }
    private PCB getGiaoVien() {
        PCB pcb = new PCB();
        pcb.setSophieu(txtMaSV.getText().toString());
        pcb.setNgaygiaobai(txtHoTen.getText().toString());
        pcb.setMagv(txtDiaChi.getSelectedItem().toString());
        return pcb;
    }

    private void setConTrol() {
        txtMaSV = findViewById(R.id.txtMa);
        txtHoTen = findViewById(R.id.txtHoTen);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        textView = findViewById(R.id.textView);
        btnxoa = findViewById(R.id.button);
        btnsua = findViewById(R.id.button2);
        btnclear = findViewById(R.id.button3);


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
