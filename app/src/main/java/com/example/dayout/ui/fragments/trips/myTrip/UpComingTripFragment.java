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
import com.example.dayout.models.room.tripsRoom.database.TripsDatabase;
import com.example.dayout.models.trip.TripData;
import com.example.dayout.models.trip.TripListModel;
import com.example.dayout.ui.dialogs.notify.ErrorDialog;
import com.example.dayout.ui.dialogs.notify.LoadingDialog;
import com.example.dayout.viewModels.TripViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

    private void getDataFromRoom() {
        TripsDatabase.getINSTANCE(requireContext())
                .iTrip()
                .getUpComingTrips()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<TripData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<TripData> tripData) {
                        if (tripData.isEmpty()) {
                            upcomingTripsRefreshLayout.setVisibility(View.GONE);
                            upcomingTripsNoTrips.setVisibility(View.VISIBLE);
                        } else {
                            upcomingTripsRefreshLayout.setVisibility(View.VISIBLE);
                            upcomingTripsNoTrips.setVisibility(View.GONE);
                            adapter.refresh(tripData);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    private void setAsUpcoming(List<TripData> list){
        for(TripData trip : list){
            trip.isUpcoming = true;
        }
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
            if (listStringPair != null) {
                if (listStringPair.first != null) {
                    if (listStringPair.first.data.isEmpty()) {
                        upcomingTripsRefreshLayout.setVisibility(View.GONE);
                        upcomingTripsNoTrips.setVisibility(View.VISIBLE);
                    } else {
                        upcomingTripsRefreshLayout.setVisibility(View.VISIBLE);
                        upcomingTripsNoTrips.setVisibility(View.GONE);
                        adapter.refresh(listStringPair.first.data);
                        setAsUpcoming(listStringPair.first.data);
                    }
                } else {
                    getDataFromRoom();
                    new ErrorDialog(requireContext(), listStringPair.second).show();
                }
            } else {
                getDataFromRoom();
                new ErrorDialog(requireContext(), "Error Connection").show();
            }
        }
    };
}