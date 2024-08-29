package com.example.globemotors;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.globemotors.models.Product;

public class Product_details extends AppCompatActivity {

    private TextView productName, productCategory, productPrice, productDescription, productBrand, productSubcategory, productOrigin, productVehicle, productUseStatus;
    private ImageView productImage;

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

        // Retrieve product data from Intent
        Product product = (Product) getIntent().getSerializableExtra("PRODUCT_DETAILS");
        if (product != null) {
            populateProductDetails(product);
        }
    }

    private void populateProductDetails(Product product) {
        productName.setText(product.getName());
        productCategory.setText(product.getCategory().getName());
        productPrice.setText(String.format("$%.2f", product.getPrice()));
        productDescription.setText(product.getDescription());
        productBrand.setText("Brand: " + product.getBrand().getName());
        productSubcategory.setText("Subcategory: " + product.getSubcategory().getName());
        productOrigin.setText("Origin: " + product.getOrigin().getName());
        productVehicle.setText("Vehicle: " + product.getVehicle().getName());
        productUseStatus.setText(product.getUseStatus().getName());

        // Load image using a library like Glide or Picasso
        // Example using Glide:
        // Glide.with(this).load(product.getPhoto()).into(productImage);
    }
}
