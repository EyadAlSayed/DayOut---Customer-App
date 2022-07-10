package com.example.dayout.ui.fragments.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;


import androidx.annotation.NonNull;
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

@SuppressLint("NonConstantResourceId")
public class ExploreFragment extends Fragment {


    View view;

    @BindView(R.id.searchView)
    SearchView searchView;

    @BindView(R.id.show_result_btn)
    Button showResultButton;

    @BindView(R.id.explore_rc)
    RecyclerView exploreRc;

    @BindView(R.id.explore_loading_bar)
    ProgressBar loadingBar;

    ExplorePlaceAdapter explorePlaceAdapter;
    LoadingDialog loadingDialog;

    //pagination
    int pageNumber;
    boolean canPaginate;
    JsonObject tmpObject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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
        pageNumber = 1;
        loadingDialog = new LoadingDialog(requireContext());
        tmpObject = new JsonObject();
        showResultButton.setOnClickListener(onShowClicked);
        searchView.setOnQueryTextListener(onQueryTextListener);

        initRc();
    }

    private void initRc(){
        exploreRc.setHasFixedSize(true);
        exploreRc.addOnScrollListener(onScroll);
        exploreRc.setLayoutManager(new LinearLayoutManager(requireContext()));
        explorePlaceAdapter = new ExplorePlaceAdapter(new ArrayList<>(),requireContext());
        exploreRc.setAdapter(explorePlaceAdapter);
    }

    private void getDataFromAPI(){
        PlaceViewModel.getINSTANCE().searchForPlace(tmpObject, pageNumber);
        PlaceViewModel.getINSTANCE().searchPlaceMutableLiveData.observe(requireActivity(),searchObserver);
    }

    private final SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            if (checkInfo()){
                searchView.setEnabled(false);
                loadingDialog.show();
                PlaceViewModel.getINSTANCE().searchForPlace(getSearchObj(), 1);
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
                PlaceViewModel.getINSTANCE().searchForPlace(getSearchObj(), 1);
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
                    explorePlaceAdapter.addAndRefresh(searchPlaceModelStringPair.first.data.data);
                    canPaginate = (searchPlaceModelStringPair.first.data.next_page_url != null);
                }else {
                    new ErrorDialog(requireContext(),searchPlaceModelStringPair.second).show();
                }
            }
            else {
                new ErrorDialog(requireContext(),getResources().getString(R.string.error_connection)).show();
            }

            hideLoadingBar();
        }
    };

    private JsonObject getSearchObj(){
        tmpObject.addProperty("name",searchView.getQuery().toString());

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name",searchView.getQuery().toString());
        return jsonObject;
    }

    private void  hideLoadingBar(){
        if (loadingBar.getVisibility() == View.GONE) return;

        loadingBar.animate().setDuration(400).alpha(0) ;
        new Handler(Looper.getMainLooper()).postDelayed(() -> loadingBar.setVisibility(View.GONE),450);
    }

    private void showLoadingBar(){
        if (loadingBar.getVisibility() == View.VISIBLE) return;

        loadingBar.setAlpha(1);
        loadingBar.setVisibility(View.VISIBLE);
    }

    private boolean checkInfo(){

        boolean ok = true;
        if (searchView.getQuery().toString().isEmpty()){
            ok = false;
            NoteMessage.showSnackBar(requireActivity(),getResources().getString(R.string.empty_search_field));
        }
        if (searchView.getQuery().toString().length() < 3){
            ok = false;
            NoteMessage.showSnackBar(requireActivity(),getResources().getString(R.string.at_least_three));
        }

        return  ok;
    }

    private final RecyclerView.OnScrollListener onScroll = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            if (newState == 1 && canPaginate){    // is scrolling
                pageNumber++;
                showLoadingBar();
                getDataFromAPI();
                canPaginate = false;
            }

            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

            super.onScrolled(recyclerView, dx, dy);
        }
    };

}