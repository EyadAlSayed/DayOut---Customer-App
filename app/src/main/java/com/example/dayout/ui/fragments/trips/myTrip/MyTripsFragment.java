package com.example.dayout.ui.fragments.trips.myTrip;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.dayout.R;
import com.example.dayout.adapters.recyclers.pager.MyTripPagerAdapter;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.ui.activities.MainActivity;
import com.example.dayout.ui.fragments.trips.FilterFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;


import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dayout.config.AppConstants.MAIN_FRC;

@SuppressLint("NonConstantResourceId")
public class MyTripsFragment extends Fragment {

    View view;

    @BindView(R.id.my_trips_tab_layout)
    TabLayout myTripsTabLayout;

    @BindView(R.id.my_trips_action_button)
    FloatingActionButton myTripsActionButton;

    @BindView(R.id.my_trips_back_arrow)
    ImageButton myTripsBackArrow;

    @BindView(R.id.my_trips_filter)
    ImageButton myTripsFilter;

    @BindView(R.id.my_trips_view_pager)
    ViewPager myTripsViewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_trips, container, false);
        ButterKnife.bind(this, view);
        myTripsTabLayout = view.findViewById(R.id.my_trips_tab_layout);
        initViews();
        return view;
    }

    @Override
    public void onStart() {
        ((MainActivity) requireActivity()).hideBottomBar();
        super.onStart();
    }

    public MyTripsFragment() {

    }

    private void initTabLayout() {
        MyTripPagerAdapter adapter = new MyTripPagerAdapter(requireActivity().getSupportFragmentManager());
        adapter.addFragment(new ActiveTripFragment(), "ACTIVE");
        adapter.addFragment(new UpComingTripFragment(), "UPCOMING");
        adapter.addFragment(new OldTripFragment(), "HISTORY");
        myTripsViewPager.setAdapter(adapter);
        myTripsTabLayout.setupWithViewPager(myTripsViewPager);
    }

    private void initViews() {
        initTabLayout();
        myTripsActionButton.setOnClickListener(onCreateTripClicked);
        myTripsBackArrow.setOnClickListener(onBackClicked);
        myTripsFilter.setOnClickListener(onFilterClicked);
    }



    private final View.OnClickListener onCreateTripClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO: Go to create trip fragment. - Caesar.
        }
    };

    private final View.OnClickListener onBackClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FN.popStack(requireActivity());
        }
    };

    private final View.OnClickListener onFilterClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FilterFragment.isFilterOpen = true;
            FN.addToStackSlideUDFragment(MAIN_FRC, requireActivity(), new FilterFragment(), "filter");
        }
    };
}