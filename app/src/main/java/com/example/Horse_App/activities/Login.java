package com.example.Horse_App.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.Entity.RideEntity;
import com.example.Horse_App.Database.Util.OnAsyncEventListener;
import com.example.Horse_App.Database.repository.RideRepository;
import com.example.Horse_App.Database.repository.UserRepository;
import com.example.Horse_App.R;

public class Login extends AppCompatActivity {

    private EditText emailView, passwordView;
    private UserRepository userRepository;
    private RideRepository rideRepository;

    /**
     * Method to display the page and get the reference of the textView
     * We also create the onClickListener on the register button
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userRepository = ((BaseApp) getApplicationContext()).getUserRepository();

        // Set up login form
        emailView = findViewById(R.id.email_login);
        passwordView = findViewById(R.id.password_login);

        // Add the login button with listener
        Button logInButton = findViewById(R.id.button_login);
        logInButton.setOnClickListener(view -> attemptLogin());

        // Add the register button with listener
        Button registerButton = findViewById(R.id.button_register);
        registerButton.setOnClickListener(view -> startActivity(new Intent(Login.this, Register.class)));
//        registerButton.setOnClickListener(view -> DataInitializer());
    }


    /**
     * Method to verify the credentials that have been given
     * If the are correct the main page will be load and the user id stocked on
     * the shared preference, if not the errorMessage is display and the page stay the same
     */
    private void attemptLogin() {

        boolean cancel = false;
        View focusView = null;
        // Reset errors
        emailView.setError(null);
        passwordView.setError(null);

        // Store values for the login attempt
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        // Check for a valid email address
        if (TextUtils.isEmpty(email)) {
            emailView.setError(getString(R.string.field_required));
            focusView = emailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailView.setError(getString(R.string.wrong_credentials));
            focusView = emailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();

        } else {
            userRepository.signIn(email, password, task -> {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(Login.this, MainPage.class);
                    startActivity(intent);
                    emailView.setText("");
                    passwordView.setText("");
                } else {
                    emailView.setError(getString(R.string.wrong_credentials));
                    emailView.requestFocus();
                    passwordView.setText("");
                }
            });
        }
    }
    private boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Method used to initialise our 4 rides. Used only once at the implementation of firebase
     */
    public void DataInitializer(){
        rideRepository = ((BaseApp) getApplication()).getRideRepository();
        RideEntity ride = new RideEntity("Walk in the forest around the village ", 25.2, 4.8, 4, "Liddes", "45.981136/7.189659/45.972979/7.202241/45.973308/7.207658/45.987731/7.189178", "11:00/14:15", 32.50, "@drawable/paysage2");
        RideEntity ride1 = new RideEntity("Walk around the tower of Saillon and in the old village with a great view on the Rhone Valley", 7.8, 2.5, 1, "Saillon", "46.168166/7.177144/46.167106/7.171189/46.161594/7.165012/46.157026/7.155829/46.164446/7.180595", "12:00/15:00", 40.00, "@drawable/saillon");
        RideEntity ride2 = new RideEntity("Walk following the Rhone with a long part in the forest,perfect for beginner", 7.6, 2.5, 2, "Sierre", "46.3011113/7.565139/46.304852/7.578500/46.307543/7.579149/46.304661/7.567815", "14:30/16:30", 25.60, "s");
        RideEntity ride3 = new RideEntity("Walk near the Rhone with a beautiful view on the moutains around Martigny", 10.2, 2.4, 2, "Martigny", "46.109987/7.102460/46.122314/7.129649/46.119117/7.132502/46.107869/7.103133", "13:30/16:30", 45.50, "@drawable/paysage1.jpg");
        rideRepository.insertRide(ride,new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d("1", "createUserWithEmail: success");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("1", "createUserWithEmail: failure", e);
            }
        });
        rideRepository.insertRide(ride1,new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d("1", "createUserWithEmail: success");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("1", "createUserWithEmail: failure", e);
            }
        });
        rideRepository.insertRide(ride2,new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d("1", "createUserWithEmail: success");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("1", "createUserWithEmail: failure", e);
            }
        });
        rideRepository.insertRide(ride3,new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d("1", "createUserWithEmail: success");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("1", "createUserWithEmail: failure", e);
            }
        });
    }

}