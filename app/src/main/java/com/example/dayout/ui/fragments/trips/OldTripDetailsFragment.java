package com.example.dayout.ui.fragments.trips;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.dayout.R;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.models.trip.TripModel;
import com.example.dayout.ui.dialogs.ErrorDialog;
import com.example.dayout.ui.dialogs.LoadingDialog;
import com.example.dayout.ui.dialogs.MessageDialog;
import com.example.dayout.viewModels.TripViewModel;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dayout.config.AppSharedPreferences.GET_USER_ID;

@SuppressLint("NonConstantResourceId")
public class OldTripDetailsFragment extends Fragment {

    View view;

    @BindView(R.id.old_trip_details_back_arrow)
    ImageButton oldTripDetailsBackArrow;

    @BindView(R.id.old_trip_details_title)
    TextView oldTripDetailsTitle;

    @BindView(R.id.old_trip_details_type)
    TextView oldTripDetailsType;

    @BindView(R.id.old_trip_details_stops)
    TextView oldTripDetailsStops;

    @BindView(R.id.old_trip_details_date)
    TextView oldTripDetailsDate;

    @BindView(R.id.old_trip_details_price)
    TextView oldTripDetailsPrice;

    @BindView(R.id.old_trip_details_end_booking_date)
    TextView oldTripDetailsEndBookingDate;

    @BindView(R.id.old_trips_end_confirmation_date)
    TextView oldTripsEndConfirmationDate;

    @BindView(R.id.old_trip_details_roadmap)
    TextView oldTripDetailsRoadMap;

    @BindView(R.id.old_trip_details_roadmap_front_arrow)
    ImageButton oldTripDetailsRoadMapFrontArrow;

    @BindView(R.id.old_trip_details_passengers_count)
    TextView oldTripDetailsPassengersCount;

    @BindView(R.id.old_trip_details_ratingBar)
    RatingBar oldTripDetailsRatingBar;

    float tripRating = 0;

    TripModel.Data data;

    LoadingDialog loadingDialog;

    public OldTripDetailsFragment(TripModel.Data data) {
        this.data = data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_old_trip_details, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        setData();
        oldTripDetailsBackArrow.setOnClickListener(onBackClicked);
        oldTripDetailsRoadMap.setOnClickListener(onRoadMapClicked);
        oldTripDetailsRoadMapFrontArrow.setOnClickListener(onRoadMapClicked);
        oldTripDetailsRatingBar.setOnRatingBarChangeListener(onRatingBarChanged);
    }

    private void setData(){
        oldTripDetailsTitle.setText(data.title);
        oldTripDetailsDate.setText(data.begin_date);
        oldTripDetailsEndBookingDate.setText(data.end_booking);
        oldTripDetailsPrice.setText(String.valueOf(data.price));
        oldTripsEndConfirmationDate.setText(data.expire_date);
        //oldTripDetailsPassengersCount.setText(String.valueOf(data.customer_trips.size()));
    }

    private JsonObject getRateData(){
        JsonObject object = new JsonObject();

        object.addProperty("customer_id", GET_USER_ID());
        object.addProperty("trip_id", data.id);
        //object.addProperty("checkout", );
        object.addProperty("rate", tripRating);

        return object;
    }

    private void rateTrip(){
        loadingDialog.show();
        TripViewModel.getINSTANCE().rateTrip(getRateData());
        TripViewModel.getINSTANCE().rateTripMutableLiveData.observe(requireActivity(), rateTripObserver);
    }

    private final Observer<Pair<TripModel, String>> rateTripObserver = new Observer<Pair<TripModel, String>>() {
        @Override
        public void onChanged(Pair<TripModel, String> tripModelStringPair) {
            loadingDialog.dismiss();
            if(tripModelStringPair != null){
                if(tripModelStringPair.first != null){
                    new MessageDialog(requireContext(), getResources().getString(R.string.trip_rated)).show();
                } else
                    new ErrorDialog(requireContext(), tripModelStringPair.second).show();
            } else
                new ErrorDialog(requireContext(), "Error Connection").show();
        }
    };

    private final View.OnClickListener onBackClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FN.popTopStack(requireActivity());
        }
    };

    private final View.OnClickListener onRoadMapClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private final RatingBar.OnRatingBarChangeListener onRatingBarChanged = new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            tripRating = rating;
            rateTrip();
        }
    };
}
