//////package com.example.mos_innovative;
//////
//////import android.os.Bundle;
//////import android.view.View;
//////import android.widget.Button;
//////import android.widget.EditText;
//////import android.widget.Spinner;
//////import android.widget.TextView;
//////import android.widget.Toast;
//////
//////import androidx.appcompat.app.AppCompatActivity;
//////import com.example.mos_innovative.model.ThemeRequest;
//////import com.example.mos_innovative.model.ThemeResponse;
//////import com.example.mos_innovative.network.GeminiApi;
//////
//////import retrofit2.Call;
//////import retrofit2.Callback;
//////import retrofit2.Response;
//////import retrofit2.Retrofit;
//////import retrofit2.converter.gson.GsonConverterFactory;
//////
//////public class WeddingThemeActivity extends AppCompatActivity {
//////
//////    private EditText inputColors;
//////    private Spinner inputVenue, inputSeason;
//////    private TextView themeSuggestionText;
//////    private Button btnGetTheme;
//////
//////    @Override
//////    protected void onCreate(Bundle savedInstanceState) {
//////        super.onCreate(savedInstanceState);
//////        setContentView(R.layout.activity_wedding_theme);
//////
//////        inputColors = findViewById(R.id.inputColors);
//////        inputVenue = findViewById(R.id.inputVenue);
//////        inputSeason = findViewById(R.id.inputSeason);
//////        themeSuggestionText = findViewById(R.id.themeSuggestionText);
//////        btnGetTheme = findViewById(R.id.btnGetTheme);
//////
//////        btnGetTheme.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View v) {
//////                // Get user inputs
//////                String colors = inputColors.getText().toString();
//////                String venue = inputVenue.getSelectedItem().toString();
//////                String season = inputSeason.getSelectedItem().toString();
//////
//////                // Validate inputs
//////                if (colors.isEmpty()) {
//////                    Toast.makeText(WeddingThemeActivity.this, "Please enter your color preferences", Toast.LENGTH_SHORT).show();
//////                    return;
//////                }
//////
//////                // Call method to get AI theme suggestions
//////                getAIThemeSuggestions(colors, venue, season);
//////            }
//////        });
//////    }
//////
//////    // Method to get AI-based theme suggestions using Gemini API
//////    private void getAIThemeSuggestions(String colors, String venue, String season) {
//////        // Create Retrofit instance
//////        Retrofit retrofit = new Retrofit.Builder()
//////                .baseUrl("https://generativelanguage.googleapis.com/") // Replace with the actual base URL of Gemini API
//////                .addConverterFactory(GsonConverterFactory.create())
//////                .build();
//////
//////        GeminiApi api = retrofit.create(GeminiApi.class);
//////
//////        // Create the request object
//////        ThemeRequest request = new ThemeRequest(colors, venue, season);
//////
//////        // Make the API call
//////        Call<ThemeResponse> call = api.getThemeSuggestions(request);
//////        call.enqueue(new Callback<ThemeResponse>() {
//////            @Override
//////            public void onResponse(Call<ThemeResponse> call, Response<ThemeResponse> response) {
//////                if (response.isSuccessful() && response.body() != null) {
//////                    // Display the AI-generated theme suggestion
//////                    String themeSuggestion = response.body().getSuggestion();
//////                    themeSuggestionText.setText(themeSuggestion);
//////                    themeSuggestionText.setVisibility(View.VISIBLE);
//////                } else {
//////                    Toast.makeText(WeddingThemeActivity.this, "Failed to get suggestions. Try again!", Toast.LENGTH_SHORT).show();
//////                }
//////            }
//////
//////            @Override
//////            public void onFailure(Call<ThemeResponse> call, Throwable t) {
//////                // Handle API failure
//////                Toast.makeText(WeddingThemeActivity.this, "API call failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
//////            }
//////        });
//////    }
//////}
////
////
////package com.example.mos_innovative;
////
////import android.os.Bundle;
////import android.view.View;
////import android.widget.Button;
////import android.widget.EditText;
////import android.widget.Spinner;
////import android.widget.TextView;
////import android.widget.Toast;
////
////import androidx.appcompat.app.AppCompatActivity;
////import com.example.mos_innovative.model.ThemeRequest;
////import com.example.mos_innovative.model.ThemeResponse;
////import com.example.mos_innovative.network.GeminiApi;
////
////import retrofit2.Call;
////import retrofit2.Callback;
////import retrofit2.Response;
////import retrofit2.Retrofit;
////import retrofit2.converter.gson.GsonConverterFactory;
////
////public class WeddingThemeActivity extends AppCompatActivity {
////
////    private EditText inputColors;
////    private Spinner inputVenue, inputSeason;
////    private TextView themeSuggestionText;
////    private Button btnGetTheme;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_wedding_theme);
////
////        inputColors = findViewById(R.id.inputColors);
////        inputVenue = findViewById(R.id.inputVenue);
////        inputSeason = findViewById(R.id.inputSeason);
////        themeSuggestionText = findViewById(R.id.themeSuggestionText);
////        btnGetTheme = findViewById(R.id.btnGetTheme);
////
////        btnGetTheme.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                // Get user inputs
////                String colors = inputColors.getText().toString().trim();
////                String venue = inputVenue.getSelectedItem().toString();
////                String season = inputSeason.getSelectedItem().toString();
////
////                // Validate inputs
////                if (colors.isEmpty()) {
////                    Toast.makeText(WeddingThemeActivity.this, "Please enter your color preferences", Toast.LENGTH_SHORT).show();
////                    return;
////                }
////
////                if (venue.equals("Select Venue")) { // Assuming this is the default item in the spinner
////                    Toast.makeText(WeddingThemeActivity.this, "Please select a venue", Toast.LENGTH_SHORT).show();
////                    return;
////                }
////
////                if (season.equals("Select Season")) { // Assuming this is the default item in the spinner
////                    Toast.makeText(WeddingThemeActivity.this, "Please select a season", Toast.LENGTH_SHORT).show();
////                    return;
////                }
////
////                // Clear previous suggestion
////                themeSuggestionText.setText("");
////                themeSuggestionText.setVisibility(View.GONE);
////
////                // Call method to get AI theme suggestions
////                getAIThemeSuggestions(colors, venue, season);
////            }
////        });
////    }
////
////    // Method to get AI-based theme suggestions using Gemini API
////    private void getAIThemeSuggestions(String colors, String venue, String season) {
////        // Create Retrofit instance
////        Retrofit retrofit = new Retrofit.Builder()
////                .baseUrl("https://generativelanguage.googleapis.com/") // Replace with the actual base URL of the Gemini API
////                .addConverterFactory(GsonConverterFactory.create())
////                .build();
////
////        GeminiApi api = retrofit.create(GeminiApi.class);
////
////        // Create the request object
////        ThemeRequest request = new ThemeRequest(colors, venue, season);
////
////        // Make the API call
////        Call<ThemeResponse> call = api.getThemeSuggestions(request);
////        call.enqueue(new Callback<ThemeResponse>() {
////            @Override
////            public void onResponse(Call<ThemeResponse> call, Response<ThemeResponse> response) {
////                System.out.println(response.isSuccessful()+"lakshin123");
////                if (response.isSuccessful() && response.body() != null) {
////                    // Display the AI-generated theme suggestion
////                    String themeSuggestion = response.body().getSuggestion();
////                    themeSuggestionText.setText(themeSuggestion);
////                    themeSuggestionText.setVisibility(View.VISIBLE);
////                } else {
////                    String errorMessage = response.errorBody() != null ? response.errorBody().toString() : "Unknown error";
////                    Toast.makeText(WeddingThemeActivity.this, "Failed to get suggestions: " + errorMessage, Toast.LENGTH_SHORT).show();
////                }
////            }
////
////            @Override
////            public void onFailure(Call<ThemeResponse> call, Throwable t) {
////                // Handle API failure
////                Toast.makeText(WeddingThemeActivity.this, "API call failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
////            }
////        });
////    }
////}
//
//
//
//package com.example.mos_innovative;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.mos_innovative.GeminiPro;
//import com.example.mos_innovative.ResponseCallback;
//
//public class WeddingThemeActivity extends AppCompatActivity {
//
//    private EditText inputColors;
//    private Spinner inputVenue, inputSeason;
//    private TextView themeSuggestionText;
//    private Button btnGetTheme;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wedding_theme);
//
//        inputColors = findViewById(R.id.inputColors);
//        inputVenue = findViewById(R.id.inputVenue);
//        inputSeason = findViewById(R.id.inputSeason);
//        themeSuggestionText = findViewById(R.id.themeSuggestionText);
//        btnGetTheme = findViewById(R.id.btnGetTheme);
//
//        btnGetTheme.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Get user inputs
//                String colors = inputColors.getText().toString();
//                String venue = inputVenue.getSelectedItem().toString();
//                String season = inputSeason.getSelectedItem().toString();
//
//                // Validate inputs
//                if (colors.isEmpty()) {
//                    Toast.makeText(WeddingThemeActivity.this, "Please enter your color preferences", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Call method to get AI theme suggestions
//                getAIThemeSuggestions(colors, venue, season);
//            }
//        });
//    }
//
//    // Method to get AI-based theme suggestions using Gemini API
//    private void getAIThemeSuggestions(String colors, String venue, String season) {
//        // Create query string
//        String query = String.format("Suggest wedding themes using colors: %s, venue: %s, season: %s.", colors, venue, season);
//
//        GeminiPro geminiPro = new GeminiPro();
//        geminiPro.getResponse(query, new ResponseCallback() {
//            @Override
//            public void onResponse(String response) {
//                // Display the AI-generated theme suggestion
//                themeSuggestionText.setText(response);
//                themeSuggestionText.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                // Handle API failure
//                Toast.makeText(WeddingThemeActivity.this, "API call failed: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//}



