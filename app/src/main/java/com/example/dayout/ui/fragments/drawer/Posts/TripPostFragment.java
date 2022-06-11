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
import com.example.dayout.adapters.recyclers.TripPostAdapter;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.models.trip.TripPost;
import com.example.dayout.ui.activities.MainActivity;
import com.example.dayout.ui.dialogs.ErrorDialog;
import com.example.dayout.viewModels.TripViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dayout.config.AppConstants.MAIN_FRC;


public class TripPostFragment extends Fragment {


    View view;

    @BindView(R.id.filter_btn)
    ImageButton filterBtn;
    @BindView(R.id.trip_poll_post_rc)
    RecyclerView tripPostRc;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    TripPostAdapter tripPostAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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
        filterBtn.setOnClickListener(onFilterClicked);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        initRc();
    }

    private void getDataFromApi(){
        TripViewModel.getINSTANCE().getTripPost();
        TripViewModel.getINSTANCE().tripPostMutableLiveData.observe(requireActivity(),tripPostObserver);
    }

    private final Observer<Pair<TripPost,String>> tripPostObserver  = new Observer<Pair<TripPost, String>>() {
        @Override
        public void onChanged(Pair<TripPost, String> tripPostStringPair) {
            if (tripPostStringPair != null){
                if (tripPostStringPair.first != null){
                    tripPostAdapter.refresh(tripPostStringPair.first.data.data);
                }
                else {
                    new ErrorDialog(requireContext(),tripPostStringPair.second).show();
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
        tripPostRc.setHasFixedSize(true);
        tripPostRc.setLayoutManager(new LinearLayoutManager(requireContext()));
        tripPostAdapter = new TripPostAdapter(new ArrayList<>(),requireContext());
        tripPostRc.setAdapter(tripPostAdapter);
    }

    private final View.OnClickListener onFilterClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FN.addToStackSlideUDFragment(MAIN_FRC, requireActivity(), new FilterPostFragment(tripPostAdapter), "filter_post");
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