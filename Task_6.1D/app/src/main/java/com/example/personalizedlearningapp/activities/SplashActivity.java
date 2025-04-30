package com.example.personalizedlearningapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalizedlearningapp.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 2000;
    private ImageView splashLogo;
    private TextView splashText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashLogo = findViewById(R.id.splashLogo);
        splashText = findViewById(R.id.splashText);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        splashLogo.startAnimation(animation);
        splashText.startAnimation(animation);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DISPLAY_LENGTH);
    }
}
