package com.example.globemotors;

import com.example.globemotors.models.LoginRequest;
import com.example.globemotors.models.LoginResponse;
import com.example.globemotors.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("/api/products")
    Call<List<Product>> getProducts();

    // Define the sign-in method
    @POST("/api/users/auth/signin")
    Call<LoginResponse> signIn(@Body LoginRequest loginRequest);
}
