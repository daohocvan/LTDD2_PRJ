package com.example.ktc.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.ktc.R;

public class Screen extends AppCompatActivity {

    private  static  int SPLASH_SCREEN = 4000;

    ImageView bieutuong,ani;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_screen);
        setControll();
        setEvent();
    }

    private void setEvent() {
        //Animations
        Animation topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
//Set animation to elements
        bieutuong.setAnimation(topAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Screen.this,MainActivity1.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
        final AnimationDrawable runningCat = (AnimationDrawable) ani.getDrawable();
        runningCat.start();
    }

    private void setControll() {
        bieutuong = findViewById(R.id.imageView);
        ani =  findViewById(R.id.ani);
    }
}
