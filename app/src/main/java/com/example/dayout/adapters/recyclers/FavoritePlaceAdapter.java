package com.example.dayout.adapters.recyclers;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.dayout.R;
import com.example.dayout.helpers.view.NoteMessage;
import com.example.dayout.models.popualrPlace.PlaceData;
import com.example.dayout.models.popualrPlace.PopularPlacePhoto;
import com.example.dayout.ui.activities.MainActivity;
import com.example.dayout.ui.dialogs.notify.ErrorDialog;
import com.example.dayout.ui.dialogs.notify.LoadingDialog;
import com.example.dayout.viewModels.PlaceViewModel;
import com.google.gson.JsonObject;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dayout.api.ApiClient.BASE_URL;
import static com.example.dayout.config.AppSharedPreferences.GET_USER_ID;

public class FavoritePlaceAdapter extends RecyclerView.Adapter<FavoritePlaceAdapter.ViewHolder> {

    List<PlaceData> list;
    Context context;
    LoadingDialog loadingDialog;

    public FavoritePlaceAdapter(List<PlaceData> list, Context context) {
        this.list = list;
        this.context = context;
        this.loadingDialog = new LoadingDialog(context);
    }

    public void refresh(List<PlaceData> list) {
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
        holder.placeName.setText(list.get(position).name);
        holder.shortDescrption.setText(list.get(position).summary);
        holder.bindImageSlider(list.get(position).photos);
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

        private void bindImageSlider(List<PopularPlacePhoto> photos) {
            List<SlideModel> slideModels = new ArrayList<>();

            String baseUrl = BASE_URL.substring(0,BASE_URL.length()-1);

            for (PopularPlacePhoto ph : photos) {
                slideModels.add(new SlideModel(baseUrl + ph.path
                        , ScaleTypes.FIT));
            }

            imageSlider.setImageList(slideModels);

            imageSlider.setScrollBarFadeDuration(10000);
        }

        private final View.OnClickListener onDeleteClicked = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.show();
                PlaceViewModel.getINSTANCE().addToFavorite(getFavoritePlaceObj());
                PlaceViewModel.getINSTANCE().successfulMutableLiveData.observe((MainActivity) context, successfulObserver);
            }
        };

        private Observer<Pair<Boolean, String>> successfulObserver = new Observer<Pair<Boolean, String>>() {
            @Override
            public void onChanged(Pair<Boolean, String> booleanStringPair) {
                deleteFavoriteButton.setEnabled(true);
                if (booleanStringPair != null) {
                    if (booleanStringPair.first != null && booleanStringPair.first) {
                        notifyItemRemoved(getAdapterPosition());
                    } else new ErrorDialog(context, booleanStringPair.second).show();
                } else new ErrorDialog(context, "connection error").show();
            }
        };


        private JsonObject getFavoritePlaceObj() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("userId", GET_USER_ID());
            jsonObject.addProperty("placeId",list.get(getAdapterPosition()).id);
            return jsonObject;
        }
        @Override
        public void onClick(View v) {

        }
    }
}
