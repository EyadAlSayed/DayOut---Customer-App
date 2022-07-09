package com.example.dayout.ui.fragments.drawer.Posts.tab;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dayout.R;
import com.example.dayout.adapters.recyclers.TripPollAdapter;
import com.example.dayout.models.poll.PollsData;
import com.example.dayout.models.poll.PollsPaginationModel;
import com.example.dayout.models.room.pollsRoom.databases.PollsDatabase;
import com.example.dayout.ui.activities.MainActivity;
import com.example.dayout.ui.dialogs.notify.ErrorDialog;
import com.example.dayout.viewModels.TripViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("NonConstantResourceId")
public class PollsFragment extends Fragment {

    View view;

    @BindView(R.id.filter_btn)
    ImageButton filterButton;

    @BindView(R.id.trip_poll_post_rc)
    RecyclerView tripPollPostRc;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.page_loading_pbar)
    ProgressBar loadingBar;

    TripPollAdapter tripPollAdapter;

    int pageNumber;
    boolean canPaginate;

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
        pageNumber = 1;
        filterButton.setVisibility(View.GONE);
        filterButton.setOnClickListener(onFilterClicked);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        initRc();
    }

    private void getDataFromRoom() {
        PollsDatabase.getINSTANCE(requireContext())
                .iPolls()
                .getPolls()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<PollsData>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull List<PollsData> data) {
                        tripPollAdapter.refresh(data);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        Log.e("getter polls roomDB", "onError: " + e.toString());
                    }
                });
    }

    private void getDataFromApi(){
        TripViewModel.getINSTANCE().getPolls(pageNumber);
        TripViewModel.getINSTANCE().pollMutableLiveData.observe(requireActivity(),pollsObserver);
    }

    private final Observer<Pair<PollsPaginationModel,String>> pollsObserver  = new Observer<Pair<PollsPaginationModel, String>>() {
        @Override
        public void onChanged(Pair<PollsPaginationModel, String> pollStringPair) {
            if (pollStringPair != null){
                if (pollStringPair.first != null){
                    tripPollAdapter.addAndRefresh(pollStringPair.first.data.data);
                    canPaginate = (pollStringPair.first.data.next_page_url != null);
                }
                else {
                    getDataFromRoom();
                    new ErrorDialog(requireContext(),pollStringPair.second).show();
                }
            }
            else {
                getDataFromRoom();
                new ErrorDialog(requireContext(),getResources().getString(R.string.error_connection)).show();
            }
            hideLoadingBar();

            swipeRefreshLayout.setRefreshing(false);
            swipeRefreshLayout.setEnabled(true);
        }
    };

    private void initRc(){
        tripPollPostRc.setHasFixedSize(true);
        tripPollPostRc.addOnScrollListener(onScroll);
        tripPollPostRc.setLayoutManager(new LinearLayoutManager(requireContext()));
        tripPollAdapter = new TripPollAdapter(new ArrayList<>(),requireContext());
        tripPollPostRc.setAdapter(tripPollAdapter);
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

    private final RecyclerView.OnScrollListener onScroll = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            if (newState == 1 && canPaginate){    // is scrolling
                pageNumber++;
                showLoadingBar();
                getDataFromApi();
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