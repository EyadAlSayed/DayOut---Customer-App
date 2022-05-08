package com.example.dayout.ui.fragments.profile;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.dayout.R;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.models.ProfileModel;
import com.example.dayout.ui.activities.MainActivity;
import com.example.dayout.ui.dialogs.ErrorDialog;
import com.example.dayout.viewModels.UserViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.dayout.config.AppConstants.MAIN_FRC;

@SuppressLint("NonConstantResourceId")
public class ProfileFragment extends Fragment {

    View view;

    @BindView(R.id.back_arrow_btn)
    ImageButton backArrowButton;

    @BindView(R.id.profile_edit_button)
    ImageButton profileEditButton;

    @BindView(R.id.profile_image)
    CircleImageView profileImage;

    @BindView(R.id.profile_following_count)
    TextView profileFollowingCount;

    @BindView(R.id.profile_trips_count)
    TextView profileTripsCount;

    @BindView(R.id.profile_gender)
    TextView profileGender;

    @BindView(R.id.profile_phone_number)
    TextView profilePhoneNumber;

    @BindView(R.id.profile_email)
    TextView profileEmail;

    @BindView(R.id.profile_email_icon)
    ImageButton email_icon;

    @BindView(R.id.profile_email_TV)
    TextView email_TV;

    @BindView(R.id.profile_full_name)
    TextView profileFullName;



    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        initViews();
        getDataFromAPI();
        return view;
    }

    @Override
    public void onStart() {
        ((MainActivity)requireActivity()).hideBottomBar();
        super.onStart();
    }

    @Override
    public void onStop() {
        ((MainActivity)requireActivity()).showBottomBar();
        super.onStop();
    }

    private void initViews(){
        backArrowButton.setOnClickListener(onBackArrowClicked);
        profileEditButton.setOnClickListener(onEditProfileClicked);
    }

    private void getDataFromAPI(){
        UserViewModel.getINSTANCE().getPassengerProfile();
        UserViewModel.getINSTANCE().profileMutableLiveData.observe(requireActivity(), profileObserver);
    }

    private Observer<Pair<ProfileModel, String>> profileObserver = new Observer<Pair<ProfileModel, String>>() {
        @Override
        public void onChanged(Pair<ProfileModel, String> profileModelStringPair) {
            if(profileModelStringPair != null){
                if(profileModelStringPair.first != null){
                    setData(profileModelStringPair.first);
                } else
                    new ErrorDialog(requireContext(), profileModelStringPair.second).show();
            } else
                new ErrorDialog(requireContext(), "Error Connection");
        }
    };

    private void setData(ProfileModel model){
        setName(model.first_name, model.last_name);
        profileImage.setImageURI(Uri.parse(model.photo));
        profileTripsCount.setText(String.valueOf(model.trips_count));
        profileFollowingCount.setText(String.valueOf(model.organizer_follow_count));
        profileGender.setText(model.gender);
        profilePhoneNumber.setText(model.phone_number);
        setEmail(model.email);
    }

    private void setEmail(String email){
        if(email == null){
            profileEmail.setVisibility(View.GONE);
            email_icon.setVisibility(View.GONE);
            email_TV.setVisibility(View.GONE);
        } else
            profileEmail.setText(email);
    }

    private void setName(String firstName, String lastName){
        profileFullName.setText(firstName + " " + lastName);
    }

    private final View.OnClickListener onBackArrowClicked = view -> FN.popTopStack(requireActivity());

    private final View.OnClickListener onEditProfileClicked = view -> FN.addFixedNameFadeFragment(MAIN_FRC, requireActivity(), new EditProfileFragment());

}
