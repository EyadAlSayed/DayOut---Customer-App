package com.example.dayout.ui.fragments.drawer.Posts;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dayout.R;
import com.example.dayout.adapters.pager.PostPagerAdapter;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.ui.activities.MainActivity;
import com.example.dayout.ui.fragments.drawer.Posts.tab.PollsFragment;
import com.example.dayout.ui.fragments.drawer.Posts.tab.TripPostFragment;
import com.example.dayout.ui.fragments.trips.myTrip.ActiveTripFragment;
import com.example.dayout.ui.fragments.trips.myTrip.OldTripFragment;
import com.example.dayout.ui.fragments.trips.myTrip.UpComingTripFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dayout.config.AppConstants.TRIP_POST_FRC;

@SuppressLint("NonConstantResourceId")
public class PostsFragment extends Fragment {


    View view;

    @BindView(R.id.back_arrow)
    ImageButton backArrow;

    @BindView(R.id.post_tab_layout)
    TabLayout postTabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_posts, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }


    @Override
    public void onStart() {
        ((MainActivity)requireActivity()).hideBottomBar();
        super.onStart();
    }

    private void initView(){
        backArrow.setOnClickListener(v -> FN.popTopStack(requireActivity()));
        initTabLayout();
    }

    private void initTabLayout() {
        FN.replaceFadeFragment(TRIP_POST_FRC, requireActivity(), new TripPostFragment());
        postTabLayout.addOnTabSelectedListener(onTabSelectedListener);
    }

    private final TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            postTabLayout.setEnabled(false);
            if (tab.getPosition() == 0) {
                FN.replaceFadeFragment(TRIP_POST_FRC, requireActivity(), new TripPostFragment());

            } else if (tab.getPosition() == 1) {
                FN.replaceFadeFragment(TRIP_POST_FRC, requireActivity(), new PollsFragment());

            }

            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                postTabLayout.setEnabled(true);
            }, 40);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

}