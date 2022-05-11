package com.example.dayout.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.dayout.R;
import com.example.dayout.helpers.view.FN;

import com.example.dayout.ui.fragments.drawer.DrawerFragment;
import com.example.dayout.ui.fragments.home.ExploreFragment;
import com.example.dayout.ui.fragments.home.FavoritePlaceFragment;
import com.example.dayout.ui.fragments.home.HomeFragment;
import com.example.dayout.ui.fragments.profile.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dayout.config.AppConstants.AUTH_FRC;
import static com.example.dayout.config.AppConstants.MAIN_FRC;
import static com.example.dayout.config.AppSharedPreferences.GET_ACC_TOKEN;
import static com.example.dayout.config.AppSharedPreferences.InitSharedPreferences;

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

   // public IPopularPlaces roomPopularPlaces;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        InitSharedPreferences(this);
        initView();
        initRoomDB();
        FN.addFixedNameFadeFragment(MAIN_FRC, this, new HomeFragment());

        Log.d("ACC_TOKEN", "onCreate: "+GET_ACC_TOKEN() );
    }


    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_fr_c);
        if (currentFragment instanceof HomeFragment) finish();
        else super.onBackPressed();
    }

    private void initRoomDB() {
    //    roomPopularPlaces = PopularPlaceDataBase.getINSTANCE(this).iPopularPlaces();
    }

    private void initView() {
        exploreButton.setOnClickListener(onExploreClicked);
        favoritePlaceButton.setOnClickListener(onFavoriteClicked);
        drawerButton.setOnClickListener(onDrawerClicked);
        profileButton.setOnClickListener(onProfileClicked);
    }

    private final View.OnClickListener onExploreClicked = v -> FN.addFixedNameFadeFragment(MAIN_FRC, MainActivity.this, new ExploreFragment());

    private final View.OnClickListener onFavoriteClicked = v -> FN.addFixedNameFadeFragment(MAIN_FRC, MainActivity.this, new FavoritePlaceFragment());
    private final View.OnClickListener onDrawerClicked = v -> {
        FN.addSlideLRFragmentUpFragment(MAIN_FRC, MainActivity.this, new DrawerFragment(), "drawer");
        isDrawerOpen = !isDrawerOpen;
    };
    private final View.OnClickListener onProfileClicked = v -> FN.addFixedNameFadeFragment(MAIN_FRC, MainActivity.this, new ProfileFragment());

    public void showBottomBar() {
        bottomBar.setVisibility(View.VISIBLE);
        bottomBar.animate().setDuration(400).alpha(1);
    }

    public void hideBottomBar() {

        bottomBar.animate().setDuration(400).alpha(0);
        new Handler(getMainLooper()).postDelayed(() -> {

            bottomBar.setVisibility(View.GONE);
        }, 450);

    }

    public void showDrawerButton() {
        drawerButton.setVisibility(View.VISIBLE);
        drawerButton.animate().setDuration(400).alpha(1);
    }

    public void hideDrawerButton() {
        drawerButton.animate().setDuration(400).alpha(0);
        new Handler(getMainLooper()).postDelayed(() -> {
            drawerButton.setVisibility(View.GONE);
        }, 450);
    }


}
