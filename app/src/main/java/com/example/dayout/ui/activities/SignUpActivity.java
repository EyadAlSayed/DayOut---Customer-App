package com.example.dayout.ui.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.dayout.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    //TODO: Make it able to delete profile image - Caesar.
    //TODO: Authenticate the username and the phone number fields - Caesar.
    //TODO: Make fields mandatory to fill - Caesar.
    //TODO: Link to login interface - Caesar.
    //FIXME: Lift screen up when showing keyboard - Caesar.

    String sImage;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.signup_profile_image)
    ImageView profile_image;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.signup_upload_image)
    ImageButton uploadImageButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.first_divider)
    View firstDivider;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.signup_username_field)
    EditText usernameField;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.signup_password_field)
    EditText passwordField;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.signup_password_confirmation)
    EditText passwordConfirmation;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.signup_male)
    RadioButton maleRadioButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.signup_female)
    RadioButton femaleRadioButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.phoneNumberField)
    EditText phoneNumberField;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.uploadImage_TV)
    TextView uploadImage_TV;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.signup_edit_profile_image)
    ImageButton editProfileImageButton;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.signup_profile_layout)
    LinearLayout profileLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        ButterKnife.bind(this);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick({R.id.signup_upload_image, R.id.signup_button, R.id.signup_edit_profile_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signup_upload_image:
                    pickImageFromGallery();
                break;

            case R.id.signup_button:

                //TODO: Activate sign up button - Caesar.

                break;

            case R.id.signup_edit_profile_image:
                    pickImageFromGallery();
                break;
        }
    }

    private void pickImageFromGallery(){
        if (ContextCompat.checkSelfPermission(SignUpActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //permission not granted
            ActivityCompat.requestPermissions(SignUpActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        } else {
            //permission granted
            selectImage();
        }
    }

    private void selectImage() {

        //clear previous data
        profile_image.setImageBitmap(null);

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Image"), 100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //permission granted
            selectImage();
        } else {
            //permission denied
            Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            //result is ok
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] bytes = stream.toByteArray();
                sImage = Base64.encodeToString(bytes, Base64.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            setVisibilities();
            decodeImage(sImage);
        }
    }

    private void decodeImage(String sImage) {
        byte[] bytes = Base64.decode(sImage, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        profile_image.setImageBitmap(bitmap);
    }

    private void setVisibilities() {
        uploadImage_TV.setVisibility(View.GONE);
        uploadImageButton.setVisibility(View.GONE);
        firstDivider.setVisibility(View.GONE);
        profileLayout.setVisibility(View.VISIBLE);
    }
}
