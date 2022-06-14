package com.example.dayout.ui.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dayout.R;
import com.example.dayout.adapters.recyclers.FavoritePlaceAdapter;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.ui.activities.MainActivity;
import com.example.dayout.viewModels.PlaceViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FavoritePlaceFragment extends Fragment {


    View view;
    FavoritePlaceAdapter favoritePlaceAdapter;
    @BindView(R.id.arrow_back)
    ImageButton arrowBack;
    @BindView(R.id.favorite_place_rc)
    RecyclerView favoritePlaceRc;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_favorite_place, container, false);
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

    private void initView() {
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        arrowBack.setOnClickListener(v -> FN.popTopStack(requireActivity()));
    }

    private void getDataFromApi() {
        PlaceViewModel.getINSTANCE().getFavoritePlaces();
    }

    private final SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            swipeRefreshLayout.setEnabled(false);
            getDataFromApi();
        }
    };
}