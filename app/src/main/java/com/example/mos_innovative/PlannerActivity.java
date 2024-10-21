package com.example.mos_innovative;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class PlannerActivity extends AppCompatActivity {

    private ListView locationListView, dressListView, menDressListView;
    private EditText guestCountEditText;
    private Button calculateButton, cateringButton;
    private TextView welcomeText, estimatedCostTextView;
    private SpinnerItem selectedLocation, selectedDress, selectedMenDress;
    private static final String PREFS_NAME = "UserPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);

        initializeViews();
        setWelcomeMessage();
        setupListViews();
        setupListeners();
    }

    private void initializeViews() {
        welcomeText = findViewById(R.id.welcomeText);
        locationListView = findViewById(R.id.locationListView);
        dressListView = findViewById(R.id.dressListView);
        menDressListView = findViewById(R.id.menDressListView);
        guestCountEditText = findViewById(R.id.editTextGuestCount);
        calculateButton = findViewById(R.id.buttonCalculate);
        cateringButton = findViewById(R.id.buttonCatering);
        estimatedCostTextView = findViewById(R.id.textViewEstimatedCost);
    }

    private void setWelcomeMessage() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String username = preferences.getString("username", "User");
        welcomeText.setText("Welcome, " + username + "!");
    }

    private void setupListViews() {
        List<SpinnerItem> locations = new ArrayList<>();
        locations.add(new SpinnerItem("Beach ", 500, R.drawable.beach));
        locations.add(new SpinnerItem("Garden ", 300, R.drawable.garden));
        locations.add(new SpinnerItem("Hall ", 700, R.drawable.hall));
        locations.add(new SpinnerItem("Home ", 1000, R.drawable.home));

        List<SpinnerItem> dresses = new ArrayList<>();
        dresses.add(new SpinnerItem("Simple Dress ", 200, R.drawable.simpledress));
        dresses.add(new SpinnerItem("Elegant Dress ", 500, R.drawable.elegantdress));
        dresses.add(new SpinnerItem("Designer Dress ", 1000, R.drawable.designerdress));
        dresses.add(new SpinnerItem("Custom Dress ", 800, R.drawable.customdress));

        List<SpinnerItem> menDresses = new ArrayList<>();
        menDresses.add(new SpinnerItem("Classic Suit ", 300, R.drawable.m_simpledress));
        menDresses.add(new SpinnerItem("Elegant Suit ", 600, R.drawable.m_elegantdress));
        menDresses.add(new SpinnerItem("Designer Wear ", 400, R.drawable.m_designerdress));
        menDresses.add(new SpinnerItem("Custom Suit ", 200, R.drawable.m_customdress));

        CustomAdapter locationAdapter = new CustomAdapter(this, locations);
        locationListView.setAdapter(locationAdapter);

        CustomAdapter dressAdapter = new CustomAdapter(this, dresses);
        dressListView.setAdapter(dressAdapter);

        CustomAdapter menDressAdapter = new CustomAdapter(this, menDresses);
        menDressListView.setAdapter(menDressAdapter);
    }

    private void setupListeners() {
        locationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedLocation = (SpinnerItem) parent.getItemAtPosition(position);
            }
        });

        dressListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedDress = (SpinnerItem) parent.getItemAtPosition(position);
            }
        });

        menDressListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMenDress = (SpinnerItem) parent.getItemAtPosition(position);
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCost();
            }
        });

        cateringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlannerActivity.this, CateringActivity.class));
            }
        });
    }

    private void calculateCost() {
        String guestCountString = guestCountEditText.getText().toString();

        if (guestCountString.isEmpty() || selectedLocation == null || selectedDress == null || selectedMenDress == null) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int guestCount = Integer.parseInt(guestCountString);
            if (guestCount <= 0) {
                Toast.makeText(this, "Guest count must be greater than zero", Toast.LENGTH_SHORT).show();
                return;
            }

            double estimatedCost = calculateWeddingCost(selectedLocation, selectedDress, selectedMenDress, guestCount);
            String costDetails = "Selected Location: " + selectedLocation.getName() + "\n" +
                    "Selected Dress: " + selectedDress.getName() + "\n" +
                    "Selected Men's Dress: " + selectedMenDress.getName() + "\n" +
                    "Base Cost: $1000\n" +
                    "Location Cost: $" + selectedLocation.getCost() + "\n" +
                    "Dress Cost: $" + selectedDress.getCost() + "\n" +
                    "Men's Dress Cost: $" + selectedMenDress.getCost() + "\n" +
                    "Accommodation Cost per person: $50\n" +
                    "Total Guest Count: " + guestCount + "\n" +
                    "Estimated Cost: $" + estimatedCost;

            estimatedCostTextView.setText(costDetails);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid guest count", Toast.LENGTH_SHORT).show();
        }
    }

    private double calculateWeddingCost(SpinnerItem location, SpinnerItem dress, SpinnerItem menDress, int guestCount) {
        double baseCost = 1000;
        double locationCost = location.getCost();
        double dressCost = dress.getCost();
        double menDressCost = menDress.getCost();
        return baseCost + locationCost + dressCost + menDressCost + (guestCount * 50);
    }
}
