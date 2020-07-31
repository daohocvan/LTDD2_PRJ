package com.example.ktc.GiaoDien;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

import com.example.ktc.Adapter.CustomApdapterMonHoc;
import com.example.ktc.DataBase.DBMonHoc;
import com.example.ktc.Model.MonHoc;
import com.example.ktc.R;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Button btnClear, btnThem;
    EditText txtTenMonHoc, txtChiPhi;
    ListView lvDanhSachMonHoc;
    Spinner spMaMonHoc;
    int index = -1;

    CustomApdapterMonHoc apdapter;
    ArrayList<MonHoc> data_MonHoc = new ArrayList<>();
    ArrayAdapter adapter_MaMonHoc;
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
        data_MaMonHoc.add("AR1");
        data_MaMonHoc.add("AR2");
        data_MaMonHoc.add("AR3");
        adapter_MaMonHoc = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data_MaMonHoc);
        spMaMonHoc.setAdapter(adapter_MaMonHoc);
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
      lvDanhSachMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              DBMonHoc dbMonHoc = new DBMonHoc(getApplicationContext());
              data_MonHoc = dbMonHoc.layDuLieu();
              MonHoc monHoc = data_MonHoc.get(position);
              spMaMonHoc.setSelection(data_MaMonHoc.indexOf(monHoc.getMaMonHoc()));
              txtTenMonHoc.setText(monHoc.getTenMonHoc());
              txtChiPhi.setText(monHoc.getChiPhi());
              index = position;
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
            MonHoc monHoc = new MonHoc();
            monHoc.setMaMonHoc(spMaMonHoc.getSelectedItem().toString());
            monHoc.setTenMonHoc(txtTenMonHoc.getText().toString());
            monHoc.setChiPhi(txtChiPhi.getText().toString());
            dbMonHoc.them(monHoc);
            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
        }
    }


    private void setConTrol() {

        btnThem = findViewById(R.id.btnThem);
        spMaMonHoc = findViewById(R.id.spMaMonHoc);
        txtTenMonHoc = findViewById(R.id.txtTenMonHoc);
        txtChiPhi = findViewById(R.id.txtChiPhi);
        lvDanhSachMonHoc = findViewById(R.id.lvDanhSach);
        btnClear = findViewById(R.id.btnClear);
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
