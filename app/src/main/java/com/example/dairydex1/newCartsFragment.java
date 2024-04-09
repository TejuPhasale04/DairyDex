package com.example.dairydex1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

public class newCartsFragment extends Fragment {

    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    private List<CartModel> cartList;

    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_carts, container, false);

        recyclerViewCart = view.findViewById(R.id.view_my_cart_detail);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(getContext()));
        cartList = new ArrayList<>();
        cartAdapter = new CartAdapter(cartList);
        recyclerViewCart.setAdapter(cartAdapter);

        // Initialize Firebase
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            // Fetch data from Firebase
            databaseReference = FirebaseDatabase.getInstance().getReference().child("AddToCart").child(user.getUid()).child("CurrentUser");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    cartList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        CartModel cartItem = snapshot.getValue(CartModel.class);
                        if (cartItem != null) {
                            cartList.add(cartItem);
                        }
                    }
                    cartAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database error
                    Toast.makeText(getContext(), "Database Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        return view;
    }
}
