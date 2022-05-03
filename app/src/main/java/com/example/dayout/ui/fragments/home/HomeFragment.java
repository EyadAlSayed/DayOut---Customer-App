package com.example.dayout.ui.fragments.home;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dayout.R;
import com.example.dayout.adapters.recyclers.HomePlaceAdapter;
import com.example.dayout.models.PopularPlace;
import com.example.dayout.ui.dialogs.ErrorDialog;
import com.example.dayout.ui.dialogs.LoadingDialog;
import com.example.dayout.viewModels.PlaceViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment {


    View view;
    @BindView(R.id.home_place_rc)
    RecyclerView homePlaceRc;

    HomePlaceAdapter homePlaceAdapter;
    LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initView();
        getDataFromApi();

        return view;
    }

    private void initView() {
        initRc();
    }

    private void initRc(){

        homePlaceRc.setHasFixedSize(true);
        homePlaceRc.setLayoutManager(new LinearLayoutManager(requireContext()));
        homePlaceAdapter = new HomePlaceAdapter(new ArrayList<>(),requireContext());
        homePlaceRc.setAdapter(homePlaceAdapter);
    }

    private void getDataFromApi(){
        PlaceViewModel.getINSTANCE().getPopularPlace();
        PlaceViewModel.getINSTANCE().popularMutableLiveData.observe(requireActivity(),popularPlaceObserver);
    }

    private final Observer<Pair<PopularPlace,String>> popularPlaceObserver =  new Observer<Pair<PopularPlace, String>>() {
        @Override
        public void onChanged(Pair<PopularPlace, String> popularPlaceStringPair) {
            if (popularPlaceStringPair != null){
                if (popularPlaceStringPair.first != null){
                    homePlaceAdapter.refreshList(popularPlaceStringPair.first.data);
                }
                else new ErrorDialog(requireContext(),popularPlaceStringPair.second).show();
            }
            else new ErrorDialog(requireContext(),"connection error").show();
        }
    };
}