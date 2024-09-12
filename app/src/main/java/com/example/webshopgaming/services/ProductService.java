package com.example.webshopgaming.services;

import com.example.webshopgaming.models.Product;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ProductService {
    @GET("products")
    Call<List<Product>> getProducts();
}
