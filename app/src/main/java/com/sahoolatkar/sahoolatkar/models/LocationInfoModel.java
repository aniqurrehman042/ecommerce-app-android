package com.sahoolatkar.sahoolatkar.models;

import android.net.Uri;

public class LocationInfoModel {
    String countryName;
    String languageName;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    String imageUrl;
    public LocationInfoModel(String countryName, String languageName,String imageUrl) {
        this.countryName = countryName;
        this.languageName = languageName;
        this.imageUrl = imageUrl;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }


}
