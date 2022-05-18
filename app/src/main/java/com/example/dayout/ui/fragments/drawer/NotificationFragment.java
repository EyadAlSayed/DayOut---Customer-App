package com.example.dayout.ui.fragments.drawer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dayout.R;
import com.example.dayout.adapters.recyclers.NotificationsAdapter;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.models.NotificationModel;
import com.example.dayout.ui.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class NotificationFragment extends Fragment {

    View view;

    @BindView(R.id.notifications_back_arrow)
    ImageButton notificationsBackArrow;

    @BindView(R.id.notifications_recycler_view)
    RecyclerView notificationsRecyclerView;

    NotificationsAdapter adapter;

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
        initRecycler();
        notificationsBackArrow.setOnClickListener(onBackClicked);
    }

    private void initRecycler(){
        notificationsRecyclerView.setHasFixedSize(true);
        notificationsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new NotificationsAdapter(new ArrayList<>(), requireContext());
        notificationsRecyclerView.setAdapter(adapter);
    }

    private void getDataFromApi(){
        List<NotificationModel> notificationModels = new ArrayList<>();
        NotificationModel n1 = new NotificationModel("n1Title", "n1Description");
        NotificationModel n2 = new NotificationModel("n2Title", " asdf sd fds fsd f sdf f");
        NotificationModel n3 = new NotificationModel("n3Title", "fsaddfsd sd fsdf asd fads fa ");
        NotificationModel n4 = new NotificationModel("n4Title", " sadf dsaf dsa f das fas");
        NotificationModel n5 = new NotificationModel("n5Title", "n1Description");
        NotificationModel n6 = new NotificationModel("n6Title", "n1Description");
        NotificationModel n7 = new NotificationModel("n7Title", " sad fsd f dsf dsfdsfsdf f");
        NotificationModel n8 = new NotificationModel("New Trip to Book!", "Sheez Organizer published a new trip. Check it out!");
        NotificationModel n9 = new NotificationModel("n9Title", "n1Description");
        NotificationModel n10 = new NotificationModel("n10Title", "n1Description");
        NotificationModel n11= new NotificationModel("n11Title", " sdfsdfd sf ds fdsfa");
        NotificationModel n12= new NotificationModel("n12Title", "n1Description");
        NotificationModel n13= new NotificationModel("n13Title", "n1Description");
        NotificationModel n14= new NotificationModel("n14Title", "n1Description");
        NotificationModel n15= new NotificationModel("n15Title", "n1Description");
        NotificationModel n16= new NotificationModel("n16Title", "n1Description");

        notificationModels.add(n1);
        notificationModels.add(n2);
        notificationModels.add(n3);
        notificationModels.add(n4);
        notificationModels.add(n5);
        notificationModels.add(n6);
        notificationModels.add(n7);
        notificationModels.add(n8);
        notificationModels.add(n9);
        notificationModels.add(n10);
        notificationModels.add(n11);
        notificationModels.add(n12);
        notificationModels.add(n13);
        notificationModels.add(n14);
        notificationModels.add(n15);
        notificationModels.add(n16);

        adapter.refreshList(notificationModels);
    }

    private final View.OnClickListener onBackClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FN.popTopStack(requireActivity());
        }
    };
}
