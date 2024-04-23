package com.example.dairydex1.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairydex1.R;
import com.example.dairydex1.ViewAllActivity;
import com.example.dairydex1.adapters.PopularAdapter;
import com.example.dairydex1.adapters.RecommededAdapter;
import com.example.dairydex1.models.PopularModel;
import com.example.dairydex1.models.RecommededModel;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private RecyclerView rvSlider, recommended_rec;
    private PopularAdapter popularAdapter;
    private RecommededAdapter recommendedAdapter;
    private ArrayList<PopularModel> popularItems;
    private ArrayList<RecommededModel> recItems;
    private int[] imageResources;
    private int currentImageIndex = 0;
    private Handler handler;
    private Timer timer;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize RecyclerViews
        rvSlider = root.findViewById(R.id.rv_slider);
        recommended_rec = root.findViewById(R.id.recommended_rec);

        // Sample data for the slider
        imageResources = new int[]{R.drawable.carausal1, R.drawable.carausal2, R.drawable.carausal3}; // Moved here
        int[] recImages = {R.drawable.product1, R.drawable.product2, R.drawable.product3};

        // Create the list of products
        popularItems = PopularModel.createProductList(3, imageResources);
        recItems = RecommededModel.createRecProductList(3, recImages);

        // Set up adapter for the slider
        popularAdapter = new PopularAdapter(popularItems);
        rvSlider.setAdapter(popularAdapter);

        // Set layout manager for horizontal scrolling
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        rvSlider.setLayoutManager(layoutManager);

        // Set up adapter for recommended products RecyclerView
        recommendedAdapter = new RecommededAdapter(recItems);
        recommended_rec.setAdapter(recommendedAdapter);

        // Set layout manager for recommended products RecyclerView
        LinearLayoutManager recommendedLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recommended_rec.setLayoutManager(recommendedLayoutManager);

        // Set click listeners for "View All" text
        TextView viewAllRecommended = root.findViewById(R.id.view_all_recommended);

        viewAllRecommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open activity_view_all.xml for viewing all recommended products
                Intent intent = new Intent(requireContext(), ViewAllActivity.class);
                startActivity(intent);
            }
        });

        // Start timer to change slider images
        startImageSlider();

        return root;
    }

    private void startImageSlider() {
        handler = new Handler(Looper.getMainLooper());
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // Increment index and make sure it doesn't exceed array size
                        currentImageIndex++;
                        if (currentImageIndex >= imageResources.length) {
                            currentImageIndex = 0; // Reset index to 0 if it exceeds array size
                        }
                        // Change the data in adapter
                        popularItems.get(currentImageIndex).setImageResource(imageResources[currentImageIndex]);
                        popularAdapter.notifyDataSetChanged();
                        // Scroll to the next item in the RecyclerView within 1 second
                        LinearLayoutManager layoutManager = (LinearLayoutManager) rvSlider.getLayoutManager();
                        if (layoutManager != null) {
                            int nextItem = (currentImageIndex + 1) % imageResources.length;
                            layoutManager.smoothScrollToPosition(rvSlider, new RecyclerView.State(), nextItem);
                        }
                    }
                });
            }
        }, 4000, 4000); // Change image every 3 seconds
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // Stop the timer when fragment is destroyed to avoid memory leaks
        timer.cancel();
    }
}
