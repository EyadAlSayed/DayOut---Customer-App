package com.example.dayout.ui.fragments.drawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dayout.R;
import com.example.dayout.adapters.recyclers.TripPostAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TripPostFragment extends Fragment {


    View view;

    @BindView(R.id.filter_btn)
    ImageButton filterBtn;
    @BindView(R.id.trip_post_rc)
    RecyclerView tripPostRc;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    TripPostAdapter tripPostAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_trip_post, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView(){
        filterBtn.setOnClickListener(onFilterClicked);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);

        initRc();
    }

    private void initRc(){
        tripPostRc.setHasFixedSize(true);
        tripPostRc.setLayoutManager(new LinearLayoutManager(requireContext()));
        tripPostAdapter = new TripPostAdapter(new ArrayList<>(),requireContext());
        tripPostRc.setAdapter(tripPostAdapter);
    }

    private final View.OnClickListener onFilterClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private final SwipeRefreshLayout.OnRefreshListener onRefreshListener =new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            swipeRefreshLayout.setRefreshing(false);
        }
    };
}