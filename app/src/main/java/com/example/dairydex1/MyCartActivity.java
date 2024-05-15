package com.example.dairydex1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dairydex1.adapters.CartAdapter;
import com.example.dairydex1.models.CartModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MyCartActivity extends Activity {

    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    private List<CartModel> cartList;
    private DatabaseReference databaseReference;
    private Button buyNowButton; // Added button reference
    private Handler handler;
    private Timer timer;
    private int currentItemIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_new_carts);

        recyclerViewCart = findViewById(R.id.view_my_cart_detail);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        cartList = new ArrayList<>();
        cartAdapter = new CartAdapter(cartList, this);
        recyclerViewCart.setAdapter(cartAdapter);

        buyNowButton = findViewById(R.id.buy_Now); // Initialize button reference

        // Initialize Firebase
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            // Fetch data from Firebase
            databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).child("cart").child("CurrentUser");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    cartList.clear();
                    cartAdapter.notifyDataSetChanged();
                    startAutoScrolling();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle error
                }
            });
        }

        // Set OnClickListener for the "Buy Now" button
        buyNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start the PlacedOrderActivity
                Intent intent = new Intent(MyCartActivity.this, PlacedOrderActivity.class);
                // Pass the list of items to the PlacedOrderActivity
                intent.putExtra("itemList", new ArrayList<>(cartList));
                // Start the activity
                startActivity(intent);
            }
        });
    }

    private void startAutoScrolling() {
        handler = new Handler(Looper.getMainLooper());
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        currentItemIndex++;
                        if (currentItemIndex >= cartList.size()) {
                            currentItemIndex = 0;
                        }
                        recyclerViewCart.smoothScrollToPosition(currentItemIndex);
                    }
                });
            }
        }, 2000, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
