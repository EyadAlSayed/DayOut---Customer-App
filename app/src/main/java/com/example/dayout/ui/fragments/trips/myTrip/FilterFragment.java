package com.example.dayout.ui.fragments.trips.myTrip;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.dayout.R;
import com.example.dayout.adapters.recyclers.myTrips.ActiveTripAdapter;
import com.example.dayout.adapters.recyclers.myTrips.OldTripAdapter;
import com.example.dayout.adapters.recyclers.myTrips.UpComingTripAdapter;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.models.trip.TripData;
import com.example.dayout.models.trip.TripListModel;
import com.example.dayout.models.trip.place.PlaceTripData;
import com.example.dayout.models.trip.tripType.TripType;
import com.example.dayout.models.trip.tripType.TripTypeModel;
import com.example.dayout.ui.dialogs.notify.ErrorDialog;
import com.example.dayout.ui.dialogs.notify.LoadingDialog;
import com.example.dayout.viewModels.TripViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class FilterFragment extends Fragment {

    View view;

    @BindView(R.id.filter_title)
    EditText filterTitle;

    @BindView(R.id.filter_min_price)
    EditText filterMinPrice;

    @BindView(R.id.filter_max_price)
    EditText filterMaxPrice;

    @BindView(R.id.filter_spinner)
    Spinner filterSpinner;

    @BindView(R.id.filter_button)
    Button filterButton;

    @BindView(R.id.textView2)
    TextView placeTV;

    @BindView(R.id.filter_place_name)
    EditText placeName;

    LoadingDialog loadingDialog;

    public static boolean isFilterOpen = false;

    ActiveTripAdapter activeTripAdapter;
    UpComingTripAdapter upComingTripAdapter;
    OldTripAdapter oldTripAdapter;

    int filterType;

    public FilterFragment(ActiveTripAdapter activeTripAdapter, int type) {
        this.activeTripAdapter = activeTripAdapter;
        this.filterType = type;
    }

    public FilterFragment(UpComingTripAdapter upComingTripAdapter, int type) {
        this.upComingTripAdapter = upComingTripAdapter;
        this.filterType = type;
    }

    public FilterFragment(OldTripAdapter oldTripAdapter, int type) {
        this.oldTripAdapter = oldTripAdapter;
        this.filterType = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.filter_fragment, container, false);
        ButterKnife.bind(this, view);
        initViews();
        getDataFromAPI();
        return view;
    }

    private void initViews() {
        loadingDialog = new LoadingDialog(requireContext());
        filterButton.setOnClickListener(onFilterClicked);
    }

    private void getDataFromAPI() {
        TripViewModel.getINSTANCE().getTripType();
        TripViewModel.getINSTANCE().tripTypeTripMutableLiveData.observe(requireActivity(), tripTypeObserver);
    }

    private final Observer<Pair<TripTypeModel, String>> tripTypeObserver = typeStringPair -> {
        if (typeStringPair != null) {
            if (typeStringPair.first != null) {
                initSpinner(getDataName(typeStringPair.first.data));
            }
        }
    };

    private void initSpinner(String[] spinnerItem) {
        ArrayAdapter<String> typesAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                spinnerItem);

        filterSpinner.setAdapter(typesAdapter);
    }

    private String[] getDataName(List<TripType> list) {
        List<String> names = new ArrayList<>();
        names.add("Any");
        for (TripType t : list) {
            names.add(t.name);
        }

        return names.toArray(new String[0]);
    }

    private final View.OnClickListener onFilterClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loadingDialog.show();

            showFilteredTrips();
            FN.popStack(requireActivity());
            isFilterOpen = false;
        }
    };

    private void showFilteredTrips() {
        if (filterType == 1) {
            TripViewModel.getINSTANCE().getHistoryTrips();
            TripViewModel.getINSTANCE().historyTripsMutableLiveData.observe(requireActivity(), new Observer<Pair<TripListModel, String>>() {
                @Override
                public void onChanged(Pair<TripListModel, String> tripModelStringPair) {
                    loadingDialog.dismiss();
                    if (tripModelStringPair != null) {
                        if (tripModelStringPair.first != null) {
                            oldTripAdapter.refresh(tripModelStringPair.first.data);
                        } else
                            new ErrorDialog(requireContext(), tripModelStringPair.second).show();
                    } else
                        new ErrorDialog(requireContext(), "Error Connection").show();
                }
            });
        } else if (filterType == 2) {
            TripViewModel.getINSTANCE().getUpcomingTrips();
            TripViewModel.getINSTANCE().upcomingTripsMutableLiveData.observe(requireActivity(), new Observer<Pair<TripListModel, String>>() {
                @Override
                public void onChanged(Pair<TripListModel, String> tripModelStringPair) {
                    loadingDialog.dismiss();
                    if (tripModelStringPair != null) {
                        if (tripModelStringPair.first != null) {
                            upComingTripAdapter.refresh(tripModelStringPair.first.data);
                        } else
                            new ErrorDialog(requireContext(), tripModelStringPair.second).show();
                    } else
                        new ErrorDialog(requireContext(), "Error Connection").show();
                }
            });
        } else if (filterType == 3) {
            TripViewModel.getINSTANCE().getActiveTrips();
            TripViewModel.getINSTANCE().activeTripsMutableLiveData.observe(requireActivity(), new Observer<Pair<TripListModel, String>>() {
                @Override
                public void onChanged(Pair<TripListModel, String> tripModelStringPair) {
                    loadingDialog.dismiss();
                    if (tripModelStringPair != null) {
                        if (tripModelStringPair.first != null) {
                            activeTripAdapter.refresh(filterList(tripModelStringPair.first.data));
                        } else
                            new ErrorDialog(requireContext(), tripModelStringPair.second).show();
                    } else
                        new ErrorDialog(requireContext(), "Error Connection").show();
                }
            });
        }
    }

    private ArrayList<TripData> filterList(ArrayList<TripData> list) {

        list = filterListOnPlace(list);
        list = filterListOnTitle(list);
        list = filterListOnMinPrice(list);
        list = filterListOnMaxPrice(list);
        list = filterListOnType(list);

        return list;
    }

    private ArrayList<TripData> filterListOnPlace(ArrayList<TripData> list){
        if(!placeName.getText().toString().equals("")){
            ArrayList<TripData> filteredTrips = new ArrayList<>();

            for(TripData trip : list){
                for(PlaceTripData place : trip.place_trips){
                    if(place.place.name.toLowerCase().contains(placeName.getText().toString().toLowerCase())){
                        filteredTrips.add(trip);
                    }
                }
            }
            return filteredTrips;
        } else
            return list;
    }

    private ArrayList<TripData> filterListOnTitle(ArrayList<TripData> list) {
        if (!filterTitle.getText().toString().equals("")) {

            ArrayList<TripData> filteredTrips = new ArrayList<>();

            for (TripData trip : list) {
                if (trip.title.toLowerCase().contains(filterTitle.getText().toString().toLowerCase())) {
                    filteredTrips.add(trip);
                }
            }
            return filteredTrips;
        } else
            return list;
    }

    private ArrayList<TripData> filterListOnMinPrice(ArrayList<TripData> list) {
        if (!filterMinPrice.getText().toString().equals("")) {
            ArrayList<TripData> filteredTrips = new ArrayList<>();

            if (Integer.parseInt(filterMinPrice.getText().toString()) > 0) {
                for (TripData trip : list) {
                    if (trip.price >= Integer.parseInt(filterMinPrice.getText().toString())) {
                        filteredTrips.add(trip);
                    }
                }
            }
            return filteredTrips;
        } else
            return list;
    }

    private ArrayList<TripData> filterListOnMaxPrice(ArrayList<TripData> list) {
        if (!filterMaxPrice.getText().toString().equals("")) {
            ArrayList<TripData> filteredTrips = new ArrayList<>();

            if (Integer.parseInt(filterMaxPrice.getText().toString()) > 0) {
                for (TripData trip : list) {
                    if (trip.price <= Integer.parseInt(filterMaxPrice.getText().toString())) {
                        filteredTrips.add(trip);
                    }
                }
            }
            return filteredTrips;
        } else
            return list;
    }

    private ArrayList<TripData> filterListOnType(ArrayList<TripData> list) {
        if (!filterSpinner.getSelectedItem().toString().equals("Any")) {
            ArrayList<TripData> filteredTrips = new ArrayList<>();

            for (TripData trip : list) {
                for (TripType tripType : trip.types) {
                    if (tripType.name.equals(filterSpinner.getSelectedItem().toString())) {
                        filteredTrips.add(trip);
                    }
                }
            }
            return filteredTrips;
        } else
            return list;
    }
}