package com.example.globemotors;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Cart extends AppCompatActivity {

    private TextView textProduct, textPrice, textTotalPrice;
    private EditText editQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialize views
        textProduct = findViewById(R.id.text_product);
        textPrice = findViewById(R.id.text_price);
        textTotalPrice = findViewById(R.id.text_total_price);
        editQuantity = findViewById(R.id.edit_quantity);

        // Retrieve data from Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String productName = extras.getString("cartProductName");
            double productPrice = extras.getDouble("cartProductPrice");

            // Set the values to the TextViews
            textProduct.setText(productName);
            textPrice.setText(String.format("LKR %.2f", productPrice));

            // Set up TextWatcher to calculate total price whenever quantity changes
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
}
