package com.example.newsfactory;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

import Models.ArticlesItem;

public class RecylerViewAdapter extends RecyclerView.Adapter<ViewHolder>{

    private static final String TAG = "RecylerViewAdapter";

    private ArrayList<ArticlesItem> lista = new ArrayList<>();
    private Context mContext;


    public RecylerViewAdapter(Context mContext, ArrayList<ArticlesItem> lista) {
        this.lista = lista;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_listitem, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.textView.setText(lista.get(position).getTitle());

        Glide.with(mContext)
                .asBitmap()
                .load(lista.get(position).getUrlToImage())
                .into(holder.imageView);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, NewsDescriptionActivity.class);
                intent.putExtra("image_url",lista.get(position).getUrlToImage());
                intent.putExtra("title", lista.get(position).getTitle());
                intent.putExtra("content", lista.get(position).getContent());
                mContext.startActivity(intent);

            }
        });


    }


    @Override
    public int getItemCount() {
        return lista.size();
    }



}
