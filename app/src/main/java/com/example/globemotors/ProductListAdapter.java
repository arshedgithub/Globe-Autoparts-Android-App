package com.example.globemotors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.globemotors.models.Product;

import java.util.ArrayList;

public class ProductListAdapter extends ArrayAdapter<Product> {

    public ProductListAdapter(@NonNull Context context, ArrayList<Product> productArrayList) {
        super(context, R.layout.product_list_item, productArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product productData = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_list_item, parent, false);
        }

        TextView productName = convertView.findViewById(R.id.list_product_name);
        TextView productCategory = convertView.findViewById(R.id.list_product_category);
        TextView productPrice = convertView.findViewById(R.id.list_product_price);
        TextView productStatus = convertView.findViewById(R.id.list_product_status);

        productName.setText(productData.getName());
        productCategory.setText(productData.getCategory().getName());
        productPrice.setText(String.format("LKR %.2f", productData.getPrice()));
        productStatus.setText(productData.getStock() > 0 ? "Available": "Not Available");

        return convertView;
    }
}
