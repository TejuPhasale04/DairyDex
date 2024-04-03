package com.example.dairydex1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairydex1.R;
import com.example.dairydex1.adapters.PopularAdapter;
import com.example.dairydex1.adapters.RecommededAdapter;
import com.example.dairydex1.models.PopularModel;
import com.example.dairydex1.models.RecommededModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView rvSlider, recommended_rec;
    private PopularAdapter popularAdapter;
    private RecommededAdapter recommendedAdapter; // Corrected variable name
    private ArrayList<PopularModel> popularItems;
    private ArrayList<RecommededModel> recItems;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize RecyclerViews
        rvSlider = root.findViewById(R.id.rv_slider);
        recommended_rec = root.findViewById(R.id.recommended_rec);

        // Sample data for the slider
        int[] imageResources = {R.drawable.carausal1, R.drawable.carausal2, R.drawable.carausal3};
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

        return root;
    }
}
