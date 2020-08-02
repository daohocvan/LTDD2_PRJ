package com.example.ktc.GiaoDien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ktc.Adapter.CustomAdapterPhieuTheoDoi;
import com.example.ktc.Adapter.CustomAdapterThongTinChamBai1;
import com.example.ktc.Adapter.CustomAdapterThongTinChamBai2;
import com.example.ktc.Adapter.CustomApdapter;
import com.example.ktc.DataBase.DBMonHoc;
import com.example.ktc.DataBase.DBPCB;
import com.example.ktc.DataBase.DBTTChamBai;
import com.example.ktc.Model.MonHoc;
import com.example.ktc.Model.PCB;
import com.example.ktc.Model.TTChamBai;
import com.example.ktc.R;

import java.util.ArrayList;

public class TheodoithongtinCB extends AppCompatActivity {
    TextView tvHoTen, tvTongPhieu, tvTongMonCham, tvTongTien;
    DBPCB dbpcb;
    DBTTChamBai dbttChamBai;
    DBMonHoc dbMonHoc;
    ArrayList<PCB> data = new ArrayList<>();
    ArrayList<TTChamBai> data2 = new ArrayList<>();
    ListView lvPhieuChamBai, lvThongTinChamBai1, lvThongTinChamBai2;
    CustomAdapterPhieuTheoDoi adapter_PhieuTheoDoi;
    CustomAdapterThongTinChamBai1 adapter_ThongTinChamBai1;
    CustomAdapterThongTinChamBai2 adapter_ThongTinChamBai2;
    ArrayList<MonHoc> data3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theodoithongtin_cb);
        setControl();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void setEvent() {
       dbpcb = new DBPCB(this);
       dbttChamBai = new DBTTChamBai(this);
       dbMonHoc = new DBMonHoc(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            String tenGV = bundle.getString("tengv");
            String ma = bundle.getString("magv");
            tvHoTen.setText(tenGV);
            try{
                data = dbpcb.LayDL(ma);
                adapter_PhieuTheoDoi = new CustomAdapterPhieuTheoDoi(this, R.layout.listview_phieutheodoi, data);
                lvPhieuChamBai.setAdapter(adapter_PhieuTheoDoi);
                tvTongPhieu.setText(String.valueOf(data.size()));

                data2 = dbttChamBai.lay(ma);
                adapter_ThongTinChamBai1 = new CustomAdapterThongTinChamBai1(this, R.layout.listview_thongtinchambai1, data2);
                lvThongTinChamBai1.setAdapter(adapter_ThongTinChamBai1);

                data3 = dbMonHoc.lay(ma);
                tvTongMonCham.setText("Tổng số môn chấm: " + String.valueOf(data3.size()));
                adapter_ThongTinChamBai2 = new CustomAdapterThongTinChamBai2(this, R.layout.listview_thongtinchambai2, data3);
                lvThongTinChamBai2.setAdapter(adapter_ThongTinChamBai2);
                double tong = 0.0;
                for(int i = 0; i < data3.size(); i++){
                    double chiPhi = Double.parseDouble( data3.get(i).getChiPhi());
                    int soBai = Integer.parseInt(data2.get(i).getSoBai());
                    tong += chiPhi * soBai;
                }
                tvTongTien.setText("Tổng tiền chấm bài: " + String.valueOf(tong) + " VNĐ");



            }catch (Exception ex){
                Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }


        }

    }

    private void setControl() { 
        tvHoTen = findViewById(R.id.tvHoTenGV);
        lvPhieuChamBai = findViewById(R.id.lvTenGiaoVien);
        lvThongTinChamBai1 = findViewById(R.id.lvThongTinChamBai);
        tvTongPhieu = findViewById(R.id.tvTongPhieu);
        lvThongTinChamBai2 = findViewById(R.id.lvThongTin);
        tvTongMonCham= findViewById(R.id.tvTongMonCham);
        tvTongTien = findViewById(R.id.tvTongTien);
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
