package com.example.dayout.ui.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dayout.R;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.ui.fragments.auth.AuthFragment;

import butterknife.ButterKnife;

import static com.example.dayout.config.AppConstants.AUTH_FRC;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_activity);
        ButterKnife.bind(this);
        FN.addFixedNameFadeFragment(AUTH_FRC, this, new AuthFragment());
    }
}
