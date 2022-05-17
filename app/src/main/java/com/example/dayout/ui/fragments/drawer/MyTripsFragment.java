package com.example.dayout.ui.fragments.drawer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dayout.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class MyTripsFragment extends Fragment {

    View view;

    @BindView(R.id.my_trips_search_field)
    SearchView myTripsSearchField;

    @Nullable
    @BindView(R.id.tabItem_active)
    TabItem tabItemActive;

    @Nullable
    @BindView(R.id.tabItem_upcoming)
    TabItem tabItemUpcoming;

    @Nullable
    @BindView(R.id.tabItem_history)
    TabItem tabItemHistory;

    @BindView(R.id.my_trips_tab_layout)
    TabLayout myTripsTabLayout;

    @BindView(R.id.my_trips_action_button)
    FloatingActionButton myTripsActionButton;

    @BindView(R.id.my_trips_recycler_view)
    RecyclerView recyclerView;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_trips, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    public MyTripsFragment() {
    }

    private void initViews() {
        initRecycler();
        myTripsTabLayout.addOnTabSelectedListener(tabListener);
    }

    private void initRecycler() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


    }



    private final TabLayout.OnTabSelectedListener tabListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };
}