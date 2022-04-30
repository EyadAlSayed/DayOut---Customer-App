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
import com.example.dayout.config.AppConstants;
import com.example.dayout.helpers.system.PermissionsHelper;
import com.example.dayout.helpers.view.ConverterImage;
import com.example.dayout.helpers.view.FN;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dayout.config.AppConstants.AUTH_FRC;

@SuppressLint("NonConstantResourceId")
public class SignUpFragment extends Fragment {

    @BindView(R.id.signup_male)
    RadioButton maleRadioButton;

    @BindView(R.id.signup_female)
    RadioButton femaleRadioButton;

    @BindView(R.id.sign_up_first_name)
    TextInputEditText firstName;

    @BindView(R.id.signup_first_name_textlayout)
    TextInputLayout firstNameTextlayout;

    @BindView(R.id.sign_up_last_name)
    TextInputEditText lastName;

    @BindView(R.id.signup_last_name_textlayout)
    TextInputLayout lastNameTextlayout;

    @BindView(R.id.sign_up_password)
    TextInputEditText password;

    @BindView(R.id.sign_up_password_textlayout)
    TextInputLayout passwordTextlayout;

    @BindView(R.id.sign_up_confirm_password)
    TextInputEditText confirmPassword;

    @BindView(R.id.sign_up_confirm_password_textlayout)
    TextInputLayout confirmPasswordTextlayout;

    @BindView(R.id.sign_up_phone_number)
    TextInputEditText phoneNumber;

    @BindView(R.id.sign_up_phone_number_textlayout)
    TextInputLayout phoneNumberTextlayout;

    @BindView(R.id.sign_up_button)
    Button signUpButton;

    @BindView(R.id.sign_up_to_login)
    TextView signUpToLogin;

    @BindView(R.id.sign_up_radio_group)
    RadioGroup radioGroup;

    @BindView(R.id.sign_up_email)
    TextInputEditText signUpEmail;

    @BindView(R.id.sign_up_email_textlayout)
    TextInputLayout signUpEmailTextlayout;

    View view;

    public SignUpFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView(){
        firstName.addTextChangedListener(firstNameWatcher);
        lastName.addTextChangedListener(lastNameWatcher);
        password.addTextChangedListener(passwordWatcher);
        confirmPassword.addTextChangedListener(passwordConfirmationWatcher);
        phoneNumber.addTextChangedListener(phoneNumberWatcher);
        signUpToLogin.setOnClickListener(onToLoginClicked);
        signUpButton.setOnClickListener(onSignUpBtnClicked);
    }

    private boolean checkInfo() {

        boolean firstNameValidation = isFirstNameValid();
        boolean lastNameValidation = isLastNameValid();
        boolean passwordValidation = isPasswordValid();
        boolean emailValidation = isEmailValid();
        boolean phoneNumberValidation = isPhoneNumberValid();

        return firstNameValidation && lastNameValidation && passwordValidation && emailValidation && phoneNumberValidation;
    }

    private boolean isFirstNameValid() {

        Matcher firstNameMatcher = AppConstants.NAME_REGEX.matcher(firstName.getText().toString());

        boolean ok = true;


        if (firstName.getText().toString().isEmpty()) {
            firstNameTextlayout.setErrorEnabled(true);
            firstNameTextlayout.setError(getResources().getString(R.string.empty_field));
            ok = false;

        } else if (firstName.getText().toString().charAt(0) == ' ') {
            firstNameTextlayout.setErrorEnabled(true);
            firstNameTextlayout.setError(getResources().getString(R.string.no_space));

            ok = false;

        } else if (!firstNameMatcher.matches()) {
            firstNameTextlayout.setErrorEnabled(true);
            firstNameTextlayout.setError(getResources().getString(R.string.name_does_not_match));

            ok = false;
        }

        return ok;
    }

    private boolean isLastNameValid() {

        Matcher lastNameMatcher = AppConstants.NAME_REGEX.matcher(lastName.getText().toString());

        boolean ok = true;

        if (lastName.getText().toString().isEmpty()) {
            lastNameTextlayout.setErrorEnabled(true);
            lastNameTextlayout.setError(getResources().getString(R.string.empty_field));

            ok = false;

        } else if (lastName.getText().toString().charAt(0) == ' ') {
            lastNameTextlayout.setErrorEnabled(true);
            lastNameTextlayout.setError(getResources().getString(R.string.no_space));

            ok = false;

        } else if (!lastNameMatcher.matches()) {
            lastNameTextlayout.setErrorEnabled(true);
            lastNameTextlayout.setError(getResources().getString(R.string.name_does_not_match));

            ok = false;
        }

        return ok;
    }

    private boolean isPasswordValid() {

        boolean ok = true;

        if (password.getText().toString().length() < 6) {
            passwordTextlayout.setErrorEnabled(true);
            passwordTextlayout.setError(getResources().getString(R.string.password_limit));

            ok = false;

        } else if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            confirmPasswordTextlayout.setErrorEnabled(true);
            confirmPasswordTextlayout.setError(getResources().getString(R.string.does_not_match_password));

            ok = false;
        }

        return ok;
    }

    private boolean isEmailValid() {

        Matcher emailMatcher = AppConstants.EMAIL_REGEX.matcher(signUpEmail.getText().toString());

        boolean ok = true;

        if (!signUpEmail.getText().toString().isEmpty()) {
            if (!emailMatcher.matches()) {
                signUpEmailTextlayout.setErrorEnabled(true);
                signUpEmailTextlayout.setError(getResources().getString(R.string.not_an_email_address));

                ok = false;
            }
        }

        return ok;
    }

    private boolean isPhoneNumberValid() {

        Matcher phoneNumberMatcher = AppConstants.PHONE_NUMBER_REGEX.matcher(phoneNumber.getText().toString());

        boolean ok = true;

        if (!phoneNumberMatcher.matches()) {
            phoneNumberTextlayout.setErrorEnabled(true);
            phoneNumberTextlayout.setError(getResources().getString(R.string.not_a_phone_number));

            ok = false;
        }

        return ok;
    }

    private final View.OnClickListener onSignUpBtnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(checkInfo()){
                System.out.println("VALID");
                //TODO: Send Object to Back - Caesar.
            }
        }
    };

    private final View.OnClickListener onToLoginClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FN.addFixedNameFadeFragment(AUTH_FRC, requireActivity(), new LoginFragment());
        }
    };

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

    private final TextWatcher firstNameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            firstNameTextlayout.setErrorEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    };

    private final TextWatcher lastNameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            lastNameTextlayout.setErrorEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    };

    private final TextWatcher passwordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            passwordTextlayout.setErrorEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private final TextWatcher phoneNumberWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            phoneNumberTextlayout.setErrorEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    };
}