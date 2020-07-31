package com.example.ktc.GiaoDien;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ktc.DataBase.DBTTChamBai;
import com.example.ktc.Model.TTChamBai;
import com.example.ktc.R;

import java.util.ArrayList;


public class ChiTietThongTinChamBai extends AppCompatActivity {
    EditText txtMaMonHoc, txtSoPhieu, txtSoBai;
    ArrayList<TTChamBai> data = new ArrayList<>();
    Button btnXoa, btnSua, btnClear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_thong_tin_cham_bai);
        setControl();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        final String ma = getIntent().getExtras().getString("ma");
        final String soPhieu = getIntent().getExtras().getString("sophieu");
        final DBTTChamBai dbttChamBai = new DBTTChamBai(this);
        data = dbttChamBai.layDuLieuBangMaMonHoc(soPhieu, ma);
        txtMaMonHoc.setText(data.get(0).getMaMonHoc());
        txtSoPhieu.setText(data.get(0).getSoPhieu());
        txtSoBai.setText(data.get(0).getSoBai());
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbttChamBai.xoa(soPhieu, ma);
                Toast.makeText(getApplicationContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivityThongTinChamBai.class);
                startActivity(intent);
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSoBai.setText("");
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TTChamBai ttChamBai = new TTChamBai();
                if(txtSoBai.getText().toString().equals("")){
                    txtSoBai.setError("Không được để trống");
                }else{
                    ttChamBai.setSoPhieu(soPhieu);
                    ttChamBai.setMaMonHoc(ma);
                    ttChamBai.setSoBai(txtSoBai.getText().toString());
                    dbttChamBai.sua(ttChamBai);
                    Toast.makeText(getApplicationContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivityThongTinChamBai.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void setControl() {
        txtMaMonHoc = findViewById(R.id.txtMaMonHoc);
        txtSoPhieu = findViewById(R.id.txtSoPhieu);
        txtSoBai = findViewById(R.id.txtSoBai);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnClear = findViewById(R.id.btnClear);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}