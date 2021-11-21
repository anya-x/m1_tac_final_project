package com.example.tvshowapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private Context context;

    private ArrayList<TvShowElement> data;

    private LayoutInflater inflater;

    private int previousPosition = 0;

    public RecyclerAdapter(Context context, ArrayList<TvShowElement> data) {

        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        Log.e("TAG", "onCreateViewHolder: executing");
        View view = inflater.inflate(R.layout.tv_show_layout, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, @SuppressLint("RecyclerView") int position) {

        Log.e("TAG", "onBindViewHolder: textViewIsNull " + (myViewHolder.textview == null ? "yes":"no"));
        myViewHolder.textview.setText(data.get(position).name);
        myViewHolder.imageView.setImageResource(data.get(position).picture);

        previousPosition = position;


        final int currentPosition = position;
        final TvShowElement infoData = data.get(position);

        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "OnClick" + position, Toast.LENGTH_SHORT).show();
                addItem(currentPosition, infoData);
            }
        });

        myViewHolder.imageView.setOnLongClickListener(v -> {

            Toast.makeText(context, "OnLongClick" + position, Toast.LENGTH_SHORT).show();

            removeItem(infoData);

            return true;
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textview;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            textview = (TextView) itemView.findViewById(R.id.textView);
            Log.e("TAG", "onBindViewHolder: textViewIsNull " + (textview == null ? "yes":"no"));

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            Log.e("TAG", "onBindViewHolder: imageViewIsNull " + (imageView == null ? "yes":"no"));
        }
    }

    // This removes the data from our Dataset and Updates the Recycler View.
    private void removeItem(TvShowElement infoData) {

        int currPosition = data.indexOf(infoData);
        data.remove(currPosition);
        notifyItemRemoved(currPosition);
    }

    // This method adds(duplicates) a Object (item ) to our Data set as well as Recycler View.
    private void addItem(int position, TvShowElement infoData) {

        data.add(position, infoData);
        notifyItemInserted(position);
    }
}

