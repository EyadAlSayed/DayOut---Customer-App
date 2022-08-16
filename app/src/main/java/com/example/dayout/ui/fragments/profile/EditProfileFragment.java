package com.example.dayout.ui.fragments.profile;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.dayout.R;
import com.example.dayout.config.AppConstants;
import com.example.dayout.helpers.system.HttpRequestConverter;
import com.example.dayout.helpers.system.PermissionsHelper;
import com.example.dayout.helpers.view.ConverterImage;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.helpers.view.ImageViewer;

import com.example.dayout.models.profile.ProfileData;
import com.example.dayout.models.room.profileRoom.databases.ProfileDatabase;
import com.example.dayout.ui.activities.MainActivity;
import com.example.dayout.ui.dialogs.notify.ErrorDialog;
import com.example.dayout.ui.dialogs.notify.LoadingDialog;
import com.example.dayout.viewModels.UserViewModel;

import java.io.File;
import java.util.regex.Matcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.example.dayout.api.ApiClient.BASE_URL;
import static com.example.dayout.config.AppSharedPreferences.GET_USER_ID;
import static com.example.dayout.helpers.view.ImageViewer.IMAGE_BASE_URL;

@SuppressLint("NonConstantResourceId")
public class EditProfileFragment extends Fragment {

    View view;

    Uri userImage;

    @BindView(R.id.edit_profile_back_button)
    ImageButton editProfileBackButton;

    @BindView(R.id.edit_profile_done)
    TextView editProfileDone;

    @BindView(R.id.edit_profile_image)
    ImageView editProfileImage;

    @BindView(R.id.edit_profile_edit_button)
    ImageButton editProfileEditButton;

    @BindView(R.id.edit_profile_image_layout)
    ConstraintLayout editProfileImageLayout;

    @BindView(R.id.edit_profile_first_name)
    EditText editProfileFirstName;

    @BindView(R.id.edit_profile_last_name)
    EditText editProfileLastName;

    @BindView(R.id.edit_profile_email)
    EditText editProfileEmail;

    @BindView(R.id.edit_profile_delete_photo_button)
    ImageButton editProfileDeletePhotoButton;

    LoadingDialog loadingDialog;

    ProfileData data;


    public EditProfileFragment(ProfileData data) {
        this.data = data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }


    @Override
    public void onStart() {
        ((MainActivity) requireActivity()).hideBottomBar();
        super.onStart();
    }

    @Override
    public void onStop() {
        ((MainActivity) requireActivity()).showBottomBar();
        super.onStop();
    }

