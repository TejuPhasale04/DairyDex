package com.example.dairydex1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dairydex1.adapters.ViewAllAdapter;
import com.example.dairydex1.models.ViewAllModel;
import com.example.dairydex1.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        RecyclerView recyclerView = findViewById(R.id.view_all_rec);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create sample data for three cards
        List<ViewAllModel> itemList = createSampleData();


        // Set up the adapter with the sample data
        ViewAllAdapter adapter = new ViewAllAdapter(this, itemList);
        recyclerView.setAdapter(adapter);

    }

    private List<ViewAllModel> createSampleData() {
        List<ViewAllModel> itemList = new ArrayList<>();

        // Adding more sample items with extended descriptions
        itemList.add(new ViewAllModel("PRIME", "Unlock the potential of your herd with PRIME. This premium feed blend is specially crafted to provide your cows with the essential nutrients they need for optimal health and productivity. Packed with vitamins, minerals, and protein-rich ingredients, PRIME ensures that your herd thrives in every aspect of their well-being.", "5.0","400", R.drawable.product1));
        itemList.add(new ViewAllModel("GOLD", "Elevate your herd's well-being with GOLD. Formulated with a balanced mix of grains, minerals, and supplements, GOLD promotes overall health and vitality in your cattle. From improved milk production to enhanced immune function, GOLD delivers exceptional results for your farm.", "5.0","400", R.drawable.product2));
        itemList.add(new ViewAllModel("MINERAL MIXTURE", "Unlock the potential of your herd with our premium MINERAL MIXTURE. Specially formulated to meet the unique nutritional needs of dairy cows, this blend is enriched with essential minerals and trace elements. Enhance the health and productivity of your cattle with MINERAL MIXTURE.", "5.0","300",R.drawable.product3));

        return itemList;
    }
}
