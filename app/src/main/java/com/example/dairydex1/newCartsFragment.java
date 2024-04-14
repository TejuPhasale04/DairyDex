package com.example.dairydex1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class newCartsFragment extends Fragment {

    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    private List<CartModel> cartList;
    Button buy_now;

    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_carts, container, false);

        recyclerViewCart = view.findViewById(R.id.view_my_cart_detail);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(getContext()));
        cartList = new ArrayList<>();
        cartAdapter = new CartAdapter(cartList,getContext());
        recyclerViewCart.setAdapter(cartAdapter);
        buy_now=view.findViewById(R.id.buy_Now);
        // Initialize Firebase
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {

            databaseReference = FirebaseDatabase.getInstance().getReference().child("AddToCart").child(mAuth.getCurrentUser().getUid()).child("CurrentUser");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    cartList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String documentId=dataSnapshot.getKey();
                        CartModel cartItem = snapshot.getValue(CartModel.class);
                        cartItem.setDocumentId(documentId);
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
        buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),PlacedOrderActivity.class);
                intent.putExtra("itemList",(Serializable) cartList);
                startActivity(intent);
            }
        });

        return view;
    }
}
