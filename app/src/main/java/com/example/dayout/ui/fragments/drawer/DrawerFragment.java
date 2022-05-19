package com.example.dayout.ui.fragments.drawer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.dayout.R;
import com.example.dayout.config.AppSharedPreferences;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.ui.activities.AuthActivity;
import com.example.dayout.ui.activities.MainActivity;
import com.example.dayout.ui.dialogs.LogOutDialog;
import com.example.dayout.ui.fragments.drawer.Posts.PostsFragment;
import com.example.dayout.ui.fragments.trips.myTrip.MyTripsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

import static com.example.dayout.config.AppConstants.MAIN_FRC;

@SuppressLint("NonConstantResourceId")
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

    @BindView(R.id.logout_txt)
    TextView logoutTxt;

    @BindView(R.id.setting_txt)
    TextView settingTxt;

    @BindView(R.id.blur_view)
    BlurView blurView;

    LogOutDialog logOutDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_drawer, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onStart() {
        ((MainActivity) requireActivity()).hideDrawerButton();
        ((MainActivity) requireActivity()).hideBottomBar();
        new Handler(Looper.getMainLooper()).postDelayed(this::initBlur, 1000);
        super.onStart();
    }

    @Override
    public void onStop() {
        ((MainActivity) requireActivity()).showDrawerButton();
        ((MainActivity) requireActivity()).showBottomBar();
        super.onStop();
    }

    private void initView() {

        logOutDialog = new LogOutDialog(requireContext());
        drawerCloseButton.setOnClickListener(onCloseClicked);
        notificationTxt.setOnClickListener(onNotificationClicked);
        myTripTxt.setOnClickListener(onMyTripsClicked);
        postTxt.setOnClickListener(onPostClicked);
        logoutTxt.setOnClickListener(onLogOutClicked);
        settingTxt.setOnClickListener(onSettingClicked);
    }

    private void initBlur() {
        float radius = 20f;

        View decorView = requireActivity().getWindow().getDecorView();
        ViewGroup rootView = decorView.findViewById(android.R.id.content);
        Drawable windowBackground = decorView.getBackground();

        blurView.setupWith(rootView)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new RenderScriptBlur(requireContext()))
                .setBlurRadius(radius)
                .setBlurAutoUpdate(true);

    }


    private final View.OnClickListener onCloseClicked = v -> {
        blurView.setupWith(new ViewGroup(requireContext()) {
            @Override
            protected void onLayout(boolean changed, int l, int t, int r, int b) {
                // this for removing blur view group before deAttach the fragment
            }
        });
        new Handler(Looper.getMainLooper()).postDelayed(() -> FN.popTopStack(requireActivity()), 200);
    };

    private final View.OnClickListener onNotificationClicked = v -> {

    };

    private final View.OnClickListener onPostClicked = v -> {
        FN.popTopStack(requireActivity());
        FN.addFixedNameFadeFragment(MAIN_FRC, requireActivity(), new PostsFragment());
    };
    private final View.OnClickListener onMyTripsClicked = view -> {
        FN.popTopStack(requireActivity());
        FN.addFixedNameFadeFragment(MAIN_FRC, requireActivity(), new MyTripsFragment());
    };

    private final View.OnClickListener onSettingClicked = v ->{
        FN.popTopStack(requireActivity());
        FN.addFixedNameFadeFragment(MAIN_FRC, requireActivity(), new SettingsFragment());
    };

    private final View.OnClickListener onLogOutClicked  = v -> {
       logOutDialog.show();
    };


}