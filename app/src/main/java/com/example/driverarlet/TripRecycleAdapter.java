package com.example.driverarlet;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TripRecycleAdapter extends RecyclerView.Adapter<TripRecycleAdapter.ViewHolderClass> {

    Context context;
    List<TripModelClass> list;

    public TripRecycleAdapter(Context context, List<TripModelClass> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public TripRecycleAdapter.ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_list, parent, false);

        ViewHolderClass viewHolderClass = new ViewHolderClass(view);

        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull TripRecycleAdapter.ViewHolderClass holder, int position) {
        holder.distance.setText(list.get(position).getDistance());
        holder.from.setText(list.get(position).getFrom());
        holder.to.setText(list.get(position).getTo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {

        TextView from, to, distance;
        LinearLayout card;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
            distance = itemView.findViewById(R.id.distance);
            card = itemView.findViewById(R.id.card);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, AllIssues.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("name", list.get(getAdapterPosition()).getFrom()+"_"+list.get(getAdapterPosition()).getTo());
                    context.startActivity(intent);
                }
            });

        }
    }
}
