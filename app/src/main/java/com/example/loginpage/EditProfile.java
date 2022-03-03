package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EditProfile extends AppCompatActivity {

    private String firstname = "hugo";
    private String lastname = "Vouillamoz";
    private String address = "Rue du chateau";
    private String city = "Liddes";
    private String email = "hugo@tst.ch";
    private int npa = 1945;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setTextValue();
    }

    public void setTextValue(){
        ((TextView) findViewById(R.id.firstname_edit)).setText(firstname);
        ((TextView) findViewById(R.id.lastname_edit)).setText(lastname);
        ((TextView) findViewById(R.id.address_edit)).setText(address);
        ((TextView) findViewById(R.id.email_edit)).setText(email);
        ((TextView) findViewById(R.id.city_edit)).setText(city);
        ((TextView) findViewById(R.id.npa_edit)).setText(String.valueOf(npa));
    }
}