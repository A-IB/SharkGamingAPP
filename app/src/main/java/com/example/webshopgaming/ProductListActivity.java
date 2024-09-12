//package com.example.webshopgaming;
//
//import android.os.Bundle;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.webshopgaming.adapters.ProductAdapter;
//import com.example.webshopgaming.models.Product;
//import com.example.webshopgaming.services.ProductService;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//import java.util.List;
//
//public class ProductListActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private ProductAdapter productAdapter;
//    private List<Product> productList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_product_list);
//
//        recyclerView = findViewById(R.id.recycler_view_product_list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        fetchProducts();
//    }
//
//    private void fetchProducts() {
//        ProductService productService = RetrofitClient.getClient().create(ProductService.class);
//        Call<List<Product>> call = productService.getProducts();
//
//        call.enqueue(new Callback<List<Product>>() {
//            @Override
//            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    productList = response.body();
//                    productAdapter = new ProductAdapter(productList);
//                    recyclerView.setAdapter(productAdapter);
//                } else {
//                    Toast.makeText(ProductListActivity.this, "Failed to retrieve products", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Product>> call, Throwable t) {
//                Toast.makeText(ProductListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
package com.example.webshopgaming;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webshopgaming.adapters.ProductAdapter;
import com.example.webshopgaming.models.Product;
import com.example.webshopgaming.services.ProductService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private Button cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        recyclerView = findViewById(R.id.recycler_view_product_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button cartButton = findViewById(R.id.cart_button);
        cartButton.setVisibility(View.VISIBLE);


        TextView brandTitle = findViewById(R.id.brand_title);
        brandTitle.setText("Shark Gaming"); // Set your brand name dynamically if needed


        fetchProducts();

        cartButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProductListActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }

    private void fetchProducts() {
        ProductService productService = RetrofitClient.getClient().create(ProductService.class);
        Call<List<Product>> call = productService.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList = response.body();
                    // Initialize the adapter with both arguments
                    productAdapter = new ProductAdapter(productList, ProductListActivity.this);
                    recyclerView.setAdapter(productAdapter);
                } else {
                    Toast.makeText(ProductListActivity.this, "Failed to retrieve products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ProductListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
