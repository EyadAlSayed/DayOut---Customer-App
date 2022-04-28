package com.example.dayout.ui.fragments.profile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.dayout.R;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.ui.fragments.drawer.DrawerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.os.Looper.getMainLooper;
import static com.example.dayout.config.AppConstants.MAIN_FRC;

public class ProfileFragment extends Fragment {

    View view;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.profile_drawer_button)
    ImageButton profileDrawerButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.profile_edit_button)
    ImageButton profileEditButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.profile_image)
    CircleImageView profileImage;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.profile_followers_count)
    TextView profileFollowersCount;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.profile_trips_count)
    TextView profileTripsCount;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.profile_gender)
    TextView profileGender;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.profile_phone_number)
    TextView profilePhoneNumber;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.profile_email)
    TextView profileEmail;

    private boolean isDrawerOpen = false;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private final View.OnClickListener onDrawerButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openDrawer();
            FN.addSlideLRFragmentUpFragment(MAIN_FRC, requireActivity(), new DrawerFragment(), "drawer");
            isDrawerOpen = !isDrawerOpen;
        }
    };

    private final View.OnClickListener onEditProfileClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private void initViews(){
        profileDrawerButton.setOnClickListener(onDrawerButtonClicked);
        profileEditButton.setOnClickListener(onEditProfileClicked);
    }

    //FIXME: When the drawer opens, the layout lags down a bit.
    //FIXME: When the drawer opens, bottom buttons are still shown.
    private void openDrawer(){
        profileDrawerButton.animate().setDuration(400).alpha(0);
        new Handler(getMainLooper()).postDelayed(() -> {
            profileDrawerButton.setVisibility(View.GONE);
        },450);
    }
}
