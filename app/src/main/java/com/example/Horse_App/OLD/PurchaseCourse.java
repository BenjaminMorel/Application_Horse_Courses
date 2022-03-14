package com.example.Horse_App.OLD;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PurchaseCourse extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_course);
    }

    public void loadMap(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }


}
