package com.example.dayout.ui.fragments.trips.myTrip;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dayout.R;
import com.example.dayout.adapters.recyclers.MyTripsAdapter;
import com.example.dayout.models.profile.ProfileModel;
import com.example.dayout.models.trip.TripModel;
import com.example.dayout.ui.dialogs.ErrorDialog;
import com.example.dayout.ui.dialogs.LoadingDialog;
import com.example.dayout.viewModels.TripViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class UpComingTripFragment extends Fragment {


    MyTripsAdapter adapter;
    View view;

    @BindView(R.id.up_coming_trip_rc)
    RecyclerView upComingTripRc;

    LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_up_coming_trip, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView(){
        loadingDialog = new LoadingDialog(requireContext());
        initRc();
        getDataFromApi();
    }

    private void initRc(){
        upComingTripRc.setHasFixedSize(true);
        upComingTripRc.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new MyTripsAdapter(new ArrayList<>(),requireContext());
        upComingTripRc.setAdapter(adapter);
    }

    private void getDataFromApi(){
        loadingDialog.show();
        TripViewModel.getINSTANCE().getUpcomingTrips();
        TripViewModel.getINSTANCE().upcomingTripsMutableLiveData.observe(requireActivity(), upcomingTripObserver);
    }

    private final Observer<Pair<TripModel, String>> upcomingTripObserver = new Observer<Pair<TripModel, String>>() {
        @Override
        public void onChanged(Pair<TripModel, String> listStringPair) {
            loadingDialog.dismiss();
            if(listStringPair != null){
                if(listStringPair.first != null){
                    adapter.refreshList(listStringPair.first.data,2);
                } else{
                    new ErrorDialog(requireContext(), listStringPair.second).show();
                }
            } else
                new ErrorDialog(requireContext(), "Error Connection").show();
        }
    };

    /*private List<TripModel> filterList(List<TripModel> list, int type) {

        List<TripModel> filteredList = new ArrayList<>();

        if (type == 1) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).state.equals("Old"))
                    filteredList.add(list.get(i));
            }
        } else if (type == 2) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).state.equals("Upcoming"))
                    filteredList.add(list.get(i));
            }
        } else if (type == 3) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).state.equals("Active")) {
                    filteredList.add(list.get(i));
                    break;
                }
            }
        }
        return filteredList;
    }*/

}