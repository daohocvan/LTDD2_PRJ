package com.example.ktc.GiaoDien;

/*import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;*/
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ktc.Adapter.CustomApdapter;
import com.example.ktc.DataBase.DBGiaoVien;
import com.example.ktc.Model.GiaoVien;
import com.example.ktc.R;

import java.util.ArrayList;

public class DanhSachGV extends AppCompatActivity {
    ListView lvDanhsach;
    ArrayList<GiaoVien> data_SV = new ArrayList<>();
    CustomApdapter adapter_sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {

        DBGiaoVien dbGiaoVien = new DBGiaoVien(this);
        data_SV = dbGiaoVien.LayDL();

        adapter_sv = new CustomApdapter(this, R.layout.listview_item1, data_SV);
        lvDanhsach.setAdapter(adapter_sv);
        registerForContextMenu(lvDanhsach);
    }
    public  void  CapnhapDL()
    {
        try {

            DBGiaoVien db = new DBGiaoVien(this);
            adapter_sv = new CustomApdapter(this, R.layout.listview_item1, db.LayDL());
            lvDanhsach.setAdapter(adapter_sv);
        }
        catch (Exception ex)
        {
            lvDanhsach.setVisibility(View.GONE);
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.seach, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.mnseach);
        SearchView searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    adapter_sv.filter("");
                    lvDanhsach.clearTextFilter();
                }
                else {
                    adapter_sv.filter(s);
                }
                return true;
            }
        });
        return true;
    }




    private void setControl() {
        lvDanhsach = findViewById(R.id.lvDanhSach);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.mn_update:
                Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mn_delete:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);

    }
}
