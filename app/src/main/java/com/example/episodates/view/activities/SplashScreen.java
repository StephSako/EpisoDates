package com.example.episodates.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.episodates.R;

public class SplashScreen extends Activity {


    public SplashScreen() { }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        int SPLASH_TIME_OUT = 1500;
        ImageView logo = findViewById(R.id.ivSplashscreen);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run(){

                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.splashscreen_animation);
        logo.startAnimation(myanim);
    }
}