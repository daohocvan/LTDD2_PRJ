package com.example.ktc.GiaoDien;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ktc.Adapter.CustomApdapterMonHoc;
import com.example.ktc.Adapter.CustomSpMaMonHoc;
import com.example.ktc.DataBase.DBMonHoc;
import com.example.ktc.Model.MonHoc;
import com.example.ktc.Model.SpMaMonHoc;
import com.example.ktc.R;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Button btnClear, btnThem, btnChart;
    EditText txtTenMonHoc, txtChiPhi;
    ListView lvDanhSachMonHoc;
    Spinner spMaMonHoc;
    int index = -1;

    CustomApdapterMonHoc apdapter;
    ArrayList<SpMaMonHoc> array_MaMonHoc = new ArrayList<>();
    ArrayList<MonHoc> data_MonHoc = new ArrayList<>();
    ArrayList<String> data_MaMonHoc = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setConTrol();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        array_MaMonHoc.add(new SpMaMonHoc("AR1", R.drawable.android2));
        array_MaMonHoc.add(new SpMaMonHoc("AR2", R.drawable.android));
        array_MaMonHoc.add(new SpMaMonHoc("JAVA1", R.drawable.java));
        array_MaMonHoc.add(new SpMaMonHoc("JAVA2", R.drawable.java2));
        CustomSpMaMonHoc adapter_SpMaMonHoc= new CustomSpMaMonHoc(this, R.layout.sp_mamonhoc, array_MaMonHoc);
        spMaMonHoc.setAdapter(adapter_SpMaMonHoc);

        hienThiDL();
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
              txtTenMonHoc.setText("");
              txtChiPhi.setText("");
          }
      });
      btnChart.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), PieChartActivity.class));
          }
      });
    }

    public  void hienThiDL()
    {
        try{
            DBMonHoc dbMonHoc = new DBMonHoc(this);
            data_MonHoc = dbMonHoc.layDuLieu();
            apdapter = new CustomApdapterMonHoc(this,R.layout.listview_item, data_MonHoc);
            lvDanhSachMonHoc.setAdapter(apdapter);
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private  void themDL()
    {
        DBMonHoc dbMonHoc = new DBMonHoc(this);
        if(txtTenMonHoc.getText().toString().equals("")){
            txtTenMonHoc.setError("Không để trống");
        }
        if(txtChiPhi.getText().toString().equals("")){
            txtChiPhi.setError("Không để trống");
        }
        if(!txtTenMonHoc.getText().toString().equals("") && !txtChiPhi.getText().toString().equals("")){
            final MonHoc monHoc = new MonHoc();

            String maMonhoc = array_MaMonHoc.get(spMaMonHoc.getSelectedItemPosition()).maMonhoc;
            monHoc.setMaMonHoc(maMonhoc);
            monHoc.setTenMonHoc(txtTenMonHoc.getText().toString());
            monHoc.setChiPhi(txtChiPhi.getText().toString());
            dbMonHoc.them(monHoc);
            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
        }
    }

    public SpMaMonHoc getItem(int position){

        return array_MaMonHoc.get(position);
    }

    private void setConTrol() {

        btnThem = findViewById(R.id.btnThem);
        spMaMonHoc = findViewById(R.id.spMaMonHoc);
        txtTenMonHoc = findViewById(R.id.txtTenMonHoc);
        txtChiPhi = findViewById(R.id.txtChiPhi);
        lvDanhSachMonHoc = findViewById(R.id.lvDanhSach);
        btnClear = findViewById(R.id.btnClear);
        btnChart = findViewById(R.id.btnChart);
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
                    lvDanhSachMonHoc.clearTextFilter();
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
