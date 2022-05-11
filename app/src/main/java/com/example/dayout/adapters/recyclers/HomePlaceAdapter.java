package com.example.dayout.adapters.recyclers;

import android.content.Context;
import android.util.Log;
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
import com.example.dayout.helpers.view.FN;
import com.example.dayout.helpers.view.NoteMessage;
import com.example.dayout.models.PopualrPlace.PopularPlace;
import com.example.dayout.ui.activities.MainActivity;
import com.example.dayout.ui.dialogs.ErrorDialog;
import com.example.dayout.ui.fragments.home.PlaceInfoFragment;
import com.example.dayout.viewModels.PlaceViewModel;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


import static com.example.dayout.config.AppConstants.MAIN_FRC;
import static com.example.dayout.config.AppSharedPreferences.GET_USER_ID;
import static com.example.dayout.viewModels.PlaceViewModel.PLACE_PHOTO_URL;

public class HomePlaceAdapter extends RecyclerView.Adapter<HomePlaceAdapter.ViewHolder> {

    private static final String TAG = "Home place Adapter";
    List<PopularPlace.Data> list;
    Context context;

    public HomePlaceAdapter(List<PopularPlace.Data> list, Context context) {
        this.list = list;
        this.context = context;
    }

//    public void insertRoomObject(PopularPlace.Data popularPlace) {
//
//        // insert object in room database
//        ((MainActivity) context).roomPopularPlaces
//                .insertPopularPlace(popularPlace)
//                .subscribeOn(Schedulers.computation()).subscribe(new CompletableObserver() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.e(TAG, "onError: " + e.toString());
//            }
//        });
//    }

    public void refreshList(List<PopularPlace.Data> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_place, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

     //   insertRoomObject(list.get(position));


        holder.placeName.setText(list.get(position).name);
        holder.shortDescrption.setText(list.get(position).summary);
        holder.bindImageSlider(list.get(position).photos);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.place_name)
        TextView placeName;
        @BindView(R.id.short_descrption)
        TextView shortDescrption;
        @BindView(R.id.image_slider)
        ImageSlider imageSlider;
        @BindView(R.id.add_favorite_btn)
        ImageButton addFavoriteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
            addFavoriteButton.setOnClickListener(onAddFavoriteClicked);
        }

        private final View.OnClickListener onAddFavoriteClicked = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFavoriteButton.setEnabled(false);
                PlaceViewModel.getINSTANCE().addToFavorite(getJsonObject());
                PlaceViewModel.getINSTANCE().successfulMutableLiveData.observe((MainActivity) context, successfulObserver);
            }
        };

        private Observer<Pair<Boolean, String>> successfulObserver = new Observer<Pair<Boolean, String>>() {
            @Override
            public void onChanged(Pair<Boolean, String> booleanStringPair) {
                addFavoriteButton.setEnabled(true);
                if (booleanStringPair != null) {
                    if (booleanStringPair.first != null && booleanStringPair.first) {
                        addFavoriteButton.setVisibility(View.GONE);
                        NoteMessage.message(context, "successful added");
                    } else new ErrorDialog(context, booleanStringPair.second).show();
                } else new ErrorDialog(context, "connection error").show();
            }
        };

        private JsonObject getJsonObject() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("userId", GET_USER_ID());
            jsonObject.addProperty("placeId", list.get(getAdapterPosition()).id);
            return jsonObject;
        }

        @Override
        public void onClick(View v) {
            FN.addFixedNameFadeFragment(MAIN_FRC, (MainActivity) context, new PlaceInfoFragment(list.get(getAdapterPosition())));
        }

        private void bindImageSlider(List<PopularPlace.Photo> photos) {
            List<SlideModel> slideModels = new ArrayList<>();

            for (PopularPlace.Photo ph : photos) {
                slideModels.add(new SlideModel(PLACE_PHOTO_URL + ph.id
                        , ScaleTypes.FIT));
            }

            imageSlider.setImageList(slideModels);

            imageSlider.setScrollBarFadeDuration(10000);
        }
    }
}
