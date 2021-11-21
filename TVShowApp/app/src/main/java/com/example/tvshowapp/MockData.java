package com.example.tvshowapp;

import java.util.ArrayList;

public class MockData {
    public static ArrayList<TvShowElement> getData() {

        ArrayList<TvShowElement> data = new ArrayList<>();

        for (int i = 0; i < 25; i++) {

            TvShowElement current = new TvShowElement();
            current.setPicture(R.drawable.dog1);
            current.setName("doggo 1");

            data.add(current);
            System.out.printf(current.getName());
        }


        return data;
    }

}
