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

/**
 * Class that manages the Splash screen activity with animations, before the Login Page
 */
public class SplashScreen extends AppCompatActivity {

    Animation topAnimation, bottomAnimation;
    ImageView image;
    TextView title, description;

    /**
     * Manages the view and animations
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the window to be full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set the view to the activity splash_screen
        setContentView(R.layout.activity_splash_screen);

        // Load the animation created
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        // Get the elements from the activity
        image = findViewById(R.id.splash_image);
        title = findViewById(R.id.splash_title);
        description = findViewById(R.id.splash_description);

        // Set animation for the image
        image.setAnimation(topAnimation);
        image.setAnimation(bottomAnimation);

        // Manages the delay for the animation and then redirect to Login page
        int SPLASH_SCREEN = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Redirect to Login Page
                redirect();
            }
        }, SPLASH_SCREEN);
    }

    /**
     * Once the animation is finished, this method is called to generate the Login Class page
     */
    private void redirect() {
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
        finish();
    }
}