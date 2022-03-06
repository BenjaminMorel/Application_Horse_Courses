package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PurchaseCourse extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_course);

        Button buttonMaps = findViewById(R.id.button_maps);

        buttonMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               loadMap(view);
            }
        });
    }

    public void loadMap(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }


}
