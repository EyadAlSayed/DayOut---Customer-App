package com.example.dayout.adapters.recyclers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.dayout.R;
import com.example.dayout.models.popualrPlace.PopularPlacePhoto;
import com.example.dayout.models.trip.TripPost;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dayout.api.ApiClient.BASE_URL;
import static com.example.dayout.viewModels.PlaceViewModel.PLACE_PHOTO_URL;

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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trip_post, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tripTitle.setText(list.get(position).title);
        holder.tripDate.setText(list.get(position).begin_date + "  -  " + list.get(position).expire_date);
        holder.endTripBookinDate.setText(list.get(position).end_booking);
        holder.tripDescription.setText(list.get(position).description);
        holder.bindImageSlider(list.get(position).trip_photos);

        /**
         * trip count passenger
         * places name
         */
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.trip_title)
        TextView tripTitle;
        @BindView(R.id.trip_image_slider)
        ImageSlider tripImageSlider;
        @BindView(R.id.trip_description)
        TextView tripDescription;
        @BindView(R.id.trip_stops)
        TextView tripStops;
        @BindView(R.id.trip_date)
        TextView tripDate;
        @BindView(R.id.trip_passengers_count)
        TextView tripPassengersCount;
        @BindView(R.id.end_trip_bookin_date)
        TextView endTripBookinDate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        private void bindImageSlider(List<PopularPlacePhoto> photos) {
            List<SlideModel> slideModels = new ArrayList<>();
            String baseUrl = BASE_URL.substring(0,BASE_URL.length()-1);
            for (PopularPlacePhoto ph : photos) {
                slideModels.add(new SlideModel(baseUrl+ph.path
                        , ScaleTypes.FIT));
            }

            tripImageSlider.setImageList(slideModels);

            tripImageSlider.setScrollBarFadeDuration(10000);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
