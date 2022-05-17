package com.example.dayout.ui.fragments.drawer.Posts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.dayout.R;
import com.example.dayout.adapters.pager.PostPagerAdapter;
import com.example.dayout.helpers.view.FN;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PostsFragment extends Fragment {


    View view;
    @BindView(R.id.back_arrow)
    ImageButton postBackArrow;
    @BindView(R.id.post_tab_layout)
    TabLayout postTabLayout;
    @BindView(R.id.post_view_pager)
    ViewPager postViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_posts, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView(){
        postBackArrow.setOnClickListener(v -> FN.popTopStack(requireActivity()));
        initTabLayout();
    }

    private void initTabLayout() {
        PostPagerAdapter adapter = new PostPagerAdapter(requireActivity().getSupportFragmentManager());
        adapter.addFragment(new TripPostFragment(), "Post");
        adapter.addFragment(new TripPostFragment(), "Poll");

        postViewPager.setAdapter(adapter);
        postTabLayout.setupWithViewPager(postViewPager);
    }
}