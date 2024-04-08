package com.example.dairydex1.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ViewAllModel implements Serializable {
    private String name;
    private String description;
    private String rating;
    private String price;
    private int img_url;

    public ViewAllModel() {
        // Default constructor required for Firebase
    }

    public ViewAllModel(String name, String description, String rating,String price,int img_url) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.img_url = img_url;
        this.price=price;
    }

    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price){
        this.price=price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getImg_url() {
        return img_url;
    }

    public void setImg_url(int img_url) {
        this.img_url = img_url;
    }

    public static ArrayList<ViewAllModel> createViewAllList(int numItems, String[] names, String[] descriptions, String[] ratings,String[] price, int[] imageResources) {
        ArrayList<ViewAllModel> items = new ArrayList<>();

        for (int i = 0; i < numItems; i++) {
            // Ensure the arrays have enough elements for all items
            int nameIndex = i % names.length;
            int descriptionIndex = i % descriptions.length;
            int ratingIndex = i % ratings.length;
            int imageIndex = i % imageResources.length;
            int priceIndex=i % price.length;

            items.add(new ViewAllModel(names[nameIndex], descriptions[descriptionIndex], ratings[ratingIndex],price[priceIndex],imageResources[imageIndex]));
        }
        return items;
    }
}
