package com.example.dayout.ui.fragments.trips;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.dayout.R;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.models.trip.TripData;
import com.example.dayout.models.trip.TripDetailsModel;
import com.example.dayout.models.trip.TripModel;
import com.example.dayout.models.trip.TripType;
import com.example.dayout.ui.dialogs.ErrorDialog;
import com.example.dayout.ui.dialogs.LoadingDialog;
import com.example.dayout.ui.dialogs.WarningDialog;
import com.example.dayout.viewModels.TripViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class UpcomingTripDetailsFragment extends Fragment {

    View view;

    @BindView(R.id.upcoming_trip_details_back_arrow)
    ImageButton upcomingTripDetailsBackArrow;

    @BindView(R.id.upcoming_trip_details_delete_icon)
    ImageButton upcomingTripDetailsDeleteIcon;

    @BindView(R.id.upcoming_trip_details_title)
    TextView upcomingTripDetailsTitle;

    @BindView(R.id.upcoming_trip_details_type)
    TextView upcomingTripDetailsType;

    @BindView(R.id.upcoming_trip_details_stops)
    TextView upcomingTripDetailsStops;

    @BindView(R.id.upcoming_trip_details_date)
    TextView upcomingTripDetailsDate;

    @BindView(R.id.upcoming_trip_details_price)
    TextView upcomingTripDetailsPrice;

    @BindView(R.id.upcoming_trip_details_expire_date)
    TextView upcomingTripDetailsExpireDate;

    @BindView(R.id.upcoming_trips_end_booking_date)
    TextView upcomingTripsEndBookingDate;

    @BindView(R.id.upcoming_trip_details_roadmap)
    TextView upcomingTripDetailsRoadMap;

    @BindView(R.id.upcoming_trip_details_roadmap_front_arrow)
    ImageButton upcomingTripDetailsRoadMapFrontArrow;

    @BindView(R.id.upcoming_trip_details_passengers_count)
    TextView upcomingTripDetailsPassengersCount;

    TripData data;

    LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_upcoming_trip_details, container, false);
        ButterKnife.bind(this, view);
        initViews();
        getDataFromApi();
        return view;
    }

    public UpcomingTripDetailsFragment(TripData data){
        this.data = data;
    }


    private void initViews(){
        loadingDialog = new LoadingDialog(requireContext());
        upcomingTripDetailsBackArrow.setOnClickListener(onBackClicked);
        upcomingTripDetailsDeleteIcon.setOnClickListener(onDeleteClicked);
        upcomingTripDetailsRoadMap.setOnClickListener(onRoadMapClicked);
        upcomingTripDetailsRoadMapFrontArrow.setOnClickListener(onRoadMapClicked);


    }

    private String getTypes(ArrayList<TripType> types){
        String tripTypes = "";

        for(int i = 0; i < types.size(); i++){
            if (i != 0) {
                tripTypes += ", " + types.get(i).name;
            } else if(i == 0)
                tripTypes += types.get(i).name;
        }

        return tripTypes;
    }

    private void setData(TripDetailsModel model){
        upcomingTripDetailsType.setText(getTypes(model.data.types));
        upcomingTripDetailsTitle.setText(model.data.title);
        upcomingTripDetailsDate.setText(model.data.begin_date);
        upcomingTripDetailsStops.setText(model.data.stopsToDetails);
        upcomingTripDetailsExpireDate.setText(model.data.expire_date);
        upcomingTripDetailsPrice.setText(String.valueOf(model.data.price));
        upcomingTripsEndBookingDate.setText(model.data.end_booking);
        upcomingTripDetailsPassengersCount.setText(String.valueOf(model.data.customer_trips_count));
    }

    private void getDataFromApi(){
        loadingDialog.show();
        TripViewModel.getINSTANCE().getTripDetails(data.id);
        TripViewModel.getINSTANCE().tripDetailsMutableLiveData.observe(requireActivity(), tripDetailsObserver);
    }

    private final Observer<Pair<TripDetailsModel, String>> tripDetailsObserver = new Observer<Pair<TripDetailsModel, String>>() {
        @Override
        public void onChanged(Pair<TripDetailsModel, String> tripDetailsModelStringPair) {
            loadingDialog.dismiss();
            if(tripDetailsModelStringPair != null){
                if(tripDetailsModelStringPair.first != null){
                    setData(tripDetailsModelStringPair.first);
                    data =tripDetailsModelStringPair.first.data;
                    //trip is active
                    if(data.isActive)
                        upcomingTripDetailsDeleteIcon.setVisibility(View.GONE);
                } else
                    new ErrorDialog(requireContext(), tripDetailsModelStringPair.second).show();
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

    private final View.OnClickListener onEditClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private final View.OnClickListener onDeleteClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new WarningDialog(requireContext(), getResources().getString(R.string.canceling_reservation)).show();
        }
    };

    private final View.OnClickListener onRoadMapClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private final View.OnClickListener onCheckClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private final View.OnClickListener onBeginTripClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
