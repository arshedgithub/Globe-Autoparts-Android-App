package com.example.globemotors.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.globemotors.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Retrieve data from arguments
        Bundle bundle = getArguments();
        if (bundle != null) {
            String productId = bundle.getString("cartProductId");
            String productName = bundle.getString("cartProductName");
            double productPrice = bundle.getDouble("cartProductPrice");
            int productStock = bundle.getInt("cartProductStock");

            // Now you can use these values to display or process in your Fragment
            final TextView textProduct = binding.textProduct;
            final TextView textPrice = binding.textPrice;

            textProduct.setText(productName);
            textProduct.setText(String.format("LKR %.2f", productPrice));
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
