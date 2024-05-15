package com.example.dairydex1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;

import com.example.dairydex1.models.CartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlacedOrderActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_order);

        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser == null) {
            // User not authenticated, handle this case if necessary
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        databaseReference = FirebaseDatabase.getInstance().getReference().child("CurrentUser").child(currentUser.getUid()).child("MyOrder");

        List<CartModel> list = (ArrayList<CartModel>) getIntent().getSerializableExtra("itemList");

        if (list != null && list.size() > 0) {
            for (CartModel model : list) {

                double totalPrice = model.getTotalPrice() * model.getTotalQuantity();

                // Create a new CartModel instance
                CartModel cartItem = new CartModel(model.getProductName(), model.getProductPrice(), model.getCurrentDate(), model.getCurrentTime(), model.getTotalQuantity(), totalPrice);

                // Push the CartModel instance to the database
                databaseReference.push().setValue(cartItem)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(PlacedOrderActivity.this, "Your Order has been placed", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });
            }
        }
    }
}
