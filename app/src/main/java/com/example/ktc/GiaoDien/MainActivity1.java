package com.example.ktc.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ktc.R;

public class MainActivity1 extends AppCompatActivity {
    Button gv, mh, pcb,ttcb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        setcontroll();
        setevent();
    }

    private void setevent() {
        gv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity1.this, GiaoVienActivity.class);
                startActivity(intent);
            }
        });
        mh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity1.this, MainActivity.class);
                startActivity(intent);
            }
        });
        pcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity1.this, PCBActivity.class);
                startActivity(intent);
            }
        });
        ttcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity1.this, MainActivityThongTinChamBai.class);
                startActivity(intent);
            }
        });
    }

    private void setcontroll() {
        gv = findViewById(R.id.gv);
        mh = findViewById(R.id.mh);
        pcb = findViewById(R.id.pcb);
        ttcb = findViewById(R.id.ttcb);
    }
}
