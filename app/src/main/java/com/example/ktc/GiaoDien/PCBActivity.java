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

import androidx.appcompat.app.AppCompatActivity;

import com.example.ktc.Adapter.CustomApdapterPCB;
import com.example.ktc.DataBase.DBPCB;
import com.example.ktc.Model.PCB;
import com.example.ktc.R;

import java.util.ArrayList;

public class PCBActivity extends AppCompatActivity {
    EditText txtma,txthoten,txtsdt;

    Button btnThem, btnXoa, btnSua, btnClear;
    ArrayList<String> data_khoa = new ArrayList<>();
    ArrayAdapter adapter_khoa;
    ArrayList<PCB> data_SV = new ArrayList<>();
    CustomApdapterPCB apdapter ;
    ListView lvDanhSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcb);
        setControl();
        setEvent();
    }

    private void setEvent() {
        CapnhapDL();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBPCB dbPCB = new DBPCB(getApplicationContext());
                PCB PCB = getPCB();
                dbPCB.Them(PCB);
                HienThiDL();
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
            DBPCB db = new DBPCB(this);
            apdapter = new CustomApdapterPCB(this, R.layout.activity_custom_apdapter_pcb, db.LayDL());
            lvDanhSach.setAdapter(apdapter);
            HienThiDL();
        }
        catch (Exception ex)
        {
            lvDanhSach.setVisibility(View.GONE);
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        }

    }
    private  void HienThiDL()
    {
        DBPCB dbSinhVien = new DBPCB(this);
        data_SV = dbSinhVien.LayDL();
        apdapter = new CustomApdapterPCB(this,R.layout.listview_item1,data_SV);
        lvDanhSach.setAdapter(apdapter);
    }
    private PCB getPCB() {
        PCB PCB = new PCB();
        PCB.setSophieu(txtma.getText().toString());
        PCB.setNgaygiaobai(txthoten.getText().toString());
        PCB.setMagv(txtsdt.getText().toString());
        return PCB;
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
                    DBPCB db = new DBPCB(this);
                    apdapter = new CustomApdapterPCB(this, R.layout.activity_custom_apdapter_pcb,db.LayDL());
                    lvDanhSach.setAdapter(apdapter);

                    Log.d("test", "Open");
                    Intent intent = new Intent(this, DanhSachPCB.class);
                    startActivity(intent);

                }catch (Exception ex){
                    lvDanhSach.setVisibility(View.GONE);
                    Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.mnThoat:
                Log.d("test", "Thoat");
                AlertDialog.Builder builder = new AlertDialog.Builder(PCBActivity.this);
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
        }

        return super.onOptionsItemSelected(item);
    }
}
