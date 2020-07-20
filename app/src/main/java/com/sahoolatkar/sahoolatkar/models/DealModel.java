package com.sahoolatkar.sahoolatkar.models;

public class DealModel {
    private String imageUrl;
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public DealModel(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
