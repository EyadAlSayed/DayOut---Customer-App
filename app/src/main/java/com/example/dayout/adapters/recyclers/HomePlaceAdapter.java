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
import com.example.dayout.helpers.view.FN;
import com.example.dayout.helpers.view.NoteMessage;
import com.example.dayout.models.PopularPlace;
import com.example.dayout.ui.activities.MainActivity;
import com.example.dayout.ui.dialogs.ErrorDialog;
import com.example.dayout.ui.fragments.home.HomeFragment;
import com.example.dayout.ui.fragments.home.PlaceInfoFragment;
import com.example.dayout.viewModels.PlaceViewModel;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dayout.config.AppConstants.MAIN_FRC;
import static com.example.dayout.config.AppSharedPreferences.GET_USER_ID;

public class HomePlaceAdapter extends RecyclerView.Adapter<HomePlaceAdapter.ViewHolder> {

    List<PopularPlace.Data> list;
    Context context;

    public HomePlaceAdapter(List<PopularPlace.Data> list, Context context) {
        this.list = list;
        this.context = context;
    }


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
//        holder.placeName.setText(list.get(position).name);
//        holder.shortDescrption.setText(list.get(position).summary);



    }

    @Override
    public int getItemCount() {
        return 5;
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
            initImageSlider();
            addFavoriteButton.setOnClickListener(onAddFavoriteClicked);
        }

        private final View.OnClickListener onAddFavoriteClicked = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaceViewModel.getINSTANCE().addToFavorite(getJsonObject());
                PlaceViewModel.getINSTANCE().successfulMutableLiveData.observe((MainActivity)context,successfulObserver);
            }
        };

        private Observer<Pair<Boolean,String>> successfulObserver = new Observer<Pair<Boolean, String>>() {
            @Override
            public void onChanged(Pair<Boolean, String> booleanStringPair) {
                if (booleanStringPair != null){
                    if (booleanStringPair.first != null && booleanStringPair.first){
                        addFavoriteButton.setVisibility(View.GONE);
                        NoteMessage.message(context,"successful added");
                    }
                    else new ErrorDialog(context,booleanStringPair.second).show();
                }
                else new ErrorDialog(context,"connection error").show();
            }
        };

        private JsonObject getJsonObject(){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("userId",GET_USER_ID());
            jsonObject.addProperty("placeId ",list.get(getAdapterPosition()).id);
            return jsonObject;

        }

        @Override
        public void onClick(View v) {
            FN.addFixedNameFadeFragment(MAIN_FRC, (MainActivity)context, new PlaceInfoFragment(list.get(getAdapterPosition())));
        }

        private void initImageSlider() {
            List<SlideModel> slideModels = new ArrayList<>();
            slideModels.add(new SlideModel(R.drawable.a, ScaleTypes.FIT)); // for one image
            slideModels.add(new SlideModel(R.drawable.aa, ScaleTypes.FIT)); // you can with title
            imageSlider.setImageList(slideModels);
            imageSlider.setScrollBarFadeDuration(10000);
        }
    }
}
