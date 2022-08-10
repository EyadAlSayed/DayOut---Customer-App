package com.example.dayout.ui.fragments.drawer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dayout.R;
import com.example.dayout.adapters.recyclers.NotificationsAdapter;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.models.notification.NotificationData;
import com.example.dayout.models.notification.NotificationModel;
import com.example.dayout.models.poll.PollsData;
import com.example.dayout.models.room.notificationsRoom.database.NotificationsDatabase;
import com.example.dayout.models.room.pollsRoom.databases.PollsDatabase;
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
public class NotificationFragment extends Fragment {

    View view;

    @BindView(R.id.notifications_back_arrow)
    ImageButton notificationsBackArrow;

    @BindView(R.id.notifications_recycler_view)
    RecyclerView notificationsRecyclerView;

    @BindView(R.id.no_notifications_TV)
    TextView noNotifications;

    NotificationsAdapter adapter;

    LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this, view);
        initViews();
        getDataFromApi();
        return view;
    }

    @Override
    public void onStart() {
        ((MainActivity)requireActivity()).hideBottomBar();
        super.onStart();
    }

    private void initViews(){
        loadingDialog = new LoadingDialog(requireContext());
        initRecycler();
        notificationsBackArrow.setOnClickListener(onBackClicked);
    }

    private void initRecycler(){
        notificationsRecyclerView.setHasFixedSize(true);
        notificationsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new NotificationsAdapter(new ArrayList<>(), requireContext());
        notificationsRecyclerView.setAdapter(adapter);
    }

    private void getDataFromRoom(){
        NotificationsDatabase.getINSTANCE(requireContext())
                .iNotifications()
                .getNotifications()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<NotificationData>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull List<NotificationData> data) {
                        adapter.refreshList(data);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        Log.e("getter notifications roomDB", "onError: " + e.toString());
                    }
                });
    }

    private void getDataFromApi(){
        loadingDialog.show();
        UserViewModel.getINSTANCE().getNotifications();
        UserViewModel.getINSTANCE().notificationMutableLiveData.observe(requireActivity(), notificationsObserver);
    }

    private final Observer<Pair<NotificationModel, String>> notificationsObserver = new Observer<Pair<NotificationModel, String>>() {
        @Override
        public void onChanged(Pair<NotificationModel, String> notificationModelStringPair) {
            loadingDialog.dismiss();
            if (notificationModelStringPair != null) {
                if (notificationModelStringPair.first != null) {
                    if (!notificationModelStringPair.first.data.isEmpty()) {
                        noNotifications.setVisibility(View.GONE);
                        notificationsRecyclerView.setVisibility(View.VISIBLE);
                        adapter.refreshList(notificationModelStringPair.first.data);
                    } else {
                        noNotifications.setVisibility(View.VISIBLE);
                        notificationsRecyclerView.setVisibility(View.GONE);
                    }
                } else {
                    getDataFromRoom();
                    new ErrorDialog(requireContext(), notificationModelStringPair.second).show();
                }
            } else {
                getDataFromRoom();
                new ErrorDialog(requireContext(), getResources().getString(R.string.error_connection));
            }
        }
    };

    private final View.OnClickListener onBackClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FN.popTopStack(requireActivity());
        }
    };
}
