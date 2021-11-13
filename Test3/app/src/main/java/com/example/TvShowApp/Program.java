package com.example.TvShowApp;

import android.net.Uri;

public class Program {
    public String title;
    public String description;
    private String imageUrl;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Uri getImageUrl() {
        return Uri.parse(imageUrl);
    }
}
