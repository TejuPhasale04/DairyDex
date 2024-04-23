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
import com.example.dairydex1.models.PopularModel;
import java.util.List;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private final List<PopularModel> popularList;

    public PopularAdapter(List<PopularModel> popularList) {
        this.popularList = popularList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        View itemView = inflater.inflate(R.layout.popular_item, parent, false);


        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PopularModel popularModel = popularList.get(position);

        // Load image using Glide with optimizations
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.img_1)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(holder.itemView.getContext())
                .load(popularModel.getImageResource())
                .apply(requestOptions)
                .into(holder.popImg);
    }

    @Override
    public int getItemCount() {
        return popularList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popImg = itemView.findViewById(R.id.pop_img);
        }
    }
}
