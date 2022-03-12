package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginpage.DBObject.AppDatabase;
import com.example.loginpage.DBObject.Ride;
import com.example.loginpage.DBObject.RideDao;
import com.example.loginpage.DBObject.User;
import com.example.loginpage.DBObject.UserDao;
import com.example.loginpage.admin.AdminMainPage;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private boolean IsLoginPageActive = true;
    private View loginFragment;
    private View registerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login_);
    }

    public void switchFragment(View view){

        if(IsLoginPageActive) {

            loginFragment = findViewById(R.id.fragment_Login);

            Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.enter_right_to_left);
       //     Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotation_left_to_right);
            loginFragment.startAnimation(animation);

            setContentView(R.layout.fragment_register);
            IsLoginPageActive=false;

        } else{

            registerFragment = findViewById(R.id.fragment_Register);

            Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.exit_left_to_right);
        //    Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotation_left_to_right);
            registerFragment.startAnimation(animation);

            setContentView(R.layout.fragment_login_);
            IsLoginPageActive=true;
        }
    }

    public void register(View view){
        Toast toast = Toast.makeText(this,"Account created", Toast.LENGTH_LONG );
        toast.show();
    }


    public void logIn(View view){


        EditText editEmail = findViewById(R.id.email_login);
        EditText editPassword = findViewById(R.id.password_login);

        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        AppDatabase db = AppDatabase.getAppDateBase(this);
        UserDao userDao = db.userDao();
        RideDao rideDao = db.rideDao();
        User hugo = new User("admin","123","hugo","vouillamoz","rue lalalal","liddes",1945)  ;
        Ride ride1 = new Ride("Super balade avec mon bon gros étalon",2.5,1.5,4,"Martigny");
        Ride ride2 = new Ride("Enorme balade avec beaucoup de caillou partout youhou",3.4,2.7,1,"Sierre");
        Ride ride3 = new Ride("Wouhou le cheval c'est génial on va si vite aller huuuuuuuu petit tonnerre",48,24,19,"Liddes");

        List<Ride> rides = rideDao.getAll();
        if(rides.size() == 0){
            rideDao.instert(ride1);
            rideDao.instert(ride2);
            rideDao.instert(ride3);
        }
        List<User> users = userDao.getAll();
        if(users.size() == 0){
            userDao.instert(hugo);
            users = userDao.getAll();
        }



        for(int i = 0; i < users.size(); i++){
            if(email.equals(users.get(i).email) && password.equals(users.get(i).password)){
                Intent intentUser = new Intent(this, UserMainPage.class);
                startActivity(intentUser);
            }else{
                editEmail.setError("Wrong Credentials");
                editPassword.setError("Wrong Credentials");
            }
        }




//        if (email.equals("user") && password.equals("123")) {
//            Intent intentUser = new Intent(this, UserMainPage.class);
//            startActivity(intentUser);
//        }
//        if (email.equals("admin") && password.equals("123")) {
//                Intent intentAdmin = new Intent(this, AdminMainPage.class);
//                startActivity(intentAdmin);
//        }
//        else {
//            editEmail.setError("Wrong Credentials");
//            editPassword.setError("Wrong Credentials");
//        }
    }
}