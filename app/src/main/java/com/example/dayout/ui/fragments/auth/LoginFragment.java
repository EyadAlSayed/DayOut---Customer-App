package com.example.dayout.ui.fragments.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.dayout.R;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.ui.activities.AuthActivity;
import com.example.dayout.ui.activities.MainActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dayout.config.AppConstants.AUTH_FRC;


public class LoginFragment extends Fragment {


    View view;
    @BindView(R.id.create_account_txt)
    TextView createAccountTxt;
    @BindView(R.id.user_name)
    TextInputEditText userName;
    @BindView(R.id.user_name_textlayout)
    TextInputLayout userNameTextlayout;
    @BindView(R.id.password)
    TextInputEditText password;
    @BindView(R.id.password_textlayout)
    TextInputLayout passwordTextlayout;
    @BindView(R.id.remember_me_switch)
    Switch rememberMeSwitch;
    @BindView(R.id.login_btn)
    Button loginButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView(){
        loginButton.setOnClickListener(onLoginClicked);
        createAccountTxt.setOnClickListener(onCreateClicked);
        password.addTextChangedListener(onTextChanged);
    }


    private final View.OnClickListener onLoginClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            requireActivity().startActivity(new Intent(requireActivity(), MainActivity.class));
            requireActivity().finish();
            if (checkInfo()){
                //TODO EYAD send login request;
            }
        }
    };

    private final View.OnClickListener onCreateClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO EYAD go to SignUp Fragment

        }
    };

    private final TextWatcher  onTextChanged = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            passwordTextlayout.setErrorEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private boolean checkInfo(){

        boolean ok = true;
        if (userName.getText().toString().isEmpty()){
            ok =false;
            userNameTextlayout.setErrorEnabled(true);
            userNameTextlayout.setError("This filed is required");
        }else  userNameTextlayout.setErrorEnabled(false);


        if (password.getText().toString().isEmpty()){
            ok =false;
            passwordTextlayout.setErrorEnabled(true);
            passwordTextlayout.setError("This filed is required");
        }else  passwordTextlayout.setErrorEnabled(false);


        return ok;
    }
}