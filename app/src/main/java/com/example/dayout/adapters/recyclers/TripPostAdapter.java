package com.example.dayout.adapters.recyclers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dayout.R;
import com.example.dayout.models.trip.TripPost;

import java.util.List;

import butterknife.ButterKnife;

public class TripPostAdapter extends RecyclerView.Adapter<TripPostAdapter.ViewHolder> {

    List<TripPost.Data> list;
    Context context;

    public TripPostAdapter(List<TripPost.Data> list, Context context) {
        this.list = list;
        this.context = context;
    }


    public void refresh(List<TripPost.Data> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TripPostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trip_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripPostAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
