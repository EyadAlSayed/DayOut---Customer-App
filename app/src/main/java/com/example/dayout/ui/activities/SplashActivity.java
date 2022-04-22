package com.example.dayout.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dayout.R;

public class SplashActivity extends AppCompatActivity {


    ImageView splash_logo;
    TextView motto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        motto = findViewById(R.id.splash_motto);
        splash_logo = findViewById(R.id.splash_logo);

        Animation fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation bottom_up = AnimationUtils.loadAnimation(this, R.anim.down_to_up_animation);
        fade_in.setDuration(1500);
        bottom_up.setDuration(2000);
        splash_logo.startAnimation(fade_in);
        motto.startAnimation(fade_in);
        motto.startAnimation(bottom_up);
    }
}