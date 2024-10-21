//////package com.example.mos_innovative;
//////
//////import android.os.Bundle;
//////import android.view.View;
//////import android.widget.Button;
//////import android.widget.EditText;
//////import android.widget.TextView;
//////import androidx.appcompat.app.AppCompatActivity;
//////import android.content.Intent;
//////
//////public class SignUpActivity extends AppCompatActivity {
//////
//////    @Override
//////    protected void onCreate(Bundle savedInstanceState) {
//////        super.onCreate(savedInstanceState);
//////        setContentView(R.layout.activity_signup);
//////
//////        EditText editTextUsername = findViewById(R.id.editTextUsername);
//////        EditText editTextEmail = findViewById(R.id.editTextEmail);
//////        EditText editTextPassword = findViewById(R.id.editTextPassword);
//////        Button buttonSignUp = findViewById(R.id.buttonSignUp);
//////        TextView textViewLogin = findViewById(R.id.textViewLogin);
//////
//////        buttonSignUp.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View v) {
//////                // Implement signup logic here
//////            }
//////        });
//////
//////        textViewLogin.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View v) {
//////                // Navigate to login activity
//////                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
//////            }
//////        });
//////    }
//////}
//////
////
////
////
package com.example.mos_innovative;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class SignUpActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "UserPrefs"; // Name of the SharedPreferences file

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup); // Set the layout for the activity

        // Initialize UI elements
        EditText editTextUsername = findViewById(R.id.editTextUsername);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonSignUp = findViewById(R.id.buttonSignUp);
        TextView textViewLogin = findViewById(R.id.textViewLogin);

        // Set click listener for the Sign Up button
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input values
                String username = editTextUsername.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Save the user data in SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", username);
                editor.putString("email", email);
                editor.putString("password", password);
                editor.apply(); // Commit the changes

                // Show a Toast message
                Toast.makeText(SignUpActivity.this, "User signed up successfully!", Toast.LENGTH_SHORT).show();

                // Optionally, navigate to login activity
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish(); // Close the SignUpActivity
            }
        });

        // Set click listener for the Login TextView
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to login activity
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });
    }
}



//package com.example.mos_innovative;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import android.content.Intent;
//
//public class SignUpActivity extends AppCompatActivity {
//
//    private static final String PREFS_NAME = "UserPrefs"; // Name of the SharedPreferences file
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
//
//        EditText editTextUsername = findViewById(R.id.editTextUsername);
//        EditText editTextEmail = findViewById(R.id.editTextEmail);
//        EditText editTextPassword = findViewById(R.id.editTextPassword);
//        Button buttonSignUp = findViewById(R.id.buttonSignUp);
//        TextView textViewLogin = findViewById(R.id.textViewLogin);
//
//        buttonSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Get the input values
//                String username = editTextUsername.getText().toString().trim();
//                String email = editTextEmail.getText().toString().trim();
//                String password = editTextPassword.getText().toString().trim();
//
//                // Save the user data to SharedPreferences
//                SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("username", username);
//                editor.putString("email", email);
//                editor.putString("password", password);
//                editor.apply(); // Save the changes
//
//                Toast.makeText(SignUpActivity.this, "Sign up successful!", Toast.LENGTH_SHORT).show();
//
//                // Navigate to login activity
//                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
//                finish(); // Close the SignUpActivity
//            }
//        });
//
//        textViewLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Navigate to login activity
//                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
//            }
//        });
//    }
//}
//
//
//
//package com.example.mos_innovative;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class SignUpActivity extends AppCompatActivity {
//
//    private EditText editTextUsername, editTextEmail, editTextPassword;
//    private Button buttonSignUp;
//    private TextView textViewLogin;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
//
//        editTextUsername = findViewById(R.id.editTextUsername);
//        editTextEmail = findViewById(R.id.editTextEmail);
//        editTextPassword = findViewById(R.id.editTextPassword);
//        buttonSignUp = findViewById(R.id.buttonSignUp);
//        textViewLogin = findViewById(R.id.textViewLogin);
//
//        buttonSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Implement signup logic here
//                String username = editTextUsername.getText().toString().trim();
//                String email = editTextEmail.getText().toString().trim();
//                String password = editTextPassword.getText().toString().trim();
//
//                // Basic validation
//                if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
//                    Toast.makeText(SignUpActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
//                } else {
//                    // Save username to SharedPreferences
//                    SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("username", username);
//                    editor.apply();
//
//                    // Navigate to Home Activity
//                    Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
//                    startActivity(intent);
//                    finish(); // Close the SignUpActivity
//                }
//            }
//        });
//
//        textViewLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Navigate to login activity
//                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
//            }
//        });
//    }
//}



//package com.example.mos_innovative;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class SignUpActivity extends AppCompatActivity {
//
//    private EditText editTextUsername, editTextEmail, editTextPassword;
//    private Button buttonSignUp;
//    private TextView textViewLogin;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
//
//        editTextUsername = findViewById(R.id.editTextUsername);
//        editTextEmail = findViewById(R.id.editTextEmail);
//        editTextPassword = findViewById(R.id.editTextPassword);
//        buttonSignUp = findViewById(R.id.buttonSignUp);
//        textViewLogin = findViewById(R.id.textViewLogin);
//
//        buttonSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String username = editTextUsername.getText().toString().trim();
//                String email = editTextEmail.getText().toString().trim();
//                String password = editTextPassword.getText().toString().trim();
//
//                // Basic validation
//                if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
//                    Toast.makeText(SignUpActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
//                } else {
//                    // Save username to SharedPreferences
//                    SharedPreferences sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("username", username);
//                    editor.apply();
//
//                    // Navigate to Login Activity
//                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
//                    finish(); // Close the SignUpActivity
//                }
//            }
//        });
//
//        textViewLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Navigate to login activity
//                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
//            }
//        });
//    }
//}
