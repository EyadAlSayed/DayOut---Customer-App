package com.example.dayout.ui.fragments.profile.organizer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.dayout.R;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.helpers.view.ImageViewer;
import com.example.dayout.models.profile.ProfileData;
import com.example.dayout.models.profile.ProfileModel;
import com.example.dayout.ui.dialogs.ReportDialog;
import com.example.dayout.ui.dialogs.notify.LoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dayout.api.ApiClient.BASE_URL;

@SuppressLint("NonConstantResourceId")
public class OrganizerProfileFragment extends Fragment {

    View view;

    @BindView(R.id.organizer_back_arrow_btn)
    ImageButton backArrowButton;

    @BindView(R.id.organizer_profile_image)
    ImageView profileImage;

    @BindView(R.id.organizer_profile_bio)
    TextView profileBio;

    @BindView(R.id.organizer_profile_followers_count)
    TextView profileFollowersCount;

    @BindView(R.id.organizer_profile_trips_count)
    TextView profileTripsCount;

    @BindView(R.id.organizer_profile_gender)
    TextView profileGender;

    @BindView(R.id.organizer_profile_phone_number)
    TextView profilePhoneNumber;

    @BindView(R.id.organizer_profile_email)
    TextView profileEmail;

    @BindView(R.id.organizer_profile_full_name)
    TextView profileFullName;

    @BindView(R.id.organizer_profile_email_TV)
    TextView emailTV;

    @BindView(R.id.organizer_profile_email_icon)
    ImageButton emailIcon;

    @BindView(R.id.organizer_profile_rate)
    TextView profileRate;

    @BindView(R.id.organizer_profile_follow_button)
    Button followButton;

    @BindView(R.id.organizer_profile_report_button)
    Button reportButton;

    LoadingDialog loadingDialog;

    ProfileModel profileModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_organizer_profile, container, false);
        ButterKnife.bind(this, view);
        initViews();
        //getDataFromAPI();
        return view;
    }

    private void initViews() {
        loadingDialog = new LoadingDialog(requireContext());
        backArrowButton.setOnClickListener(onBackArrowClicked);
        followButton.setOnClickListener(onFollowClicked);
        reportButton.setOnClickListener(onReportClicked);
    }

    // this code is from dayout - organizer.
//    private void getDataFromRoom(){
//        ProfileDatabase.getINSTANCE(requireContext())
//                .iProfileModel()
//                .getProfile(GET_USER_ID())
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<ProfileData>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(@NonNull ProfileData profileData) {
//                        setData(profileData);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                    }
//                });
//    }

//    private void getDataFromAPI() {
//        UserViewModel.getINSTANCE().getOrganizerProfile(GET_USER_ID());
//        UserViewModel.getINSTANCE().profileMutableLiveData.observe(requireActivity(), profileObserver);
//    }

//    private final Observer<Pair<ProfileModel, String>> profileObserver = new Observer<Pair<ProfileModel, String>>() {
//        @Override
//        public void onChanged(Pair<ProfileModel, String> profileModelStringPair) {
//            loadingDialog.dismiss();
//            if (profileModelStringPair != null) {
//                if (profileModelStringPair.first != null) {
//                    setData(profileModelStringPair.first.data);
//                    profileModel = profileModelStringPair.first;
//                } else {
//                    getDataFromRoom();
//                    new ErrorDialog(requireContext(), profileModelStringPair.second).show();
//                }
//            } else {
//                getDataFromRoom();
//                new ErrorDialog(requireContext(), "Error Connection").show();
//            }
//        }
//    };

    private void setData(ProfileData data) {
//        setName(data.user.first_name, data.user.last_name);
//        if (data.bio != null)
//            setBio(data.bio);
//        profileTripsCount.setText(String.valueOf(data.trips_count));
//        profileFollowersCount.setText(String.valueOf(data.followers_count));
//        profileGender.setText(data.user.gender);
//        profilePhoneNumber.setText(data.user.phone_number);
//        profileRate.setText(String.valueOf(data.rating));
//        setEmail(data.user.email);
//        downloadUserImage(data.user.photo);
    }

    private void downloadUserImage(String url) {
        String baseUrl = BASE_URL.substring(0, BASE_URL.length() - 1);
        ImageViewer.downloadCircleImage(requireContext(), profileImage, R.drawable.profile_place_holder, baseUrl + url);
    }

    @SuppressLint("SetTextI18n")
    private void setName(String firstName, String lastName) {
        profileFullName.setText(firstName + " " + lastName);
    }

    private void setEmail(String email) {
        if (email == null) {
            profileEmail.setVisibility(View.GONE);
            emailIcon.setVisibility(View.GONE);
            emailTV.setVisibility(View.GONE);
        } else
            profileEmail.setText(email);
    }

    private void setBio(String bio) {
        if (bio != null) {
            profileBio.setText(bio);
        } else {
            profileBio.setText(R.string.biography);
        }
    }


    private final View.OnClickListener onBackArrowClicked = view -> FN.popTopStack(requireActivity());

    private final View.OnClickListener onReportClicked = v -> new ReportDialog(requireContext(), profileFullName.getText().toString()).show();

    private final View.OnClickListener onFollowClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //after following
            followButton.setText(R.string.unfollow);

            //after unfollowing
            followButton.setText(R.string.follow);
        }
    };
}