package com.example.globemotors;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.globemotors.models.Product;
import com.example.globemotors.ui.slideshow.SlideshowFragment;

public class Product_details extends AppCompatActivity {

    private TextView productName, productCategory, productPrice, productDescription, productBrand, productSubcategory, productOrigin, productVehicle, productUseStatus;
    private ImageView productImage;
    private Button productCartBtn;

    // SharedPreferences to store JWT token
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_details);

        // Initialize views
        productName = findViewById(R.id.product_name);
        productCategory = findViewById(R.id.product_category);
        productPrice = findViewById(R.id.product_price);
        productDescription = findViewById(R.id.product_description);
        productBrand = findViewById(R.id.product_brand);
        productSubcategory = findViewById(R.id.product_subcategory);
        productOrigin = findViewById(R.id.product_origin);
        productVehicle = findViewById(R.id.product_vehicle);
        productUseStatus = findViewById(R.id.product_use_status);
        productImage = findViewById(R.id.product_image);
        productCartBtn = findViewById(R.id.product_cart_button);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);

        // Retrieve product data from Intent
        Product product = (Product) getIntent().getSerializableExtra("PRODUCT_DETAILS");
        if (product != null) {
            populateProductDetails(product);
        }

        productCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleAddToCart(product);
            }
        });
    }

    private void populateProductDetails(Product product) {
        productName.setText(product.getName());
        productCategory.setText(product.getCategory().getName());
        productPrice.setText(String.format("LKR %.2f", product.getPrice()));
        productDescription.setText(product.getDescription());
        productBrand.setText("Brand: " + product.getBrand().getName());
        productSubcategory.setText("Subcategory: " + product.getSubcategory().getName());
        productOrigin.setText("Origin: " + product.getOrigin().getName());
        productVehicle.setText("Vehicle: " + product.getVehicle().getName());
        productUseStatus.setText(product.getUseStatus().getName());

        if (product.getStock() <= 0) {
            productCartBtn.setEnabled(false);
            productCartBtn.setText("Out of Stock");
            productCartBtn.setBackgroundColor(getColor(R.color.red));
        } else {
            productCartBtn.setEnabled(true);
        }

        // Load image using a library like Glide or Picasso
        // Example using Glide:
        // Glide.with(this).load(product.getPhoto()).into(productImage);
    }

    private void handleAddToCart(Product product) {
        // Check if the user is signed in by checking for the JWT token in SharedPreferences
        String accessToken = sharedPreferences.getString("accessToken", null);

        if (accessToken != null) {
            Intent intent = new Intent(getBaseContext(), Cart.class);
            intent.putExtra("cartProductId", product.getId());
            intent.putExtra("cartProductName", product.getName());
            intent.putExtra("cartProductPrice", product.getPrice());
            intent.putExtra("cartProductStock", product.getStock());
            startActivity(intent);
        } else {
            Toast.makeText(this, "igned into order Products", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getBaseContext(), Signin.class);
            startActivity(intent);
        }
    }
}