//package com.example.mos_innovative;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.mos_innovative.GeminiPro;
//import com.example.mos_innovative.ResponseCallback;
//
//public class WeddingThemeActivity extends AppCompatActivity {
//
//    private EditText inputColors;
//    private Spinner inputVenue, inputSeason;
//    private TextView themeSuggestionText;
//    private Button btnGetTheme;
//    private ProgressBar progressBar; // ProgressBar to show loading state
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wedding_theme);
//
//        inputColors = findViewById(R.id.inputColors);
//        inputVenue = findViewById(R.id.inputVenue);
//        inputSeason = findViewById(R.id.inputSeason);
//        themeSuggestionText = findViewById(R.id.themeSuggestionText);
//        btnGetTheme = findViewById(R.id.btnGetTheme);
//        progressBar = findViewById(R.id.progressBar); // Initialize your ProgressBar
//
//        btnGetTheme.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Get user inputs
//                String colors = inputColors.getText().toString();
//                String venue = inputVenue.getSelectedItem().toString();
//                String season = inputSeason.getSelectedItem().toString();
//
//                // Validate inputs
//                if (colors.isEmpty()) {
//                    Toast.makeText(WeddingThemeActivity.this, "Please enter your color preferences", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Call method to get AI theme suggestions
//                getAIThemeSuggestions(colors, venue, season);
//            }
//        });
//    }
//
//    // Method to get AI-based theme suggestions using Gemini API
//    private void getAIThemeSuggestions(String colors, String venue, String season) {
//        // Create query string
//        String query = String.format("Suggest wedding themes using colors: %s, venue: %s, season: %s.", colors, venue, season);
//
//        GeminiPro geminiPro = new GeminiPro();
//        progressBar.setVisibility(View.VISIBLE); // Show the progress bar
//
//        // Clear previous suggestion text
//        themeSuggestionText.setText("");
//
//        // Call the GeminiPro method to get the response
//        geminiPro.getResponse(query, new ResponseCallback() {
//            @Override
//            public void onResponse(String response) {
//
//                // Display the AI-generated theme suggestion
//                themeSuggestionText.setText(response);
//                themeSuggestionText.setVisibility(View.VISIBLE);
//                progressBar.setVisibility(View.GONE); // Hide the progress bar
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                // Handle API failure
//                Toast.makeText(WeddingThemeActivity.this, "API call failed: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
//                progressBar.setVisibility(View.GONE); // Hide the progress bar
//            }
//        });
//    }
//}





