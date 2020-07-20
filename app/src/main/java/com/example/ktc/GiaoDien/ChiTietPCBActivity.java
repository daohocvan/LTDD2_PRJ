package com.example.ktc.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ktc.DataBase.DBPCB;
import com.example.ktc.Model.PCB;
import com.example.ktc.R;

import java.util.ArrayList;

public class ChiTietPCBActivity extends AppCompatActivity {
    EditText txtMaSV, txtHoTen, txtDiaChi;
    Button btnxoa,btnsua,btnclear;
    ArrayList<PCB> data_SV = new ArrayList<>();

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
        String ma = getIntent().getExtras().getString("ma");
        DBPCB dbPCB = new DBPCB(this);
        data_SV = dbPCB.LayDL(ma);
        txtMaSV.setText(data_SV.get(0).getSophieu());
        txtHoTen.setText(data_SV.get(0).getNgaygiaobai());
        txtDiaChi.setText(data_SV.get(0).getMagv());

        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBPCB dbpcb = new DBPCB(getApplicationContext());
                PCB pcb = getGiaoVien();
                dbpcb.Xoa(pcb);

                txtMaSV.setText("");
                txtHoTen.setText("");
                txtDiaChi.setText("");

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
                txtMaSV.setText("");
                txtHoTen.setText("");
                txtDiaChi.setText("");
                Intent intent = new Intent(ChiTietPCBActivity.this, PCBActivity.class);
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
    }

    private PCB getGiaoVien() {
        PCB pcb = new PCB();
        pcb.setSophieu(txtMaSV.getText().toString());
        pcb.setNgaygiaobai(txtHoTen.getText().toString());
        pcb.setMagv(txtDiaChi.getText().toString());
        return pcb;
    }

    private void setConTrol() {
        txtMaSV = findViewById(R.id.txtMa);
        txtHoTen = findViewById(R.id.txtHoTen);
        txtDiaChi = findViewById(R.id.txtDiaChi);

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
