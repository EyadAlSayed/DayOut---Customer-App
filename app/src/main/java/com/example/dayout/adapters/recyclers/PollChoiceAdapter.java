package com.example.dayout.adapters.recyclers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dayout.R;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.helpers.view.NoteMessage;
import com.example.dayout.models.poll.PollChoice;
import com.example.dayout.ui.activities.MainActivity;
import com.example.dayout.ui.dialogs.notify.ErrorDialog;
import com.example.dayout.ui.dialogs.notify.LoadingDialog;
import com.example.dayout.viewModels.TripViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PollChoiceAdapter extends RecyclerView.Adapter<PollChoiceAdapter.ViewHolder> {

    List<PollChoice> list;
    Context context;
    LoadingDialog loadingDialog;
    int totalVotes;

    public PollChoiceAdapter(List<PollChoice> list, Context context, int totalVotes) {
        this.list = list;
        this.context = context;
        loadingDialog = new LoadingDialog(context);
        this.totalVotes = totalVotes == 0 ? 1 : totalVotes;
    }

    public void refresh(List<PollChoice> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poll_choice, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.voteTitle.setText(list.get(position).value);
        int percentage = calculatePercentage(list.get(position).users.size());
        holder.votePercentage.setText(percentage + "%");
        holder.progressBar.setProgress(percentage, true);
    }


    private int calculatePercentage(int votes) {
        return (votes * 100) / totalVotes;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.vote_title)
        TextView voteTitle;
        @BindView(R.id.progressBar)
        ProgressBar progressBar;
        @BindView(R.id.vote_percentage)
        TextView votePercentage;
        @BindView(R.id.vote_btn)
        Button voteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            voteButton.setOnClickListener(v -> sendVoteRequest());
        }

        private void sendVoteRequest() {
            loadingDialog.show();
            int pollId = list.get(getAdapterPosition()).poll_id;
            int choiceId = list.get(getAdapterPosition()).id;
            TripViewModel.getINSTANCE().voteOnPoll(pollId, choiceId);
            TripViewModel.getINSTANCE().successfulMutableLiveData.observe((MainActivity) context, successfulObserver);
        }

        private final Observer<Pair<Boolean, String>> successfulObserver = new Observer<Pair<Boolean, String>>() {
            @Override
            public void onChanged(Pair<Boolean, String> booleanStringPair) {
                loadingDialog.dismiss();

                if (booleanStringPair != null) {
                    if (booleanStringPair.first != null && booleanStringPair.first) {
                        NoteMessage.showSnackBar((MainActivity) context, "Thanks for your vote ");
                        FN.popTopStack((MainActivity) context);
                    } else {
                        new ErrorDialog(context, booleanStringPair.second).show();
                    }
                } else {
                    new ErrorDialog(context, "Connection Error").show();

                }
            }
        };


    }
}
