package com.example.dairydex1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class pop_item extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popular_item);

        // Set click listener for the product image
        ImageView productImage = findViewById(R.id.pop_img);
        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open ViewAllActivity for viewing all products
                Intent intent = new Intent(pop_item.this, ViewAllActivity.class);
                startActivity(intent);
            }
        });
    }
}
