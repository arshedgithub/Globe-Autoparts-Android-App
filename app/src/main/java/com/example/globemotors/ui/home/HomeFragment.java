package com.example.globemotors.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.globemotors.Product;
import com.example.globemotors.ProductListAdapter;
import com.example.globemotors.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private ListView listView;
    private ProductListAdapter adapter;
    private ArrayList<Product> productList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        listView = binding.productList;

        // Create a list of products
        productList = new ArrayList<>();
        productList.add(new Product(1, "Product 1", "Category A", 10.0f, 5, "https://example.com/image1.png"));
        productList.add(new Product(2, "Product 2", "Category B", 20.0f, 3, "https://example.com/image2.png"));
        productList.add(new Product(3, "Product 3", "Category C", 30.0f, 2, "https://example.com/image3.png"));

        // Initialize custom adapter
        adapter = new ProductListAdapter(requireContext(), productList);

        // Set adapter to ListView
        listView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}