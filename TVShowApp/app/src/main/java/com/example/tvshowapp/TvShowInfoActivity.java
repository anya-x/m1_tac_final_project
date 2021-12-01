package com.example.tvshowapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.tvshowapp.model.TvShowInfo;


import androidx.appcompat.app.AppCompatActivity;

public class TvShowInfoActivity extends AppCompatActivity {

    public TextView detail;
    public TextView subTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv_show_info);

        subTitle = findViewById(R.id.subTitle);
        detail = findViewById(R.id.detail);
        TextView tvShowTitle = findViewById(R.id.tvShowTitle);
        ImageView imageView2 = findViewById(R.id.imageView2);

        Intent intent = getIntent();

        String referenceTvShow = intent.getStringExtra("textview");
        tvShowTitle.setText(referenceTvShow);

        String urlImage = intent.getStringExtra("imageView");
        Glide.with(this)
                .load(urlImage)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView2);

        callTvShowDetail(referenceTvShow);
    }
    private void callTvShowDetail(String referenceTvShow ) {
        // to finish once we have API_client
        // Single<TvShowInfo> tvShowInfo = API_Client..getInstance().getMyApi().getProductInfo(referenceProduct)
        //                .subscribeOn(Schedulers.io())
        //                .observeOn(AndroidSchedulers.mainThread());



    }


}
