package com.example.dayout.ui.fragments.drawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dayout.R;

import butterknife.ButterKnife;


public class FollowedOrganizersFragment extends Fragment {


    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_followed_organizers, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
}