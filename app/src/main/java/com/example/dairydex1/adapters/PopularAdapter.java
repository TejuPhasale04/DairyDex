package com.example.dairydex1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.dairydex1.R;
import com.example.dairydex1.models.PopularModel;
import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private List<PopularModel> popularList;

    public PopularAdapter(List<PopularModel> popularList) {
        this.popularList = popularList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.popular_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PopularModel popularModel = popularList.get(position);
        holder.name.setText(popularModel.getName());
        holder.description.setText(popularModel.getDescription());
        holder.rating.setText(popularModel.getRating());
        holder.discount.setText(popularModel.getDiscount());

        // Load image using Glide
        Glide.with(holder.itemView.getContext())
                .load(popularModel.getImageResource())
                .placeholder(R.drawable.img_1) // Placeholder image while loading
                .into(holder.popImg);
    }



    @Override
    public int getItemCount() {
        return popularList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView name, description, rating, discount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views here
            popImg = itemView.findViewById(R.id.pop_img);
            name = itemView.findViewById(R.id.pop_name);
            description = itemView.findViewById(R.id.pop_des);
            rating = itemView.findViewById(R.id.pop_rate);
            discount = itemView.findViewById(R.id.pop_dis);
        }
    }
}
