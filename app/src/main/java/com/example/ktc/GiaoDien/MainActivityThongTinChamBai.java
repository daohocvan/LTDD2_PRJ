package com.example.ktc.GiaoDien;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ktc.Adapter.CustomApdapterPCB;
import com.example.ktc.Adapter.CustomApdapterTTChamBai;
import com.example.ktc.DataBase.DBGiaoVien;
import com.example.ktc.DataBase.DBMonHoc;
import com.example.ktc.DataBase.DBPCB;
import com.example.ktc.DataBase.DBTTChamBai;
import com.example.ktc.Model.TTChamBai;
import com.example.ktc.R;

import java.util.ArrayList;


public class MainActivityThongTinChamBai extends AppCompatActivity {
    Button btnClear, btnThem;
    EditText txtSoBai;
    ListView lvDanhSach_TTChamBai;
    Spinner spMaMonHoc, spSoPhieu;
    int index = -1;

    CustomApdapterTTChamBai apdapter;
    ArrayList<TTChamBai> data_TTChamBai = new ArrayList<>();

    ArrayAdapter adapter_SoPhieu;
    ArrayList<String> data_SoPhieu = new ArrayList<>();
    ArrayAdapter adapter_MaMonHoc;
    ArrayList<String> data_MaMonHoc = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_tin_cham_bai);
        setControl();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {

        try {
            DBPCB dbpcb = new DBPCB(this);
            data_SoPhieu = dbpcb.laySoPhieu();
            adapter_SoPhieu = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data_SoPhieu);
            adapter_SoPhieu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSoPhieu.setAdapter(adapter_SoPhieu);
            DBMonHoc dbMonHoc = new DBMonHoc(this);
            data_MaMonHoc = dbMonHoc.layMaMonHoc();
            adapter_MaMonHoc = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data_MaMonHoc);
            adapter_MaMonHoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spMaMonHoc.setAdapter(adapter_MaMonHoc);
            hienThiDL();
        }catch (Exception ex){
            lvDanhSach_TTChamBai.setVisibility(View.GONE);
            Toast.makeText(this, "Cần thêm số phiếu", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Cần thêm mã môn học", Toast.LENGTH_SHORT).show();
        }
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themDL();
                hienThiDL();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spMaMonHoc.setSelection(0);
                spSoPhieu.setSelection(0);
                txtSoBai.setText("");
            }
        });
    }

    private void setControl() {
        btnClear = findViewById(R.id.btnClear);
        btnThem = findViewById(R.id.btnThem);
        lvDanhSach_TTChamBai = findViewById(R.id.lvDanhSachTTChamBai);
        spMaMonHoc = findViewById(R.id.spMaMonHoc);
        spSoPhieu = findViewById(R.id.spSoPhieu);
        txtSoBai = findViewById(R.id.txtSoBai);
    }
    public  void hienThiDL()
    {
        try{
            DBTTChamBai dbttChamBai = new DBTTChamBai(this);
            data_TTChamBai = dbttChamBai.layDuLieu();
            apdapter = new CustomApdapterTTChamBai(this,R.layout.list_view_tt_chambai, data_TTChamBai);
            lvDanhSach_TTChamBai.setAdapter(apdapter);
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private  void themDL()
    {
        DBTTChamBai dbttChamBai = new DBTTChamBai(this);

        if(txtSoBai.getText().toString().equals("")){
            txtSoBai.setError("Không để trống");
        }
        else{
            TTChamBai ttChamBai = new TTChamBai();
            ttChamBai.setSoPhieu(spSoPhieu.getSelectedItem().toString());
            ttChamBai.setMaMonHoc(spMaMonHoc.getSelectedItem().toString());
            ttChamBai.setSoBai(txtSoBai.getText().toString());
            dbttChamBai.them(ttChamBai);
            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    apdapter.filter("");
                    lvDanhSach_TTChamBai.clearTextFilter();
                }
                else {
                    apdapter.filter(s);
                }
                return true;

            }
        });
        return true;
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