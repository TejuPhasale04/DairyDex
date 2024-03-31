package com.example.dairydex1.models;

import java.util.ArrayList;

public class RecommededModel {
    int img_url;

    public RecommededModel(){

    }
    public RecommededModel(int img_url) {
        this.img_url = img_url;
    }

    public int getImg_url() {
        return img_url;
    }

    public void setImg_url(int img_url) {
        this.img_url = img_url;
    }
    private static int lastContactId = 0;

    public static ArrayList<RecommededModel> createRecProductList(int numProducts, int[] imageResources) {
        ArrayList<RecommededModel> recproducts = new ArrayList<>();

        for (int i = 0; i < numProducts; i++) {
            // Ensure the imageResources array has enough elements for all products
            int imageIndex = i % imageResources.length;
            recproducts.add(new RecommededModel(imageResources[imageIndex]));
        }
        return recproducts;
    }

}
