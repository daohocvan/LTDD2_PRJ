package com.example.ktc.GiaoDien;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ktc.DataBase.DBMonHoc;
import com.example.ktc.Model.MonHoc;
import com.example.ktc.R;

import java.util.ArrayList;


public class ChiTietActivity extends AppCompatActivity {
    EditText txtMaMonHoc, txtTenMonHoc, txtChiPhi;
    ArrayList<MonHoc> data_MonHoc = new ArrayList<>();
    Button btnXoa, btnSua, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        setConTrol();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    private void setEvent() {
        final String ma = getIntent().getExtras().getString("ma");
        final DBMonHoc dbMonHoc = new DBMonHoc(this);
        data_MonHoc = dbMonHoc.layDuLieuBangMaMonHoc(ma);
        txtMaMonHoc.setText(data_MonHoc.get(0).getMaMonHoc());
        txtTenMonHoc.setText(data_MonHoc.get(0).getTenMonHoc());
        txtChiPhi.setText(data_MonHoc.get(0).getChiPhi());
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbMonHoc.xoa(ma);
                Toast.makeText(getApplicationContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonHoc monHoc = new MonHoc();
                if(txtTenMonHoc.getText().toString().equals("") && txtChiPhi.getText().toString().equals("")){
                    txtTenMonHoc.setError("Không được để trống");
                    txtChiPhi.setError("Không được để trống");
                }else{
                    monHoc.setMaMonHoc(ma);
                    monHoc.setTenMonHoc(txtTenMonHoc.getText().toString());
                    monHoc.setChiPhi(txtChiPhi.getText().toString());
                    dbMonHoc.sua(monHoc);
                    Toast.makeText(getApplicationContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTenMonHoc.setText("");
                txtChiPhi.setText("");
            }
        });
    }

    private void setConTrol() {
        txtMaMonHoc = findViewById(R.id.txtMa);
        txtTenMonHoc = findViewById(R.id.txtHoTen);
        txtChiPhi = findViewById(R.id.txtDiaChi);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnClear = findViewById(R.id.btnClear);
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
