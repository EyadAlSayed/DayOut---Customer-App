package com.example.dayout.ui.fragments.drawer.Posts;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dayout.R;
import com.example.dayout.adapters.recyclers.TripPollAdapter;
import com.example.dayout.models.poll.PollsModel;
import com.example.dayout.ui.activities.MainActivity;
import com.example.dayout.ui.dialogs.ErrorDialog;
import com.example.dayout.viewModels.TripViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PollsFragment extends Fragment {


    View view;

    @BindView(R.id.filter_btn)
    ImageButton filterButton;
    @BindView(R.id.trip_poll_post_rc)
    RecyclerView tripPollPostRc;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    TripPollAdapter tripPollAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_trip_poll_post, container, false);
        ButterKnife.bind(this, view);
        initView();
        getDataFromApi();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity)requireActivity()).hideBottomBar();
    }

    private void initView(){
        filterButton.setVisibility(View.GONE);
        filterButton.setOnClickListener(onFilterClicked);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        initRc();
    }

    private void getDataFromApi(){
        TripViewModel.getINSTANCE().getPolls();
        TripViewModel.getINSTANCE().pollMutableLiveData.observe(requireActivity(),pollsObserver);
    }

    private final Observer<Pair<PollsModel,String>> pollsObserver  = new Observer<Pair<PollsModel, String>>() {
        @Override
        public void onChanged(Pair<PollsModel, String> pollStringPair) {
            if (pollStringPair != null){
                if (pollStringPair.first != null){
                    tripPollAdapter.refresh(pollStringPair.first.data);
                }
                else {
                    new ErrorDialog(requireContext(),pollStringPair.second).show();
                }
            }
            else {
                new ErrorDialog(requireContext(),"Connection Error").show();
            }

            swipeRefreshLayout.setRefreshing(false);
            swipeRefreshLayout.setEnabled(true);
        }
    };

    private void initRc(){
        tripPollPostRc.setHasFixedSize(true);
        tripPollPostRc.setLayoutManager(new LinearLayoutManager(requireContext()));
        tripPollAdapter = new TripPollAdapter(new ArrayList<>(),requireContext());
        tripPollPostRc.setAdapter(tripPollAdapter);
    }

    private final View.OnClickListener onFilterClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           // FN.addToStackSlideUDFragment(MAIN_FRC, requireActivity(), new FilterPostFragment(tripPostAdapter), "filter_post");
        }
    };

    private final SwipeRefreshLayout.OnRefreshListener onRefreshListener =new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            swipeRefreshLayout.setEnabled(false);
            getDataFromApi();
        }
    };
}