package com.example.Horse_App.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Horse_App.R;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;

    Animation topAnimation, bottomAnimation;
    ImageView image;
    TextView title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image = findViewById(R.id.splash_image);
        title = findViewById(R.id.splash_title);
        description = findViewById(R.id.splash_description);

        image.setAnimation(topAnimation);
        image.setAnimation(bottomAnimation);
//        title.setText((CharSequence) bottomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                redirect();
            }
        }, SPLASH_SCREEN);

    }

    private void redirect() {
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
        finish();
    }
}