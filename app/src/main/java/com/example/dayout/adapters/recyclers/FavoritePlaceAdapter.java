package com.example.dayout.adapters.recyclers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.example.dayout.R;
import com.example.dayout.ui.dialogs.LoadingDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritePlaceAdapter extends RecyclerView.Adapter<FavoritePlaceAdapter.ViewHolder> {

    List<String> list;
    Context context;
    LoadingDialog loadingDialog;

    public FavoritePlaceAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        this.loadingDialog = new LoadingDialog(context);
    }

    public void refresh(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
        view.findViewById(R.id.add_favorite_btn).setVisibility(View.GONE);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.image_slider)
        ImageSlider imageSlider;
        @BindView(R.id.place_name)
        TextView placeName;
        @BindView(R.id.short_descrption)
        TextView shortDescrption;
        @BindView(R.id.delete_favorite_btn)
        ImageButton deleteFavoriteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
            deleteFavoriteButton.setOnClickListener(onDeleteClicked);
        }

        private final View.OnClickListener onDeleteClicked = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  loadingDialog.show();
            }
        };

        @Override
        public void onClick(View v) {
        }
    }
}
