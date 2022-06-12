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
import com.example.dayout.models.trip.tripType.TripType;
import com.example.dayout.ui.dialogs.ErrorDialog;
import com.example.dayout.ui.dialogs.LoadingDialog;
import com.example.dayout.ui.dialogs.MessageDialog;
import com.example.dayout.ui.dialogs.WarningDialog;
import com.example.dayout.viewModels.TripViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dayout.config.AppConstants.MAIN_FRC;

@SuppressLint("NonConstantResourceId")
public class TripDetailsFragment extends Fragment {

    View view;

    @BindView(R.id.trip_details_back_arrow)
    ImageButton tripDetailsBackArrow;

    @BindView(R.id.trip_details_delete_icon)
    ImageButton tripDetailsDeleteIcon;

    @BindView(R.id.trip_details_title)
    TextView tripDetailsTitle;

    @BindView(R.id.trip_details_type)
    TextView tripDetailsType;

    @BindView(R.id.trip_details_stops)
    TextView tripDetailsStops;

    @BindView(R.id.trip_details_date)
    TextView tripDetailsDate;

    @BindView(R.id.trip_details_price)
    TextView tripDetailsPrice;

    @BindView(R.id.trip_details_expire_date)
    TextView tripDetailsExpireDate;

    @BindView(R.id.trips_end_booking_date)
    TextView tripsEndBookingDate;

    @BindView(R.id.trip_details_roadmap)
    TextView tripDetailsRoadMap;

    @BindView(R.id.trip_details_roadmap_front_arrow)
    ImageButton tripDetailsRoadMapFrontArrow;

    @BindView(R.id.trip_details_passengers_count)
    TextView tripDetailsPassengersCount;

    @BindView(R.id.book_trip_button)
    Button bookTripButton;

    boolean isPost;

    TripData data;

    LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trip_details, container, false);
        ButterKnife.bind(this, view);
        initViews();
        getDataFromApi();
        return view;
    }

    public TripDetailsFragment(TripData data, boolean isPost){
        this.data = data;
        this.isPost = isPost;
    }


    private void initViews() {
        loadingDialog = new LoadingDialog(requireContext());
        tripDetailsBackArrow.setOnClickListener(onBackClicked);
        tripDetailsDeleteIcon.setOnClickListener(onDeleteClicked);
        tripDetailsRoadMap.setOnClickListener(onRoadMapClicked);
        tripDetailsRoadMapFrontArrow.setOnClickListener(onRoadMapClicked);
        if (isPost) {
            bookTripButton.setVisibility(View.VISIBLE);
            bookTripButton.setOnClickListener(onBookClicked);
        }
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
        tripDetailsType.setText(getTypes(model.data.types));
        tripDetailsTitle.setText(model.data.title);
        tripDetailsDate.setText(model.data.begin_date);
        tripDetailsStops.setText(model.data.stopsToDetails);
        tripDetailsExpireDate.setText(model.data.expire_date);
        tripDetailsPrice.setText(String.valueOf(model.data.price));
        tripsEndBookingDate.setText(model.data.end_booking);
        tripDetailsPassengersCount.setText(String.valueOf(model.data.customer_trips_count));
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
                        tripDetailsDeleteIcon.setVisibility(View.GONE);
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


    private final View.OnClickListener onDeleteClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new WarningDialog(requireContext(), getResources().getString(R.string.canceling_reservation)).show();
        }
    };

    private final View.OnClickListener onRoadMapClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FN.addFixedNameFadeFragment(MAIN_FRC,requireActivity(),new RoadMapFragment(data.id));
        }
    };

    private final View.OnClickListener onBookClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new MessageDialog(requireContext(), "Enter names of passengers you are booking for. Please consider including your name if you are booking for yourself as well.").show();
            FN.addFixedNameFadeFragment(MAIN_FRC, requireActivity(), new BookTripFragment(data.id));
        }
    };
}