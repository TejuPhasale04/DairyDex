package com.example.dairydex1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dairydex1.R;
import com.example.dairydex1.models.RecommededModel;

import java.util.List;

public class RecommededAdapter extends RecyclerView.Adapter<RecommededAdapter.ViewHolder> {

    private List<RecommededModel> recList;

    public RecommededAdapter(List<RecommededModel> recList) {
        this.recList = recList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View recItemView = inflater.inflate(R.layout.rec_item, parent, false);

        // Return a new holder instance
        return new ViewHolder(recItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecommededModel recItem = recList.get(position);

        // Set the image using Glide library
        Glide.with(holder.itemView.getContext())
                .load(recItem.getImg_url())
                .placeholder(R.drawable.product1) // Placeholder image while loading
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return recList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.rec_img); // assuming you have an ImageView in rec_item layout with id recommended_image
        }
    }
}