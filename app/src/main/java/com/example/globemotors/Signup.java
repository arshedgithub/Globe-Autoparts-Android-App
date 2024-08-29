package com.example.globemotors;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.globemotors.models.User;
import com.example.globemotors.ui.home.HomeFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {

    private EditText nameEditText, usernameEditText, passwordEditText, retypePasswordEditText,
            addressEditText, contactEditText, emailEditText;
    private Button signupButton;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize views
        nameEditText = findViewById(R.id.input_name);
        usernameEditText = findViewById(R.id.input_username);
        passwordEditText = findViewById(R.id.input_password);
        retypePasswordEditText = findViewById(R.id.input_retype);
        addressEditText = findViewById(R.id.input_address);
        contactEditText = findViewById(R.id.input_contact);
        emailEditText = findViewById(R.id.input_email);
        signupButton = findViewById(R.id.signup_btn);

        // Initialize Retrofit
        apiService = RetrofitClient.getClient().create(ApiService.class);

        // Set up click listener for the sign-up button
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString().trim();
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String retypePassword = retypePasswordEditText.getText().toString().trim();
                String address = addressEditText.getText().toString().trim();
                String contact = contactEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();

                if (validateInputs(name, username, password, retypePassword, address, contact, email)) {
                    performSignup(name, username, password, address, contact, email);
                }
            }
        });
    }

    private boolean validateInputs(String name, String username, String password, String retypePassword,
                                   String address, String contact, String email) {
        if (name.isEmpty() || username.isEmpty() || password.isEmpty() || retypePassword.isEmpty() ||
                address.isEmpty() || contact.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(retypePassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void performSignup(String name, String username, String password, String address,
                               String contact, String email) {
        // Create signup request object
        User user = new User(name, username, password, address, contact, email);

        // API calling
        apiService.signUp(user).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(Signup.this, "Sign up successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), HomeFragment.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Signup.this, "Sign up failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Signup.this, "Network error. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
