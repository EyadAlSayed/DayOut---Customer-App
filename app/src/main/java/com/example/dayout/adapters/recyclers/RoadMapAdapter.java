package com.example.dayout.adapters.recyclers;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dayout.R;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.models.trip.PlaceTripData;
import com.example.dayout.ui.activities.MainActivity;
import com.example.dayout.ui.fragments.home.PlaceInfoFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dayout.config.AppConstants.MAIN_FRC;

public class RoadMapAdapter extends RecyclerView.Adapter<RoadMapAdapter.ViewHolder> {

    List<PlaceTripData> list;
    Context context;
    boolean isWaitMark;

    public RoadMapAdapter(List<PlaceTripData> list, Context context) {
        this.list = list;
        this.context = context;
        this.isWaitMark = false;
    }

    public void refresh(List<PlaceTripData> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_road_map, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.placeName.setText(list.get(position).place.name);
        if (isWaitMark) {
            holder.changeToOffColor(holder.finishedMark);
            holder.changeToOffColor(holder.notFinishedMark);
            holder.changeToOnColor(holder.waitingMark);
        } else if (list.get(position).status) // if road map place is visited
        {
            holder.changeToOnColor(holder.finishedMark);
            holder.changeToOffColor(holder.notFinishedMark);
            holder.changeToOffColor(holder.waitingMark);
        } else {
            holder.changeToOffColor(holder.finishedMark);
            holder.changeToOnColor(holder.notFinishedMark);
            holder.changeToOffColor(holder.waitingMark);
            isWaitMark = true;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.place_name)
        TextView placeName;
        @BindView(R.id.finished_mark)
        ImageView finishedMark;
        @BindView(R.id.not_finished_mark)
        ImageView notFinishedMark;
        @BindView(R.id.waiting_mark)
        ImageView waitingMark;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            FN.addFixedNameFadeFragment(MAIN_FRC, (MainActivity) context, new PlaceInfoFragment(list.get(getAdapterPosition()).place_id));
        }


        public void changeToOffColor(ImageView imv) {
            imv.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.gray)));
        }

        public void changeToOnColor(ImageView imv) {
            imv.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.orange_500)));
        }


    }
}
