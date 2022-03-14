package com.example.Horse_App.OLD;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.Horse_App.Database.AppDatabase;
import com.example.Horse_App.Database.Entity.User;
import com.example.Horse_App.Database.DAO.UserDao;

public class EditProfile extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setTextValue();
    }

    public void setTextValue(){
        AppDatabase db = AppDatabase.getAppDateBase(this);
        UserDao userDao = db.userDao();
   //     user = userDao.findByID(1);
        Button saveButton = findViewById(R.id.button_save_edit);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });
        ((TextView) findViewById(R.id.firstname_edit)).setText(user.firstName);
        ((TextView) findViewById(R.id.lastname_edit)).setText(user.lastName);
        ((TextView) findViewById(R.id.email_edit)).setText(user.email);
    }


    public void updateUser(){
        AppDatabase db = AppDatabase.getAppDateBase(this);
        UserDao userDao = db.userDao();


        EditText editFirstname = findViewById(R.id.firstname_edit);
        EditText editLastname = findViewById(R.id.lastname_edit);
        EditText editaddress = findViewById(R.id.address_edit);
        EditText editemail = findViewById(R.id.email_edit);
        EditText editPassword = findViewById(R.id.new_password_edit);
        EditText editConfirmPassword = findViewById(R.id.confirm_new_password_edit);
        EditText editnpa = findViewById(R.id.npa_edit);
        EditText editCity = findViewById(R.id.city_edit);

        String firstname = editFirstname.getText().toString();
        String lastname = editLastname.getText().toString();
        String address = editaddress.getText().toString();
        String mail = editemail.getText().toString();
        String password = editPassword.getText().toString();
        String confirmPassword = editConfirmPassword.getText().toString();
        String npa = editnpa.getText().toString();
        String city = editCity.getText().toString();

        int postCode = Integer.parseInt(npa);

        User user = new User(mail,password,firstname,lastname,"84838388");
        //TODO set a real session token
        user.setUserID(1);

    }


}