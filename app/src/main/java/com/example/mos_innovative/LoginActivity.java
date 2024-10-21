////////////////package com.example.mos_innovative;
////////////////import android.os.Bundle;
////////////////import android.view.View;
////////////////import android.widget.Button;
////////////////import android.widget.CheckBox;
////////////////import android.widget.EditText;
////////////////import android.widget.TextView;
////////////////import androidx.appcompat.app.AppCompatActivity;
////////////////import android.content.Intent;
////////////////
////////////////public class LoginActivity extends AppCompatActivity {
////////////////
////////////////    @Override
////////////////    protected void onCreate(Bundle savedInstanceState) {
////////////////        super.onCreate(savedInstanceState);
////////////////        setContentView(R.layout.activity_login);
////////////////
////////////////        EditText editTextEmail = findViewById(R.id.editTextEmail);
////////////////        EditText editTextPassword = findViewById(R.id.editTextPassword);
////////////////        CheckBox checkBoxRememberMe = findViewById(R.id.checkBoxRememberMe);
////////////////        Button buttonLogin = findViewById(R.id.buttonLogin);
////////////////        TextView textViewSignUp = findViewById(R.id.textViewSignUp);
////////////////
////////////////        buttonLogin.setOnClickListener(new View.OnClickListener() {
////////////////            @Override
////////////////            public void onClick(View v) {
////////////////                // Implement login logic here
////////////////            }
////////////////        });
////////////////
////////////////        textViewSignUp.setOnClickListener(new View.OnClickListener() {
////////////////            @Override
////////////////            public void onClick(View v) {
////////////////                // Navigate to signup activity
////////////////                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
////////////////            }
////////////////        });
////////////////    }
////////////////}
//////////////
//////////////
////////package com.example.mos_innovative;
////////
////////import android.content.SharedPreferences;
////////import android.os.Bundle;
////////import android.view.View;
////////import android.widget.Button;
////////import android.widget.CheckBox;
////////import android.widget.EditText;
////////import android.widget.TextView;
////////import android.widget.Toast;
////////
////////import androidx.appcompat.app.AppCompatActivity;
////////import android.content.Intent;
////////
////////public class LoginActivity extends AppCompatActivity {
////////
////////    private static final String PREFS_NAME = "UserPrefs"; // Name of the SharedPreferences file
////////
////////    @Override
////////    protected void onCreate(Bundle savedInstanceState) {
////////        super.onCreate(savedInstanceState);
////////        setContentView(R.layout.activity_login);
////////
////////        EditText editTextEmail = findViewById(R.id.editTextEmail);
////////        EditText editTextPassword = findViewById(R.id.editTextPassword);
////////        CheckBox checkBoxRememberMe = findViewById(R.id.checkBoxRememberMe);
////////        Button buttonLogin = findViewById(R.id.buttonLogin);
////////        TextView textViewSignUp = findViewById(R.id.textViewSignUp);
////////
////////        buttonLogin.setOnClickListener(new View.OnClickListener() {
////////            @Override
////////            public void onClick(View v) {
////////                // Get the input values
////////                String email = editTextEmail.getText().toString().trim();
////////                String password = editTextPassword.getText().toString().trim();
////////
////////                // Retrieve stored user data from SharedPreferences
////////                SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
////////                String storedEmail = sharedPreferences.getString("email", null);
////////                String storedPassword = sharedPreferences.getString("password", null);
////////
////////                // Check if the input matches the stored data
////////                if (email.equals(storedEmail) && password.equals(storedPassword)) {
////////                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
////////                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
////////                    intent.putExtra("USERNAME", storedEmail); // Pass username to HomeActivity
////////                    // Navigate to HomeActivity or another activity after successful login
////////                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
////////                    finish(); // Close the LoginActivity
////////                } else {
////////                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
////////                }
////////            }
////////        });
////////
////////        textViewSignUp.setOnClickListener(new View.OnClickListener() {
////////            @Override
////////            public void onClick(View v) {
////////                // Navigate to signup activity
////////                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
////////            }
////////        });
////////    }
////////}
//////
//////
//////
//////package com.example.mos_innovative;
//////
//////import android.content.SharedPreferences;
//////import android.os.Bundle;
//////import android.view.View;
//////import android.widget.Button;
//////import android.widget.EditText;
//////import android.widget.TextView;
//////import android.widget.Toast;
//////
//////import androidx.appcompat.app.AppCompatActivity;
//////import android.content.Intent;
//////
//////public class LoginActivity extends AppCompatActivity {
//////
//////    private static final String PREFS_NAME = "UserPrefs"; // Name of the SharedPreferences file
//////
//////    @Override
//////    protected void onCreate(Bundle savedInstanceState) {
//////        super.onCreate(savedInstanceState);
//////        setContentView(R.layout.activity_login);
//////
//////        EditText editTextEmail = findViewById(R.id.editTextEmail);
//////        EditText editTextPassword = findViewById(R.id.editTextPassword);
//////        Button buttonLogin = findViewById(R.id.buttonLogin);
//////        TextView textViewSignUp = findViewById(R.id.textViewSignUp);
//////
//////        buttonLogin.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View v) {
//////                // Get the input values
//////                String email = editTextEmail.getText().toString().trim();
//////                String password = editTextPassword.getText().toString().trim();
//////
//////                // Retrieve stored user data from SharedPreferences
//////                SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//////                String storedEmail = sharedPreferences.getString("email", null);
//////                String storedPassword = sharedPreferences.getString("password", null);
//////
//////                // Check if the input matches the stored data
//////                if (email.equals(storedEmail) && password.equals(storedPassword)) {
//////                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
//////                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//////                    intent.putExtra("USERNAME", storedEmail); // Pass username to HomeActivity
//////                    startActivity(intent);
//////                    finish(); // Close the LoginActivity
//////                } else {
//////                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
//////                }
//////            }
//////        });
//////
//////        textViewSignUp.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View v) {
//////                // Navigate to signup activity
//////                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
//////            }
//////        });
//////    }
//////}
////
////
////package com.example.mos_innovative;
////
////import android.content.Intent;
////import android.content.SharedPreferences;
////import android.os.Bundle;
////import android.view.View;
////import android.widget.Button;
////import android.widget.EditText;
////import android.widget.TextView;
////import android.widget.Toast;
////
////import androidx.appcompat.app.AppCompatActivity;
////
////public class LoginActivity extends AppCompatActivity {
////
////    private static final String PREFS_NAME = "userPrefs"; // Use the same name as in SignUpActivity
////    private EditText editTextEmail, editTextPassword;
////    private Button buttonLogin;
////    private TextView textViewSignUp;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_login);
////
////        editTextEmail = findViewById(R.id.editTextEmail);
////        editTextPassword = findViewById(R.id.editTextPassword);
////        buttonLogin = findViewById(R.id.buttonLogin);
////        textViewSignUp = findViewById(R.id.textViewSignUp);
////
////        buttonLogin.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                String email = editTextEmail.getText().toString().trim();
////                String password = editTextPassword.getText().toString().trim();
////
////                // Retrieve stored user data from SharedPreferences
////                SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
////                String storedEmail = sharedPreferences.getString("email", null);
////                String storedPassword = sharedPreferences.getString("password", null);
////
////                // Validate input
////                if (email.isEmpty() || password.isEmpty()) {
////                    Toast.makeText(LoginActivity.this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show();
////                    return;
////                }
////
////                // Check if the input matches the stored data
////                if (email.equals(storedEmail) && password.equals(storedPassword)) {
////                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
////                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
////                    intent.putExtra("USERNAME", storedEmail); // Pass email to HomeActivity
////                    startActivity(intent);
////                    finish(); // Close the LoginActivity
////                } else {
////                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
////                }
////            }
////        });
////
////        textViewSignUp.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                // Navigate to signup activity
////                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
////            }
////        });
////    }
////}
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
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class LoginActivity extends AppCompatActivity {
//
//    private static final String PREFS_NAME = "userPrefs"; // Use the same name as in SignUpActivity
//    private EditText editTextEmail, editTextPassword;
//    private Button buttonLogin;
//    private TextView textViewSignUp;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        editTextEmail = findViewById(R.id.editTextEmail);
//        editTextPassword = findViewById(R.id.editTextPassword);
//        buttonLogin = findViewById(R.id.buttonLogin);
//        textViewSignUp = findViewById(R.id.textViewSignUp);
//
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = editTextEmail.getText().toString().trim();
//                String password = editTextPassword.getText().toString().trim();
//
//                // Retrieve stored user data from SharedPreferences
//                SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//                String storedEmail = sharedPreferences.getString("email");
//                String storedPassword = sharedPreferences.getString("password");
//
//                // Validate input
//                if (email.isEmpty() || password.isEmpty()) {
//                    Toast.makeText(LoginActivity.this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Check if the input matches the stored data
//                if (email.equals(storedEmail) && password.equals(storedPassword)) {
//                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                    intent.putExtra("USERNAME", storedEmail); // Pass email to HomeActivity
//                    startActivity(intent);
//                    finish(); // Close the LoginActivity
//                } else {
//                    // Display stored email and password on login failure
//                    String message = "Stored Email: " + storedEmail + ", Stored Password: " + storedPassword;
//                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//        textViewSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Navigate to signup activity
//                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
//            }
//        });
//    }
//}



package com.example.mos_innovative;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "UserPrefs"; // Name of the SharedPreferences file
    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private TextView textViewSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Set the layout for the activity

        // Initialize UI elements
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewSignUp = findViewById(R.id.textViewSignUp);

        // Set click listener for the Login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input values
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Retrieve stored user data from SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                String storedEmail = sharedPreferences.getString("email", null);
                String storedPassword = sharedPreferences.getString("password", null);

                // Validate input
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if the input matches the stored data
                if (email.equals(storedEmail) && password.equals(storedPassword)) {
                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("USERNAME", storedEmail); // Pass email to HomeActivity
                    startActivity(intent);
                    finish(); // Close the LoginActivity
                } else {
                    // Display an error message if the login fails
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set click listener for the Sign Up TextView
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to signup activity
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }
}
