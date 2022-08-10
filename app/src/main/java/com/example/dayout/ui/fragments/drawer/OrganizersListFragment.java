package com.example.dayout.ui.fragments.drawer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dayout.R;
import com.example.dayout.adapters.recyclers.OrganizersAdapter;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.models.notification.NotificationData;
import com.example.dayout.models.profile.ProfileData;
import com.example.dayout.models.profile.organizer.OrganizersModel;
import com.example.dayout.models.room.notificationsRoom.database.NotificationsDatabase;
import com.example.dayout.models.room.organizersRoom.database.OrganizersDatabase;
import com.example.dayout.ui.activities.MainActivity;
import com.example.dayout.ui.dialogs.notify.ErrorDialog;
import com.example.dayout.ui.dialogs.notify.LoadingDialog;
import com.example.dayout.viewModels.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("NonConstantResourceId")
public class OrganizersListFragment extends Fragment {

    View view;

    @BindView(R.id.organizers_back_button)
    ImageButton backButton;

    @BindView(R.id.organizers_search_field)
    EditText searchField;

    @BindView(R.id.organizers_recycler)
    RecyclerView organizersRecycler;

    @BindView(R.id.organizers_loading_bar)
    ProgressBar loadingBar;

    LoadingDialog loadingDialog;

    OrganizersAdapter adapter;

    List<ProfileData> mainList;
    List<ProfileData> filteredList;

    boolean followingOnly;

    //pagination
    int pageNumber;
    boolean canPaginate;

    public OrganizersListFragment(boolean followingOnly) {
        this.followingOnly = followingOnly;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_organizers_list, container, false);
        ButterKnife.bind(this, view);
        initView();
        getDataFromAPI();
        return view;
    }

    @Override
    public void onStart() {
        ((MainActivity) requireActivity()).hideBottomBar();
        super.onStart();
    }

    private void initView() {
        pageNumber = 1;
        loadingDialog = new LoadingDialog(requireContext());
        mainList = new ArrayList<>();
        filteredList = new ArrayList<>();
        initRecycler();
        backButton.setOnClickListener(onBackClicked);
        searchField.addTextChangedListener(textWatcher);
    }

    private void initRecycler() {
        organizersRecycler.setHasFixedSize(true);
        organizersRecycler.addOnScrollListener(onScroll);
        organizersRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new OrganizersAdapter(new ArrayList<>(), requireContext());
        organizersRecycler.setAdapter(adapter);
    }

    private void getOrganizersFromRoom(){
        OrganizersDatabase.getINSTANCE(requireContext())
                .iOrganizers()
                .getOrganizers()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ProfileData>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull List<ProfileData> data) {
                        adapter.refreshList(data);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        Log.e("getter notifications roomDB", "onError: " + e.toString());
                    }
                });
    }

    private void getFollowedOrganizersFromRoom(){
        OrganizersDatabase.getINSTANCE(requireContext())
                .iOrganizers()
                .getFollowedOrganizers()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ProfileData>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull List<ProfileData> data) {
                        adapter.refreshList(data);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        Log.e("getter notifications roomDB", "onError: " + e.toString());
                    }
                });
    }

    private void getDataFromAPI() {
        loadingDialog.show();
        if (followingOnly) {
            UserViewModel.getINSTANCE().getAllFollowedOrganizers(pageNumber);
        } else {
            UserViewModel.getINSTANCE().getAllOrganizers(pageNumber);
        }
        UserViewModel.getINSTANCE().organizersMutableLiveData.observe(requireActivity(), organizersObserver);
    }

    private final Observer<Pair<OrganizersModel, String>> organizersObserver = new Observer<Pair<OrganizersModel, String>>() {
        @Override
        public void onChanged(Pair<OrganizersModel, String> organizersModelStringPair) {
            loadingDialog.dismiss();
            if (organizersModelStringPair != null) {
                if (organizersModelStringPair.first != null) {
                    mainList = organizersModelStringPair.first.data.data;
                    adapter.addAndRefresh(organizersModelStringPair.first.data.data);
                    canPaginate = (organizersModelStringPair.first.data.next_page_url != null);
                } else {
                    if(followingOnly)
                        getFollowedOrganizersFromRoom();
                    else
                        getOrganizersFromRoom();
                    new ErrorDialog(requireContext(), organizersModelStringPair.second).show();
                }
            }else {
                if(followingOnly)
                    getFollowedOrganizersFromRoom();
                else
                    getOrganizersFromRoom();
                new ErrorDialog(requireContext(), getResources().getString(R.string.error_connection)).show();
            }
            hideLoadingBar();
        }
    };

    private void filter(String organizerName) {
        filteredList.clear();

        if (organizerName.isEmpty()) {
            adapter.refreshList(mainList);
            return;
        }

        for (ProfileData organizer : mainList) {
            String name = organizer.user.first_name + " " + organizer.user.last_name;
            if (name.toLowerCase().contains(organizerName.toLowerCase())) {
                filteredList.add(organizer);
            }
        }

        adapter.refreshList(filteredList);
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

    private final View.OnClickListener onBackClicked = v -> FN.popStack(requireActivity());

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            filter(s.toString().toLowerCase());
        }
    };

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