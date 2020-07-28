package com.example.ktc.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.ktc.R;

public class MainActivity1 extends AppCompatActivity {
    Button gv, mh, pcb,ttcb;
    TextView giaovien, monhoc,chambai,thongtin;
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
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                gv.startAnimation(animation);
                giaovien.startAnimation(animation);
                final Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            Intent intent = new Intent(MainActivity1.this, GiaoVienActivity.class);
                            startActivity(intent);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });
                thread.start();

            }
        });
        mh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                mh.startAnimation(animation);
                monhoc.startAnimation(animation);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            Intent intent = new Intent(MainActivity1.this, MainActivity.class);
                            startActivity(intent);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });
                thread.start();

            }
        });
        pcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
                pcb.startAnimation(animation);
                chambai.startAnimation(animation);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            Intent intent = new Intent(MainActivity1.this, PCBActivity.class);
                            startActivity(intent);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();

            }
        });
        ttcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.lefttoright);
                ttcb.startAnimation(animation);
                thongtin.startAnimation(animation);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            Intent intent = new Intent(MainActivity1.this, MainActivityThongTinChamBai.class);
                            startActivity(intent);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();

            }
        });



    }

    private void setcontroll() {
        gv = findViewById(R.id.gv);
        mh = findViewById(R.id.mh);
        pcb = findViewById(R.id.pcb);
        ttcb = findViewById(R.id.ttcb);

        giaovien = findViewById(R.id.giaovien);
        monhoc = findViewById(R.id.monhoc);
        chambai = findViewById(R.id.phieucham);
        thongtin = findViewById(R.id.thongtin);
    }
}