    private void getDataFromRoom() {
        ProfileDatabase.getINSTANCE(requireActivity())
                .iProfileModel()
                .getProfile(GET_USER_ID())
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ProfileData>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ProfileData profileData) {
                        data = profileData;
                        setData();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    private void initViews() {

        if (data == null) getDataFromRoom();
        else setData();
        editProfileDeletePhotoButton.setOnClickListener(onDeleteImageClicked);
        editProfileEditButton.setOnClickListener(onUploadImageClicked);
        editProfileBackButton.setOnClickListener(onBackClicked);
        editProfileDone.setOnClickListener(onDoneClicked);
        loadingDialog = new LoadingDialog(requireContext());
        if(editProfileImage.getDrawable() == getResources().getDrawable(R.drawable.profile_place_holder_orange))
            editProfileDeletePhotoButton.setVisibility(View.GONE);
    }

    private void setData() {
        editProfileFirstName.setText(data.first_name);
        editProfileLastName.setText(data.last_name);
        editProfileEmail.setText(data.email);
        downloadUserImage(data.photo);
    }

    private void downloadUserImage(String url) {

        ImageViewer.downloadCircleImage(requireContext(), editProfileImage, R.drawable.profile_place_holder_orange, IMAGE_BASE_URL + url);
    }

    private boolean checkInfo() {
        return isFirstNameValid() && isLastNameValid() && isEmailValid();
    }

    private boolean isFirstNameValid() {

        Matcher firstNameMatcher = AppConstants.NAME_REGEX.matcher(editProfileFirstName.getText().toString());

        boolean ok = true;


        if (editProfileFirstName.getText().toString().isEmpty()) {
            editProfileFirstName.setError(getResources().getString(R.string.empty_field));

            ok = false;

        } else if (editProfileFirstName.getText().toString().charAt(0) == ' ') {
            editProfileFirstName.setError(getResources().getString(R.string.no_space));

            ok = false;

        } else if (!firstNameMatcher.matches()) {
            editProfileFirstName.setError(getResources().getString(R.string.name_does_not_match));

            ok = false;
        }

        return ok;
    }

    private boolean isLastNameValid() {

        Matcher lastNameMatcher = AppConstants.NAME_REGEX.matcher(editProfileLastName.getText().toString());

        boolean ok = true;

        if (editProfileLastName.getText().toString().isEmpty()) {
            editProfileLastName.setError(getResources().getString(R.string.empty_field));

            ok = false;

        } else if (editProfileLastName.getText().toString().charAt(0) == ' ') {
            editProfileLastName.setError(getResources().getString(R.string.no_space));

            ok = false;

        } else if (!lastNameMatcher.matches()) {
            editProfileLastName.setError(getResources().getString(R.string.name_does_not_match));

            ok = false;
        }

        return ok;
    }

    private boolean isEmailValid() {

        Matcher emailMatcher = AppConstants.EMAIL_REGEX.matcher(editProfileEmail.getText().toString());

        boolean ok = true;

        if (!editProfileEmail.getText().toString().isEmpty()) {
            if (!emailMatcher.matches()) {
                editProfileEmail.setError(getResources().getString(R.string.not_an_email_address));
                ok = false;
            }
        }

        return ok;
    }

    private void selectImage() {
        if (PermissionsHelper.getREAD_EXTERNAL_STORAGE(requireActivity()))
            launcher.launch("image/*");
    }


    private final ActivityResultLauncher<String> launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            editProfileImage.setImageURI(result);
            userImage = result;
        }
    });


    private final View.OnClickListener onUploadImageClicked = view -> selectImage();

    private final View.OnClickListener onDeleteImageClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editProfileImage.setImageURI(Uri.EMPTY);
            userImage = Uri.EMPTY;
        }
    };

    private final View.OnClickListener onBackClicked = view -> FN.popTopStack(requireActivity());

    private final View.OnClickListener onDoneClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (checkInfo()) {
                loadingDialog.show();
                UserViewModel.getINSTANCE().editProfile(getRequestBody("PUT"),
                        getRequestBody(editProfileFirstName.getText().toString()),
                        getRequestBody(editProfileLastName.getText().toString()),
                        getRequestBody(editProfileEmail.getText().toString()),
                        getPhotoAsRequestBody());
                UserViewModel.getINSTANCE().successfulMutableLiveData.observe(requireActivity(), editProfileObserver);
            }
        }
    };

    private RequestBody getRequestBody(String body) {
        return HttpRequestConverter.createStringAsRequestBody("multipart/form-data", body);
    }

    private MultipartBody.Part getPhotoAsRequestBody() {

        if (userImage == null) return null;

        String path = ConverterImage.createImageFilePath(requireActivity(), userImage);
        File file = new File(path);
        RequestBody photoBody = HttpRequestConverter.createFileAsRequestBody("multipart/form-data", file);
        return HttpRequestConverter.createFormDataFile("photo", file.getName(), photoBody);
    }

    private final Observer<Pair<Boolean, String>> editProfileObserver = new Observer<Pair<Boolean, String>>() {
        @Override
        public void onChanged(Pair<Boolean, String> editProfileModelStringPair) {
            loadingDialog.dismiss();
            if (editProfileModelStringPair != null) {
                if (editProfileModelStringPair.first != null) {
                    FN.popTopStack(requireActivity());
                } else
                    new ErrorDialog(requireContext(), editProfileModelStringPair.second).show();
            } else
                new ErrorDialog(requireContext(), getResources().getString(R.string.error_connection)).show();
        }
    };
}