package com.example.dayout.ui.fragments.drawer.Posts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dayout.R;
import com.example.dayout.adapters.pager.PostPagerAdapter;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.ui.fragments.drawer.Posts.tab.PollsFragment;
import com.example.dayout.ui.fragments.drawer.Posts.tab.TripPostFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PostsFragment extends Fragment {


    View view;
    @BindView(R.id.back_arrow)
    ImageButton postBackArrow;
    @BindView(R.id.post_tab_layout)
    TabLayout postTabLayout;
    @BindView(R.id.post_view_pager)
    ViewPager2 postViewPager;

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
        List<Fragment> list = new ArrayList<>();
        list.add(new TripPostFragment());
        list.add(new PollsFragment());

        PostPagerAdapter pagerAdapter = new PostPagerAdapter(requireActivity(),list);

        postViewPager.setAdapter(pagerAdapter);
        new TabLayoutMediator(postTabLayout, postViewPager, (tab, position) -> {
            switch (position) {
                case 0: {
                    tab.setText("Post");

                    break;
                }
                case 1: {
                    tab.setText("Poll");

                    break;
                }
            }
        }).attach();
    }
}