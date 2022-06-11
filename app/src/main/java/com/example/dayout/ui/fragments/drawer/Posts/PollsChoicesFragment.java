package com.example.dayout.ui.fragments.drawer.Posts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dayout.R;
import com.example.dayout.adapters.recyclers.PollChoiceAdapter;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.models.poll.Polls;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PollsChoicesFragment extends Fragment {


    View view;

    Polls.Data data;
    @BindView(R.id.votes_back_button)
    ImageButton arrowBack;
    @BindView(R.id.poll_title)
    TextView pollTitle;
    @BindView(R.id.poll_choice_rc)
    RecyclerView pollChoiceRc;


    PollChoiceAdapter pollChoiceAdapter;

    public PollsChoicesFragment(Polls.Data data) {
        this.data = data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_polls_choices, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        pollTitle.setText(data.title);
        arrowBack.setOnClickListener(v -> FN.popTopStack(requireActivity()));
        initRc();
    }

    private void initRc() {
        pollChoiceRc.setHasFixedSize(true);
        pollChoiceRc.setLayoutManager(new LinearLayoutManager(requireContext()));
        pollChoiceAdapter = new PollChoiceAdapter(data.poll_choices, requireContext());
        pollChoiceRc.setAdapter(pollChoiceAdapter);
    }
}