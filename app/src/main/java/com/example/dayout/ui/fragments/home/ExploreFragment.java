package com.example.dayout.ui.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;


import androidx.fragment.app.Fragment;
import android.util.Pair;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dayout.R;
import com.example.dayout.adapters.recyclers.ExplorePlaceAdapter;
import com.example.dayout.helpers.view.NoteMessage;
import com.example.dayout.models.SearchPlaceModel;
import com.example.dayout.ui.activities.MainActivity;
import com.example.dayout.ui.dialogs.notify.ErrorDialog;
import com.example.dayout.ui.dialogs.notify.LoadingDialog;
import com.example.dayout.viewModels.PlaceViewModel;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ExploreFragment extends Fragment {


    View view;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.show_result_btn)
    Button showResultButton;
    @BindView(R.id.explore_rc)
    RecyclerView exploreRc;

    ExplorePlaceAdapter explorePlaceAdapter;
    LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_explore, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onStart() {
        ((MainActivity) requireActivity()).hideDrawerButton();
        ((MainActivity) requireActivity()).hideBottomBar();
        super.onStart();
    }

    @Override
    public void onStop() {
        ((MainActivity) requireActivity()).showDrawerButton();
        ((MainActivity) requireActivity()).showBottomBar();
        super.onStop();
    }

    private void initView(){
        loadingDialog = new LoadingDialog(requireContext());
        showResultButton.setOnClickListener(onShowClicked);
        searchView.setOnQueryTextListener(onQueryTextListener);

        initRc();
    }

    private void initRc(){
        exploreRc.setHasFixedSize(true);
        exploreRc.setLayoutManager(new LinearLayoutManager(requireContext()));
        explorePlaceAdapter = new ExplorePlaceAdapter(new ArrayList<>(),requireContext());
        exploreRc.setAdapter(explorePlaceAdapter);
    }


    private final SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            if (checkInfo()){
                searchView.setEnabled(false);
                loadingDialog.show();
                PlaceViewModel.getINSTANCE().searchForPlace(getSearchObj());
                PlaceViewModel.getINSTANCE().searchPlaceMutableLiveData.observe(requireActivity(),searchObserver);
            }
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private final View.OnClickListener onShowClicked  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (checkInfo()){
                searchView.setEnabled(false);
                loadingDialog.show();
                PlaceViewModel.getINSTANCE().searchForPlace(getSearchObj());
                PlaceViewModel.getINSTANCE().searchPlaceMutableLiveData.observe(requireActivity(),searchObserver);
            }
        }
    };

    private final Observer<Pair<SearchPlaceModel,String>> searchObserver = new Observer<Pair<SearchPlaceModel, String>>() {
        @Override
        public void onChanged(Pair<SearchPlaceModel, String> searchPlaceModelStringPair) {
            loadingDialog.dismiss();
            searchView.setEnabled(true);
            if (searchPlaceModelStringPair != null){
                if (searchPlaceModelStringPair.first != null){
                    explorePlaceAdapter.refresh(searchPlaceModelStringPair.first.data.data);
                }else {
                    new ErrorDialog(requireContext(),searchPlaceModelStringPair.second).show();
                }
            }
            else {
                new ErrorDialog(requireContext(),"Connection Error").show();

            }
        }
    };

    private JsonObject getSearchObj(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name",searchView.getQuery().toString());
        return jsonObject;
    }

    private boolean checkInfo(){

        boolean ok = true;
        if (searchView.getQuery().toString().isEmpty()){
            ok = false;
            NoteMessage.showSnackBar(requireActivity(),"Search Text can not be empty");
        }
        if (searchView.getQuery().toString().length() <= 3){
            ok = false;
            NoteMessage.showSnackBar(requireActivity(),"Search text can not be less than three letter");
        }

        return  ok;
    }


}