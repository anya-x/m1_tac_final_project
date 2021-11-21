package com.example.tvshowapp;

import android.graphics.drawable.Drawable;

public class TvShowElement {
    public String name;
    public int picture;

    public void setPicture(int picture){
        this.picture = picture;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getPicture(){
        return this.picture;
    }

    public String getName(){
        return this.name;
    }
}
