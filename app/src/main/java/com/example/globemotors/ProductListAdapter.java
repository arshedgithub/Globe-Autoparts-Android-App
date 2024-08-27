package com.example.globemotors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProductListAdapter extends ArrayAdapter<Product> {
    private ArrayList<Product> productArrayList;

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

        productName.setText(productData.getName());
        productCategory.setText(productData.getCategory());
        productPrice.setText(String.valueOf(productData.getPrice()));

        return convertView;
    }
}
