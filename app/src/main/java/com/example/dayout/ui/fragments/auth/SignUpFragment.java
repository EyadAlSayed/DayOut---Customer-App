package com.example.dayout.ui.fragments.auth;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.dayout.R;
import com.example.dayout.helpers.view.ConverterImage;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SignUpFragment extends Fragment {

    //TODO: Valid the username and the phone number fields - Caesar.
    //TODO: Make fields mandatory to fill - Caesar.
    //TODO: Link to login interface - Caesar.
    //FIXME: Lift screen up when showing keyboard - Caesar.

    String sImage;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.signup_male)
    RadioButton maleRadioButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.signup_female)
    RadioButton femaleRadioButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sign_up_first_name)
    TextInputEditText firstName;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.signup_first_name_textlayout)
    TextInputLayout firstNameTextlayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sign_up_last_name)
    TextInputEditText lastName;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.signup_last_name_textlayout)
    TextInputLayout lastNameTextlayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sign_up_password)
    TextInputEditText password;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sign_up_password_textlayout)
    TextInputLayout passwordTextlayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sign_up_confirm_password)
    TextInputEditText confirmPassword;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sign_up_confirm_password_textlayout)
    TextInputLayout confirmPasswordTextlayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sign_up_phone_number)
    TextInputEditText phoneNumber;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sign_up_phone_number_textlayout)
    TextInputLayout phoneNumberTextlayout;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sign_up_button)
    Button signUpButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sign_up_to_login)
    TextView signUpToLogin;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.sign_up_radio_group)
    RadioGroup radioGroup;

    View view;


    private void pickImageFromGallery() {
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //permission not granted
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        } else {
            //permission granted
            selectImage();
        }
    }

    private void selectImage() {

        //clear previous data
        //profile_image.setImageBitmap(null);

//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//        startActivityForResult(Intent.createChooser(intent, "Select Image"), 100);
        launcher.launch("image/*");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //permission granted
            selectImage();
        } else {
            //permission denied
            Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    private final ActivityResultLauncher<String> launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            //profile_image.setImageURI(result);
            String image = ConverterImage.convertUriToBase64(requireContext(), result);
        }
    });

    public SignUpFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private final View.OnClickListener onSignUpBtnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private final View.OnClickListener onToLoginClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private void checkInfo() {

        if (password.getText().toString().equals(confirmPassword.getText().toString())) {
            confirmPasswordTextlayout.setErrorEnabled(true);
            confirmPasswordTextlayout.setError(getResources().getString(R.string.does_not_match_password));
        }

//        if (/*first name regex*/) {
//        }
//
//        if (/*last name regex*/) {
//        }
//
//        if (/*phone number regex*/) {
//        }

    }

    private final TextWatcher passwordConfirmationWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            confirmPasswordTextlayout.setErrorEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    };

    private void initView(){
        confirmPassword.addTextChangedListener(passwordConfirmationWatcher);
    }
}