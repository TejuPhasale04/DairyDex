package com.example.dairydex1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.dairydex1.models.CartModel;
import com.example.dairydex1.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DetailedActivity extends AppCompatActivity {
    TextView quantity;
    int totalQuantity = 1;
    double totalPrice = 0;
    ImageView detailedImg;
    TextView price, rating, description;
    Button addToCart;
    ImageView addItem, removeItem;
    Toolbar toolbar;
    ViewAllModel viewAllModel = null;

    FirebaseAuth auth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("AddToCart");
        auth = FirebaseAuth.getInstance();

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ViewAllModel) {
            viewAllModel = (ViewAllModel) object;
        }
        quantity = findViewById(R.id.quantity);

        detailedImg = findViewById(R.id.detailed_img);
        price = findViewById(R.id.detailed_price);
        rating = findViewById(R.id.rate);
        description = findViewById(R.id.des);
        if (viewAllModel != null) {
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedImg);
            rating.setText(viewAllModel.getRating());
            description.setText(viewAllModel.getDescription());
            price.setText("Price:$" + viewAllModel.getPrice());

            // Calculate total price based on the quantity of the product
            int productPrice = Integer.parseInt(viewAllModel.getPrice());
            totalPrice = productPrice * totalQuantity;
        }


        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);

        addToCart = findViewById(R.id.add_to_card);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addedToCart();
            }
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity < 10) {
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                }
            }
        });
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity > 0) {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                }
            }
        });
    }

    private void addedToCart() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd,yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("MM:mm:ss");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        String productName = viewAllModel.getName();
        String productPrice = viewAllModel.getPrice();

        // Create a new CartModel instance
        CartModel cartItem = new CartModel(productName, productPrice, saveCurrentDate, saveCurrentTime, totalQuantity, totalPrice);

        // Get a reference to the user's cart in the database
        DatabaseReference userCartRef = databaseReference.child(Objects.requireNonNull(auth.getCurrentUser()).getUid()).child("CurrentUser");

        // Push the CartModel instance to the database
        userCartRef.push().setValue(cartItem)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DetailedActivity.this, "Added To Cart", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(DetailedActivity.this, "Failed to add to cart", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}