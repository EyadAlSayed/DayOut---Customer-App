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
import com.example.dayout.adapters.recyclers.myTrips.OldTripAdapter;
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
public class OldTripFragment extends Fragment {


    OldTripAdapter adapter;

    View view;

    @BindView(R.id.old_trip_rc)
    RecyclerView oldTripRc;

    @BindView(R.id.old_trips_no_history)
    TextView oldTripsNoHistory;

    @BindView(R.id.old_trips_refresh_layout)
    SwipeRefreshLayout oldTripsRefreshLayout;

    LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_old_trip, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }
    public OldTripFragment(OldTripAdapter adapter) {
        this.adapter = adapter;
    }

    
    private void initView(){
        loadingDialog = new LoadingDialog(requireContext());
        initRc();
        getDataFromApi();
    }

    private void initRc(){
        oldTripRc.setHasFixedSize(true);
        oldTripRc.setLayoutManager(new LinearLayoutManager(requireContext()));
        oldTripRc.setAdapter(adapter);
    }

    private void getDataFromRoom() {
        TripsDatabase.getINSTANCE(requireContext())
                .iTrip()
                .getHistoryTrips()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<TripData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<TripData> tripData) {
                        if (tripData.isEmpty()) {
                            oldTripsRefreshLayout.setVisibility(View.GONE);
                            oldTripsNoHistory.setVisibility(View.VISIBLE);
                        } else {
                            oldTripsRefreshLayout.setVisibility(View.VISIBLE);
                            oldTripsNoHistory.setVisibility(View.GONE);
                            adapter.refresh(tripData);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    private void getDataFromApi(){
        loadingDialog.show();
        TripViewModel.getINSTANCE().getHistoryTrips();
        TripViewModel.getINSTANCE().historyTripsMutableLiveData.observe(requireActivity(), historyTripsObserver);
    }

    private final Observer<Pair<TripListModel, String>> historyTripsObserver = new Observer<Pair<TripListModel, String>>() {
        @Override
        public void onChanged(Pair<TripListModel, String> tripModelStringPair) {
            loadingDialog.dismiss();
            if (tripModelStringPair != null) {
                if (tripModelStringPair.first != null) {
                    if (tripModelStringPair.first.data.isEmpty()) {
                        oldTripsRefreshLayout.setVisibility(View.GONE);
                        oldTripsNoHistory.setVisibility(View.VISIBLE);
                    } else {
                        oldTripsRefreshLayout.setVisibility(View.VISIBLE);
                        oldTripsNoHistory.setVisibility(View.GONE);
                        adapter.refresh(tripModelStringPair.first.data);
                    }
                } else {
                    getDataFromRoom();
                    new ErrorDialog(requireContext(), tripModelStringPair.second).show();
                }
            } else {
                getDataFromRoom();
                new ErrorDialog(requireContext(), "Error Connection");
            }
        }
    };

}