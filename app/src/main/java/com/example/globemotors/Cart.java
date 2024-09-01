package com.example.globemotors;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.globemotors.models.OrderRequest;
import com.example.globemotors.reusable.InputFilterMinMax;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart extends AppCompatActivity {

    private TextView textProduct, textPrice, textTotalPrice;
    private EditText editQuantity;
    private Button orderBtn;
    private double productPrice;
    private int userId = 1;
    private int productId;
    private int productStock;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);

        textProduct = findViewById(R.id.text_product);
        textPrice = findViewById(R.id.text_price);
        textTotalPrice = findViewById(R.id.text_total_price);
        editQuantity = findViewById(R.id.edit_quantity);
        orderBtn = findViewById(R.id.order_btn);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String productName = extras.getString("cartProductName");
            productPrice = extras.getFloat("cartProductPrice");
            productId = extras.getInt("cartProductId");
            productStock = extras.getInt("cartProductStock");

            textProduct.setText(productName);
            textPrice.setText(String.format("LKR %.2f", productPrice));

            // Apply InputFilter to restrict input range between 0 and productStock
            editQuantity.setFilters(new InputFilter[]{ new InputFilterMinMax(0, productStock) });

            editQuantity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    calculateTotalPrice(productPrice);
                }
            });
        }

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitOrder();
            }
        });
    }

    private void calculateTotalPrice(double productPrice) {
        String quantityText = editQuantity.getText().toString();
        if (!quantityText.isEmpty()) {
            int quantity = Integer.parseInt(quantityText);
            double totalPrice = quantity * productPrice;
            textTotalPrice.setText(String.format("Total Price: LKR %.2f", totalPrice));
        } else {
            textTotalPrice.setText("Total Price: LKR 0.00");
        }
    }

    private void submitOrder() {
        String quantityText = editQuantity.getText().toString();
        if (quantityText.isEmpty()) {
            Toast.makeText(this, "Please enter a quantity.", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity = Integer.parseInt(quantityText);
        double total = quantity * productPrice;

        OrderRequest orderRequest = new OrderRequest(quantity, total, userId, productId);
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        String authToken = sharedPreferences.getString("accessToken", "");

        Call<Void> call = apiService.submitOrder(authToken, orderRequest);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Cart.this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), OrderSuccess.class);
                    startActivity(intent);
                } else {
                    if (authToken == null){
                        Toast.makeText(Cart.this, "Sign in for order.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getBaseContext(), Signin.class);
                        startActivity(intent);
                    }
                    Toast.makeText(Cart.this, "Failed to place order. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Cart.this, "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
