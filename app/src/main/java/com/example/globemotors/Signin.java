package com.example.globemotors;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.globemotors.models.LoginRequest;
import com.example.globemotors.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signin extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signinButton;
    private Button signupButton;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        // Initialize views
        usernameEditText = findViewById(R.id.input_username);
        passwordEditText = findViewById(R.id.input_password);
        signinButton = findViewById(R.id.signin_btn);
        signupButton = findViewById(R.id.signup_btn);

        // Initialize Retrofit and ApiService
        RetrofitClient retrofitClient = new RetrofitClient();
        apiService = retrofitClient.getClient().create(ApiService.class);

        // Set up the click listener for the sign-in button
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (!username.isEmpty() && !password.isEmpty()) {
                    // Perform login
                    login(username, password);
                } else {
                    Toast.makeText(Signin.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Signup.class);
                startActivity(intent);
            }
        });
    }

    private void login(String username, String password) {
        // Create the login request model
        LoginRequest loginRequest = new LoginRequest(username, password);

        // Make the API call
        apiService.signIn(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String accessToken = response.body().getAccessToken();
                    // Handle successful login (e.g., save token, navigate to another activity)
                    Toast.makeText(Signin.this, "Login successful!", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle failed login
                    Log.d("signin response", "onResponse: " + response);
                    Toast.makeText(Signin.this, "Invalid username or password.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Handle network error
                Toast.makeText(Signin.this, "Network error. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
