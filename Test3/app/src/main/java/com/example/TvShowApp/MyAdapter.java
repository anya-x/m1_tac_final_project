package com.example.TvShowApp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    private List<Program> programList = new ArrayList<Program>();

    public MyAdapter(Context ct){
        context = ct;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**The LayoutInflater class is used to instantiate the contents of layout XML files into their corresponding View objects.**/
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Program program = programList.get(position);

        holder.myText1.setText(program.getTitle());
        holder.myText2.setText(program.getDescription());

        Picasso.get().load(program.getImageUrl()).into(holder.myImage);
    }

    @Override
    public int getItemCount() {
        return programList.size();
    }

    public void setProgramList(List<Program> programs) {
        programList = programs;
        this.notifyDataSetChanged();
    }

    public void informError(Exception e) {
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView myImage;
        TextView myText1, myText2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.myTitle);
            myText2 = itemView.findViewById(R.id.myDescrip);
            myImage = itemView.findViewById(R.id.myImage);
        }
    }
}
