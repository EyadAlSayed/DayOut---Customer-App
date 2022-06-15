package com.example.dayout.ui.fragments.drawer.Posts.tab;

import android.annotation.SuppressLint;
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
import com.example.dayout.models.poll.PollChoice;
import com.example.dayout.models.poll.PollsData;

import butterknife.BindView;
import butterknife.ButterKnife;


@SuppressLint("NonConstantResourceId")
public class PollsChoicesFragment extends Fragment {


    View view;

    PollsData data;

    @BindView(R.id.votes_back_button)
    ImageButton arrowBack;
    @BindView(R.id.poll_title)
    TextView pollTitle;
    @BindView(R.id.poll_choice_rc)
    RecyclerView pollChoiceRc;


    PollChoiceAdapter pollChoiceAdapter;

    public PollsChoicesFragment(PollsData data) {
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
        pollChoiceAdapter = new PollChoiceAdapter(data.poll_choices, requireContext(),getVotesAvg());
        pollChoiceRc.setAdapter(pollChoiceAdapter);
    }

    private int getVotesAvg(){
        int avg = 0;
        for (PollChoice pc : data.poll_choices){
            avg += pc.users.size();
        }
        return avg;
    }
}