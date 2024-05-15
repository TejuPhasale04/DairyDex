package com.example.dairydex1.models;

import com.google.firebase.database.DataSnapshot;

import java.io.Serializable;

public class CartModel implements Serializable {
    private String productName;
    private String productPrice;
    private String currentDate;
    private String currentTime;
    private int totalQuantity;
    private double totalPrice;

    String documentId;
    private String itemId;

    public CartModel() {

    }

    public CartModel(String productName, String productPrice, String currentDate, String currentTime, int totalQuantity, double totalPrice) { // Changed data type to double
        this.productName = productName;
        this.productPrice = productPrice;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDocumentId() {
        return documentId;
    }

    // Setter method for documentId
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}