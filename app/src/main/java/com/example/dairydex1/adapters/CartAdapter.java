package com.example.dairydex1.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairydex1.models.CartModel;
import com.example.dairydex1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final List<CartModel> cartList;
    private final Context context;
    private final DatabaseReference cartReference;

    public CartAdapter(List<CartModel> cartList, Context context) {
        this.cartList = cartList;
        this.context = context;
        this.cartReference = FirebaseDatabase.getInstance().getReference().child("AddToCart").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("CurrentUser");
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_my_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartModel cartItem = cartList.get(position);

        holder.textProductName.setText(cartItem.getProductName());
        holder.textProductPrice.setText("Product Price: $" + cartItem.getProductPrice());
        holder.textTotalQuantity.setText("Total Quantity: " + cartItem.getTotalQuantity());
        holder.textTotalPrice.setText("Total Price: $" + (cartItem.getTotalPrice() * cartItem.getTotalQuantity()));
        holder.textCurrentTime.setText("Current Time: " + cartItem.getCurrentTime());

      /*  holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cartItem.getItemId() != null) {
                    deleteCartItem(cartItem.getItemId());
                } else {
                    Log.e("CartAdapter", "Item ID is null");
                    Toast.makeText(context, "Item ID is null", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView textProductName, textProductPrice, textTotalQuantity, textTotalPrice, textCurrentTime;
        ImageView deleteItem;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            textProductName = itemView.findViewById(R.id.text_product_name);
            textProductPrice = itemView.findViewById(R.id.text_product_price);
            textTotalQuantity = itemView.findViewById(R.id.text_total_quantity);
            textTotalPrice = itemView.findViewById(R.id.text_total_price);
            textCurrentTime = itemView.findViewById(R.id.text_current_time);
        }
    }
/*
    private void deleteCartItem(String itemId) {
        cartReference.child(itemId).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Item deleted successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("CartAdapter", "Error deleting item: " + e.getMessage());
                        Toast.makeText(context, "Failed to delete item", Toast.LENGTH_SHORT).show();
                    }
                });
    }*/
}
