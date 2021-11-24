package com.example.tvshowapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    Switch sw1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        recyclerAdapter = new RecyclerAdapter(this, MockData.getData());
        recyclerView.setAdapter(recyclerAdapter);
        LinearLayoutManager mLayoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager); // Vertical Orientation By Default
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 2);
        sw1 = (Switch)findViewById(R.id.switch1);
        sw1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (sw1.isChecked()) {
                    recyclerView.setLayoutManager(mLayoutManager);
                } else {
                    recyclerView.setLayoutManager(mGridLayoutManager);

                }

            }
        });
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }
}


