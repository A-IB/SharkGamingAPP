package com.example.webshopgaming;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webshopgaming.adapters.ProductAdapter;
import com.example.webshopgaming.models.Product;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> cartProductList; // You need to define how to get this list from your CartManager
    private ImageButton backButton;
    private TextView totalPriceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recycler_view_cart);
        backButton = findViewById(R.id.back_button);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        totalPriceText = findViewById(R.id.total_price_text);

        // Assuming you have a CartManager or similar to get the cart products
//        cartProductList = CartManager.getInstance().getCartProducts();
        CartManager cartManager = CartManager.getInstance(this);
        cartProductList = cartManager.getCartProducts();

        productAdapter = new ProductAdapter(cartProductList, CartActivity.this);
        recyclerView.setAdapter(productAdapter);

        backButton.setOnClickListener(v -> finish());

        updateTotalPrice();
    }

    private void updateTotalPrice() {
        double totalPrice = 0;
        for (Product product : cartProductList) {
            totalPrice += product.getPrice();
        }
        totalPriceText.setText(String.format("Total: $%.2f", totalPrice));
    }
}
