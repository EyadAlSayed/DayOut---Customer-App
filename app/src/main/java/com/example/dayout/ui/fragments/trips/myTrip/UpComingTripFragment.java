package com.example.dayout.ui.fragments.trips.myTrip;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dayout.R;
import com.example.dayout.adapters.recyclers.myTrips.UpComingTripAdapter;
import com.example.dayout.models.trip.TripListModel;
import com.example.dayout.ui.dialogs.notify.ErrorDialog;
import com.example.dayout.ui.dialogs.notify.LoadingDialog;
import com.example.dayout.viewModels.TripViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class UpComingTripFragment extends Fragment {


    UpComingTripAdapter adapter;

    View view;

    @BindView(R.id.up_coming_trip_rc)
    RecyclerView upComingTripRc;

    @BindView(R.id.upcoming_trips_no_trips)
    TextView upcomingTripsNoTrips;

    @BindView(R.id.upcoming_trips_refresh_layout)
    SwipeRefreshLayout upcomingTripsRefreshLayout;

    LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_up_coming_trip, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public UpComingTripFragment(UpComingTripAdapter adapter) {
        this.adapter = adapter;
    }

    private void initView(){
        loadingDialog = new LoadingDialog(requireContext());
        initRc();
        getDataFromApi();
    }

    private void initRc(){
        upComingTripRc.setHasFixedSize(true);
        upComingTripRc.setLayoutManager(new LinearLayoutManager(requireContext()));
        upComingTripRc.setAdapter(adapter);
    }

    private void getDataFromApi(){
        loadingDialog.show();
        TripViewModel.getINSTANCE().getUpcomingTrips();
        TripViewModel.getINSTANCE().upcomingTripsMutableLiveData.observe(requireActivity(), upcomingTripObserver);
    }

    private final Observer<Pair<TripListModel, String>> upcomingTripObserver = new Observer<Pair<TripListModel, String>>() {
        @Override
        public void onChanged(Pair<TripListModel, String> listStringPair) {
            loadingDialog.dismiss();
            if(listStringPair != null){
                if (listStringPair.first != null) {
                    if (listStringPair.first.data.isEmpty()) {
                        upcomingTripsRefreshLayout.setVisibility(View.GONE);
                        upcomingTripsNoTrips.setVisibility(View.VISIBLE);
                    } else {
                        upcomingTripsRefreshLayout.setVisibility(View.VISIBLE);
                        upcomingTripsNoTrips.setVisibility(View.GONE);
                        adapter.refresh(listStringPair.first.data);
                    }
                }else{
                    new ErrorDialog(requireContext(), listStringPair.second).show();
                }
            } else
                new ErrorDialog(requireContext(), "Error Connection").show();
        }
    };
}