package com.example.dairydex1.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairydex1.DetailedActivity;
import com.example.dairydex1.models.ViewAllModel;
import com.example.dairydex1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

    private List<ViewAllModel> itemList;
    private Context context;

    public ViewAllAdapter(Context context, List<ViewAllModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewAllModel model = itemList.get(position);

        holder.nameTextView.setText(model.getName());
        holder.descriptionTextView.setText(model.getDescription());
        holder.ratingTextView.setText(model.getRating());
        holder.priceTextView.setText(model.getPrice());

        // Load and resize image using Picasso
        Picasso.get()
                .load(model.getImg_url())
                .resize(200, 200)
                .centerCrop()
                .into(holder.itemImageView);


        holder.nextImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detail",itemList.get(position));
                context.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImageView, nextImageView;
        TextView nameTextView, descriptionTextView, ratingTextView,priceTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImageView = itemView.findViewById(R.id.cat_nav_img);
            nextImageView = itemView.findViewById(R.id.next);
            nameTextView = itemView.findViewById(R.id.nav_cat_name);
            descriptionTextView = itemView.findViewById(R.id.nav_cat_des);
            ratingTextView = itemView.findViewById(R.id.pop_rate);
            priceTextView=itemView.findViewById(R.id.discountText);
        }
    }
}
