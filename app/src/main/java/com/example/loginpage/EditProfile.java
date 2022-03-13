package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginpage.DBObject.AppDatabase;
import com.example.loginpage.DBObject.RideDao;
import com.example.loginpage.DBObject.User;
import com.example.loginpage.DBObject.UserDao;

import java.util.ArrayList;
import java.util.List;

public class EditProfile extends AppCompatActivity {

//    private String firstname = "hugo";
//    private String lastname = "Vouillamoz";
//    private String address = "Rue du chateau";
//    private String city = "Liddes";
//    private String email = "hugo@tst.ch";
//    private int npa = 1945;
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
        user = userDao.findByID(1);
        Button saveButton = findViewById(R.id.button_save_edit);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });
        ((TextView) findViewById(R.id.firstname_edit)).setText(user.firstName);
        ((TextView) findViewById(R.id.lastname_edit)).setText(user.lastName);
        ((TextView) findViewById(R.id.address_edit)).setText(user.address);
        ((TextView) findViewById(R.id.email_edit)).setText(user.email);
        ((TextView) findViewById(R.id.city_edit)).setText(user.city);
        ((TextView) findViewById(R.id.npa_edit)).setText(String.valueOf(user.npa));
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

        User user = new User(mail,password,firstname,lastname,address,city,postCode);
        //TODO set a real session token
        user.setUserID(1);

        if(user.password.isEmpty()){
            userDao.updateSpecificRow(user.userID, user.email, user.firstName, user.lastName, user.address, user.city, user.npa);
            Intent intent = new Intent(this, UserMainPage.class);
            startActivity(intent);
            Toast toast = Toast.makeText(this,"Account details were changed", Toast.LENGTH_LONG );
            toast.show();
        }else{
            EditText editOldPassword = findViewById(R.id.old_password_edit);
            String oldPassword = editOldPassword.getText().toString();
            User control_user = userDao.findByID(1);
            if(control_user.password.equals(oldPassword)){
                if(confirmPassword.equals(password)){
                    userDao.update(user);
                    Intent intent = new Intent(this, UserMainPage.class);
                    startActivity(intent);
                    Toast toast = Toast.makeText(this,"Account details were changed", Toast.LENGTH_LONG );
                    toast.show();
                }else{
                    editPassword.setError("Password are not the same !");
                    editConfirmPassword.setError("Password are not the same !");
                }
            }else{
               editOldPassword.setError("Old password is not correct !");
            }
        }

    }


}