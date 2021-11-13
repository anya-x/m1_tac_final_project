package com.example.TvShowApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    TVMazeDatasource datasource;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datasource = new TVMazeDatasource(Volley.newRequestQueue(this));
        recyclerView = findViewById(R.id.list_test);
        myAdapter = new MyAdapter(this);
        datasource.fetchPrograms(programs -> { myAdapter.setProgramList(programs) ; }, e -> {myAdapter.informError(e) ;}, "shows");

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(getApplicationContext(), "Portrait mode", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(getApplicationContext(), "Landscape mode", Toast.LENGTH_SHORT).show();
        }
    }

}