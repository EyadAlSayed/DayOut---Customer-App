package com.example.dayout.ui.fragments.drawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.dayout.R;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.ui.activities.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DrawerFragment extends Fragment {


    View view;
    @BindView(R.id.drawer_close_btn)
    ImageButton drawerCloseButton;
    @BindView(R.id.my_trip_txt)
    TextView myTripTxt;
    @BindView(R.id.org_txt)
    TextView orgTxt;
    @BindView(R.id.post_txt)
    TextView postTxt;
    @BindView(R.id.notification_txt)
    TextView notificationTxt;
    @BindView(R.id.connect_us_txt)
    TextView connectUsTxt;
    @BindView(R.id.setting_txt)
    TextView settingTxt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_drawer, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        drawerCloseButton.setOnClickListener(onCloseClicked);

    }

    private final View.OnClickListener onCloseClicked = v -> {
        FN.popStack(requireActivity());
        ((MainActivity) requireActivity()).showBottomBar();
    };
}