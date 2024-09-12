package com.example.webshopgaming;

import android.content.Context;
import com.example.webshopgaming.models.Product;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<Product> cartProducts;
    private Context context;

    private CartManager(Context context) {
        this.context = context;
        cartProducts = new ArrayList<>();
    }

    public static synchronized CartManager getInstance(Context context) {
        if (instance == null) {
            instance = new CartManager(context.getApplicationContext());
        }
        return instance;
    }

    public void addProduct(Product product) {
        cartProducts.add(product);
    }

    public List<Product> getCartProducts() {
        return cartProducts;
    }

    public void clearCart() {
        cartProducts.clear();
    }
}
