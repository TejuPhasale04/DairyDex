package com.example.dairydex1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairydex1.adapters.ViewAllAdapter;
import com.example.dairydex1.models.ViewAllModel;
import com.example.dairydex1.R;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        RecyclerView recyclerView = findViewById(R.id.view_all_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create sample data for three cards
        List<ViewAllModel> itemList = createSampleData();

        // Set up the adapter with the sample data
        ViewAllAdapter adapter = new ViewAllAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }

    private List<ViewAllModel> createSampleData() {
        List<ViewAllModel> itemList = new ArrayList<>();

        // Adding three sample items
        itemList.add(new ViewAllModel("PRIME", "Unlock the potential of your herd with our premium-grade PRIME Cattle Feed, meticulously crafted to elevate their health and productivity", "4.8", R.drawable.product1));
        itemList.add(new ViewAllModel("GOLD", "Elevate your herd's well-being to gold standards with our meticulously crafted GOLD Cattle Feed, designed to optimize health and performance for superior results.", "4.8", R.drawable.product2));
        itemList.add(new ViewAllModel("MINERAL MIXURE", "Unlock the potential of your livestock with our premium Mineral Mixture, a vital blend enriched with essential nutrients for optimal health and vitality.", "4.9", R.drawable.product3));

        return itemList;
    }
}
