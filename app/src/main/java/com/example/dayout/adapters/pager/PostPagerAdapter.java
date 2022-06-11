package com.example.dayout.adapters.pager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class PostPagerAdapter extends FragmentStateAdapter {

    private final List<Fragment> list;


    public PostPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> list) {
        super(fragmentActivity);
        this.list = list;
    }




    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return list.get(position);
    }


   
    @Override
    public int getItemCount() {
        return list.size();
    }
}
