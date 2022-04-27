package com.example.dayout.ui.fragments.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dayout.R;

import butterknife.ButterKnife;


public class FavoritePlaceFragment extends Fragment {


    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_favorite_place, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}