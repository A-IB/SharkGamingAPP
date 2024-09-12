package com.example.webshopgaming;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.webshopgaming.models.Product;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView productImageView;
    private Product product;
    private TextView productName;
    private TextView productPrice;
    private TextView productDescription;
    private Button addToCartButton;
    private ImageButton backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        productImageView = findViewById(R.id.product_detail_image);
        productName = findViewById(R.id.product_name);
        productPrice = findViewById(R.id.product_price);
        productDescription = findViewById(R.id.product_description);
        backButton = findViewById(R.id.back_button);
        addToCartButton = findViewById(R.id.add_to_cart_button);

        // Get the product details from the intent
        product = (Product) getIntent().getSerializableExtra("PRODUCT");
        if (product != null) {
            productName.setText(product.getName());
            productPrice.setText(String.format("$%.2f", product.getPrice()));
            productDescription.setText(product.getDescription());
            Glide.with(this)
                    .load(product.getImageUrl()) // Load the image URL
                    .placeholder(R.drawable.sample_product_image_foreground) // Placeholder image
                    .into(productImageView);
        }


//        // Retrieve data from intent
//        if (getIntent() != null && getIntent().getExtras() != null) {
//            Product product = (Product) getIntent().getSerializableExtra("PRODUCT");
//            if (product != null) {
//                productName.setText(product.getName());
//                productPrice.setText(String.format("$%.2f", product.getPrice()));
//                productDescription.setText(product.getDescription());
//            }
//        }

        Button addToCartButton = findViewById(R.id.add_to_cart_button);
        addToCartButton.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_click));
            // Handle add to cart
        });

        addToCartButton.setOnClickListener(v -> {
            if (product != null) {
                CartManager.getInstance(this).addProduct(product);
                Toast.makeText(this, "Product added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finish the activity and return to the previous activity
            }
        });
    }
}