package com.example.mos_innovative;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mos_innovative.GeminiPro;
import com.example.mos_innovative.ResponseCallback;

public class WeddingThemeActivity extends AppCompatActivity {

    private EditText inputColors;
    private Spinner inputVenue, inputSeason;
    private TextView themeSuggestionText;
    private Button btnGetTheme;
    private ProgressBar progressBar; // ProgressBar to show loading state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding_theme);

        inputColors = findViewById(R.id.inputColors);
        inputVenue = findViewById(R.id.inputVenue);
        inputSeason = findViewById(R.id.inputSeason);
        themeSuggestionText = findViewById(R.id.themeSuggestionText);
        btnGetTheme = findViewById(R.id.btnGetTheme);
        progressBar = findViewById(R.id.progressBar); // Initialize your ProgressBar

        btnGetTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user inputs
                String colors = inputColors.getText().toString();
                String venue = inputVenue.getSelectedItem().toString();
                String season = inputSeason.getSelectedItem().toString();

                // Validate inputs
                if (colors.isEmpty()) {
                    Toast.makeText(WeddingThemeActivity.this, "Please enter your color preferences", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Call method to get AI theme suggestions
                getAIThemeSuggestions(colors, venue, season);
            }
        });
    }

    // Method to get AI-based theme suggestions using Gemini API
    private void getAIThemeSuggestions(String colors, String venue, String season) {
        // Create query string
        String query = String.format("Suggest wedding themes using colors: %s, venue: %s, season: %s.", colors, venue, season);

        GeminiPro geminiPro = new GeminiPro();
        progressBar.setVisibility(View.VISIBLE); // Show the progress bar

        // Clear previous suggestion text
        themeSuggestionText.setText("");

        // Call the GeminiPro method to get the response
        geminiPro.getResponse(query, new ResponseCallback() {
            @Override
            public void onResponse(String response) {
                // Remove asterisks from the response
                response = response.replace("**", ""); // Remove asterisks

                // Display the AI-generated theme suggestion
                themeSuggestionText.setText(response);
                themeSuggestionText.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE); // Hide the progress bar
            }

            @Override
            public void onError(Throwable throwable) {
                // Handle API failure
                Toast.makeText(WeddingThemeActivity.this, "API call failed: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE); // Hide the progress bar
            }
        });
    }
}
