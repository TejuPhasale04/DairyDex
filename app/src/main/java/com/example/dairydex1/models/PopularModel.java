package com.example.dairydex1.models;

import com.example.dairydex1.R;

import java.util.ArrayList;

public class PopularModel {
    private int imageResource;
    String name;
    String description;
    String rating;
    String discount;
    String type;
    String img_url;

    public PopularModel(String name, String description, String rating, String discount, String imgUrl){

    }
    public PopularModel(String name, String description, String rating, String discount, String type, int imageResource) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.discount = discount;
        this.type = type;
        this.imageResource = imageResource;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }

    public String getDiscount() {
        return discount;
    }

    public String getType() {
        return type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    private static int lastContactId = 0;

    public static ArrayList<PopularModel> createProductList(int numProducts, int[] imageResources) {
        ArrayList<PopularModel> products = new ArrayList<>();

        for (int i = 0; i < numProducts; i++) {
            // Ensure the imageResources array has enough elements for all products
            int imageIndex = i % imageResources.length;
            products.add(new PopularModel("Product " + (i + 1), "Description " + (i + 1), "5.0", "50%", "xyz", imageResources[imageIndex]));
        }
        return products;
    }



}
