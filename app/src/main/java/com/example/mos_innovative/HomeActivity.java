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

public class HomeActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "UserPrefs"; // Name of the SharedPreferences file

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Get the username passed from LoginActivity or retrieved from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "User"); // Default to "User" if not found

        // Display the username on the HomeActivity
        TextView welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome, " + username + "!");

        // Initialize EditText fields for bride and groom names
        EditText editTextBrideName = findViewById(R.id.editTextBrideName);
        EditText editTextGroomName = findViewById(R.id.editTextGroomName);

        // Initialize buttons
        Button buttonPlanWedding = findViewById(R.id.buttonPlanWedding);
        Button buttonCatering = findViewById(R.id.buttonCatering);
        Button buttonInvitations = findViewById(R.id.buttonInvitations);
        Button buttonWeddingTheme = findViewById(R.id.buttonWeddingTheme); // New button
        Button buttonExit = findViewById(R.id.buttonExit);

        // Set up onClickListener for Plan Wedding button
        buttonPlanWedding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to PlannerActivity
                startActivity(new Intent(HomeActivity.this, PlannerActivity.class));
            }
        });

        // Set up onClickListener for Catering button
        buttonCatering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to CateringActivity
                startActivity(new Intent(HomeActivity.this, CateringActivity.class));
            }
        });

        // Set up onClickListener for Wedding Theme button
        buttonWeddingTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to WeddingThemeActivity
                startActivity(new Intent(HomeActivity.this, WeddingThemeActivity.class));
            }
        });

        // Set up onClickListener for Invitations button
        buttonInvitations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get bride's and groom's names from EditText
                String brideName = editTextBrideName.getText().toString().trim();
                String groomName = editTextGroomName.getText().toString().trim();

                // Check if names are not empty
                if (brideName.isEmpty() || groomName.isEmpty()) {
                    Toast.makeText(HomeActivity.this, "Please enter both names", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create intent to start InvitationsActivity
                Intent intent = new Intent(HomeActivity.this, InvitationsActivity.class);
                intent.putExtra("BRIDE_NAME", brideName);
                intent.putExtra("GROOM_NAME", groomName);
                startActivity(intent);
            }
        });

        // Set up onClickListener for Exit button
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Logging out...", Toast.LENGTH_SHORT).show();
                // Finish the activity and go back to LoginActivity
                finish();
            }
        });
    }
}
