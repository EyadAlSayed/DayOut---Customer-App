package com.example.dayout.ui.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dayout.R;
import com.example.dayout.adapters.recyclers.ExplorePlaceAdapter;
import com.example.dayout.ui.activities.MainActivity;

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
        showResultButton.setOnClickListener(onShowClicked);
        initRc();
    }

    private void initRc(){
        exploreRc.setHasFixedSize(true);
        exploreRc.setLayoutManager(new LinearLayoutManager(requireContext()));
        explorePlaceAdapter = new ExplorePlaceAdapter(new ArrayList<>(),requireContext());
        exploreRc.setAdapter(explorePlaceAdapter);
    }

    private final View.OnClickListener onShowClicked  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}