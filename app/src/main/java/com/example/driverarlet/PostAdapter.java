package com.example.driverarlet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolderClass> {
    Context context;
    List<PostModelClass> list;

    public PostAdapter(Context context, List<PostModelClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_ui, parent, false);

        ViewHolderClass viewHolderClass = new ViewHolderClass(view);

        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolderClass holder, int position) {
        holder.author.setText(list.get(position).getAuthor());
        holder.content_summary.setText(list.get(position).getContent_summary());
        holder.location.setText(list.get(position).getLocation());
        holder.time.setText(list.get(position).getTime());

        Glide.with(context).load(list.get(position).getContent_thumbnail()).into(holder.content_thumbnail);

        holder.play_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView author, content_summary, location, time;
        ImageView content_thumbnail;
        LinearLayout play_location;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

            author = itemView.findViewById(R.id.author);
            content_summary = itemView.findViewById(R.id.content_summary);
            location = itemView.findViewById(R.id.location);
            time = itemView.findViewById(R.id.time);
            content_thumbnail = itemView.findViewById(R.id.content_thumbnail);
            play_location = itemView.findViewById(R.id.play_location);

        }
    }
}
