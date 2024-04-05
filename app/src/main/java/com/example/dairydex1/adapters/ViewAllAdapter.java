package com.example.dairydex1.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairydex1.models.ViewAllModel;
import com.example.dairydex1.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

    private List<ViewAllModel> itemList;

    public ViewAllAdapter(List<ViewAllModel> itemList) {
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

        // Load image using Picasso
        Picasso.get()
                .load(model.getImg_url())
                .into(holder.itemImageView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImageView;
        TextView nameTextView, descriptionTextView, ratingTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImageView = itemView.findViewById(R.id.cat_nav_img);
            nameTextView = itemView.findViewById(R.id.nav_cat_name);
            descriptionTextView = itemView.findViewById(R.id.nav_cat_des);
            ratingTextView = itemView.findViewById(R.id.pop_rate);
        }
    }
}
