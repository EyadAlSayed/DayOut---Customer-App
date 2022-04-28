package com.example.dayout.ui.activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.dayout.R;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.ui.fragments.auth.AuthFragment;
import com.example.dayout.ui.fragments.drawer.DrawerFragment;
import com.example.dayout.ui.fragments.home.ExploreFragment;
import com.example.dayout.ui.fragments.home.FavoritePlaceFragment;
import com.example.dayout.ui.fragments.home.HomeFragment;
import com.example.dayout.ui.fragments.profile.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dayout.config.AppConstants.AUTH_FRC;
import static com.example.dayout.config.AppConstants.MAIN_FRC;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.explore_btn)
    ImageButton exploreButton;
    @BindView(R.id.favorite_place_btn)
    ImageButton favoritePlaceButton;
    @BindView(R.id.drawer_btn)
    ImageButton drawerButton;
    @BindView(R.id.profile_btn)
    ImageButton profileButton;
    @BindView(R.id.bottom_bar)
    CardView bottomBar;

    private boolean isDrawerOpen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        FN.addFixedNameFadeFragment(MAIN_FRC, this, new HomeFragment());
    }

    private void initView() {
        exploreButton.setOnClickListener(onExploreClicked);
        favoritePlaceButton.setOnClickListener(onFavoriteClicked);
        drawerButton.setOnClickListener(onDrawerClicked);
        profileButton.setOnClickListener(onProfileClicked);
    }

    private final View.OnClickListener onExploreClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FN.addFixedNameFadeFragment(MAIN_FRC, MainActivity.this, new ExploreFragment());
        }
    };

    private final View.OnClickListener onFavoriteClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FN.addFixedNameFadeFragment(MAIN_FRC, MainActivity.this, new FavoritePlaceFragment());
        }
    };
    private final View.OnClickListener onDrawerClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hideBottomBar();
            FN.addSlideLRFragmentUpFragment(MAIN_FRC, MainActivity.this, new DrawerFragment(), "drawer");
            isDrawerOpen = !isDrawerOpen;
        }
    };
    private final View.OnClickListener onProfileClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FN.addFixedNameFadeFragment(MAIN_FRC, MainActivity.this, new ProfileFragment());
        }
    };

    public void showBottomBar() {
        drawerButton.setVisibility(View.VISIBLE);
        bottomBar.setVisibility(View.VISIBLE);
        drawerButton.animate().setDuration(400).alpha(1);
        bottomBar.animate().setDuration(400).alpha(1);
    }

    private void hideBottomBar() {
        drawerButton.animate().setDuration(400).alpha(0);
        bottomBar.animate().setDuration(400).alpha(0);
        new Handler(getMainLooper()).postDelayed(() -> {
            drawerButton.setVisibility(View.GONE);
            bottomBar.setVisibility(View.GONE);
        },450);

    }


}
