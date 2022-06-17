package com.example.dayout.adapters.recyclers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dayout.R;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.helpers.view.ImageViewer;
import com.example.dayout.models.profile.ProfileData;
import com.example.dayout.ui.activities.MainActivity;
import com.example.dayout.ui.fragments.profile.organizer.OrganizerProfileFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dayout.api.ApiClient.BASE_URL;
import static com.example.dayout.config.AppConstants.MAIN_FRC;

public class OrganizersAdapter extends RecyclerView.Adapter<OrganizersAdapter.ViewHolder> {

    List<ProfileData> organizers;
    Context context;

    public OrganizersAdapter(List<ProfileData> organizers, Context context){
        this.organizers = organizers;
        this.context = context;
    }

    public void refreshList(List<ProfileData> organizers){
        this.organizers = organizers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrganizersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_organizer_item, parent, false);
        return new OrganizersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrganizersAdapter.ViewHolder holder, int position) {
        String name = organizers.get(position).user.first_name + " " + organizers.get(position).user.last_name;

        downloadUserImage(organizers.get(position).user.photo, holder.photo);
        holder.name.setText(name);
        holder.rate.setText(String.valueOf(roundRating(organizers.get(position).rating)));
    }

    @Override
    public int getItemCount() {
        return organizers.size();
    }

    private void downloadUserImage(String url, ImageView imageView) {
        String baseUrl = BASE_URL.substring(0, BASE_URL.length() - 1);
        ImageViewer.downloadCircleImage(context, imageView, R.drawable.profile_place_holder_orange, baseUrl + url);
    }

    private float roundRating(float rating){
        return (float) (Math.round(rating * 10) / 10.0);
    }

    @SuppressLint("NonConstantResourceId")
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.organizer_item_photo)
        ImageView photo;

        @BindView(R.id.organizer_item_name)
        TextView name;

        @BindView(R.id.organizer_item_rate)
        TextView rate;

        @BindView(R.id.organizer_item_info_button)
        ImageButton infoButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            infoButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ProfileData data = organizers.get(getAdapterPosition());
            FN.addFixedNameFadeFragment(MAIN_FRC, (MainActivity) context, new OrganizerProfileFragment(data));
        }
    }
}