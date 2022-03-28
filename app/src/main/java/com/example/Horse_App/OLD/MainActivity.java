//package com.example.Horse_App.OLD;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.example.Horse_App.Database.AppDatabase;
//import com.example.Horse_App.Database.Entity.User;
//import com.example.Horse_App.Database.DAO.UserDao;
//import com.example.Horse_App.Database.repository.UserRepository;
//
//public class MainActivity extends AppCompatActivity {
//
//    private boolean IsLoginPageActive = true;
//    private UserRepository repository;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setTitle("LoginPage");
//        setContentView(R.layout.fragment_login_page);
//
//     //   repository = ((BaseApp) getApplication()).getUserRepository();
//
//        Button registerButton = findViewById(R.id.buttonSignup);
//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                RegisterFragment registerFragment = new RegisterFragment();
//                getSupportFragmentManager().beginTransaction()
//                        .add(R.id.login_page_fragment_container, registerFragment)
//                        .commit();
//            }
//        });
//
//    }
//
//    public void switchFragment(View view) {
//
//        if (IsLoginPageActive) {
//
//            View loginFragment = findViewById(R.id.fragment_Login);
//
//            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.enter_right_to_left);
//            loginFragment.startAnimation(animation);
//
//            setContentView(R.layout.fragment_register);
//            IsLoginPageActive = false;
//
//        } else {
//            View registerFragment = findViewById(R.id.fragment_Register);
//
//            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.exit_left_to_right);
//            registerFragment.startAnimation(animation);
//
//            setContentView(R.layout.fragment_login_page);
//            IsLoginPageActive = true;
//        }
//    }
//
//    public void register(View view) {
//        //Get all data from the edit text fields and set the value into variables
//        EditText editText_firstname = findViewById(R.id.firstname_register);
//        String firstname = editText_firstname.getText().toString();
//
//        EditText editText_lastname = findViewById(R.id.lastname_register);
//        String lastname = editText_lastname.getText().toString();
//
//        EditText editText_address = findViewById(R.id.address_register);
//        String address = editText_address.getText().toString();
//
//        EditText editText_city = findViewById(R.id.city_register);
//        String city = editText_city.getText().toString();
//
//        EditText editText_npa = findViewById(R.id.post_code_register);
//        String str_npa = editText_npa.getText().toString();
//        int npa;
//        if (!str_npa.isEmpty())
//            npa = Integer.parseInt(str_npa);
//        else
//            npa = 0;
//
//        EditText editText_email = findViewById(R.id.email_register);
//        String email = editText_email.getText().toString();
//
//        EditText editText_password = findViewById(R.id.password_register);
//        String password = editText_password.getText().toString();
//
//        EditText editText_conf_password = findViewById(R.id.confirm_password_register);
//        String confirm_password = editText_conf_password.getText().toString();
//
//        //Create an instance of the database
//        AppDatabase db = AppDatabase.getAppDateBase(this);
//        UserDao userDao = db.userDao();
//
//        //Verify that all fields are not empty
//        if (!firstname.isEmpty() && !lastname.isEmpty() && !address.isEmpty() && !city.isEmpty() && !str_npa.isEmpty() && !email.isEmpty()) {
//
//            //Verify that password and confirm password fields are the same
//            if (password.equals(confirm_password)) {
//
//                //Insert a new User into the database
//                User newUser = new User(email, password, firstname, lastname, "0795949");
//                userDao.insert(newUser);
//
//                //Set a toast for the connection
//                Toast toast = Toast.makeText(this, "Account created", Toast.LENGTH_LONG);
//                toast.show();
//
//                //Redirect on login page
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//
//            } else {
//                editText_password.setError("Passwords don't match");
//                editText_conf_password.setError("Passwords don't match");
//            }
//
//            Toast toast = Toast.makeText(this, "One field is empty", Toast.LENGTH_LONG);
//            toast.show();
//        }
//    }
//
//    public void logIn(View view) {
//
//        EditText editEmail = findViewById(R.id.email_login);
//        EditText editPassword = findViewById(R.id.password_login);
//
//        String email = editEmail.getText().toString();
//        String password = editPassword.getText().toString();
//
//    }
//}