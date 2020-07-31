package com.example.ktc.GiaoDien;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ktc.Adapter.CustomApdapter;
import com.example.ktc.Adapter.CustomApdapterMonHoc;
import com.example.ktc.DataBase.DBGiaoVien;
import com.example.ktc.DataBase.DBMonHoc;
import com.example.ktc.Model.GiaoVien;
import com.example.ktc.R;

import java.util.ArrayList;

public class GiaoVienActivity extends AppCompatActivity {
    EditText txtma,txthoten,txtsdt;

    Button btnThem, btnXoa, btnSua, btnClear;
    ArrayList<String> data_khoa = new ArrayList<>();
    ArrayAdapter adapter_khoa;
    ArrayList<GiaoVien> data_SV = new ArrayList<>();
    CustomApdapter apdapter ;
    ListView lvDanhSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaovien);
        setControl();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        apdapter = new CustomApdapter(this,R.layout.listview_item1,data_SV);
        lvDanhSach.setAdapter(apdapter);;
        HienThiDL();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBGiaoVien dbGiaoVien = new DBGiaoVien(getApplicationContext());
                GiaoVien GiaoVien = getGiaoVien();
                dbGiaoVien.Them(GiaoVien);
                CapnhapDL();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtma.setText("");
                txthoten.setText("");
                txtsdt.setText("");
            }
        });
    }
    public  void  CapnhapDL()
    {
        try {
            DBGiaoVien db = new DBGiaoVien(this);
            apdapter = new CustomApdapter(this, R.layout.listview_item1, db.LayDL());
            lvDanhSach.setAdapter(apdapter);
        }
        catch (Exception ex)
        {
            lvDanhSach.setVisibility(View.GONE);
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        }

    }
    private  void HienThiDL()
    {

        try{
            DBGiaoVien dbSinhVien = new DBGiaoVien(this);
            data_SV = dbSinhVien.LayDL();
            apdapter = new CustomApdapter(this,R.layout.listview_item1,data_SV);
            lvDanhSach.setAdapter(apdapter);
        }
        catch (Exception ex){
            lvDanhSach.setVisibility(View.GONE);
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();

        }
    }
    private GiaoVien getGiaoVien() {
        GiaoVien GiaoVien = new GiaoVien();
        GiaoVien.setMagv(txtma.getText().toString());
        GiaoVien.setTengv(txthoten.getText().toString());
        GiaoVien.setSdt(txtsdt.getText().toString());
        return GiaoVien;
    }


    private void setControl() {
        txtma = findViewById(R.id.txtMa);
        txthoten = findViewById(R.id.txtHoTen);
        txtsdt = findViewById(R.id.txtsdt);
        btnThem = findViewById(R.id.btnThem);
        btnClear = findViewById(R.id.btnClear);
        lvDanhSach = findViewById(R.id.lvDanhSach);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

    case R.id.mnopen:
        try {
            DBGiaoVien db = new DBGiaoVien(this);
            apdapter = new CustomApdapter(this, R.layout.listview_item1, db.LayDL());
            lvDanhSach.setAdapter(apdapter);

            Log.d("test", "Open");
            Intent intent = new Intent(this, DanhSachGV.class);
            startActivity(intent);

        }catch (Exception ex){
            lvDanhSach.setVisibility(View.GONE);
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        }
        break;

            case R.id.mnThoat:
                Log.d("test", "Thoat");
                AlertDialog.Builder builder = new AlertDialog.Builder(GiaoVienActivity.this);
                builder.setTitle("Thông Báo");
                builder.setMessage("Bạn Có Muốn Thoát");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
