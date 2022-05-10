package com.example.dayout.viewModels;

import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.example.dayout.api.ApiClient;
import com.example.dayout.models.EditProfileModel;
import com.example.dayout.models.ProfileModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.dayout.config.AppConstants.getErrorMessage;

public class UserViewModel {

    private final String TAG ="UserViewModel";
    private final ApiClient apiClient = new ApiClient();
    private static UserViewModel instance;
    public MutableLiveData<Pair<ProfileModel, String>> profileMutableLiveData;

    public MutableLiveData<Pair<EditProfileModel, String>> editProfileMutableLiveData;

    public static UserViewModel getINSTANCE(){
        if(instance == null){
            instance = new UserViewModel();
        }
        return instance;
    }

    public void getPassengerProfile(int passengerId){
        profileMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().getPassengerProfile(passengerId).enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {

                Log.d(TAG, "onResponse: getPassengerProfile "+response.code());
                Log.d(TAG, "onResponse: getPassengerProfile "+response.body());

                if(response.isSuccessful()){
                    profileMutableLiveData.setValue(new Pair<>(response.body(), null));
                } else {
                    profileMutableLiveData.setValue(new Pair<>(null, response.message()));
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                profileMutableLiveData.setValue(null);
            }
        });
    }

    public void editProfile(int passengerId, EditProfileModel model){
        editProfileMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().editProfile(passengerId, model).enqueue(new Callback<EditProfileModel>() {
            @Override
            public void onResponse(Call<EditProfileModel> call, Response<EditProfileModel> response) {
                if(response.isSuccessful()){
                    editProfileMutableLiveData.setValue(new Pair<>(response.body(), null));
                } else {
                    try {
                        editProfileMutableLiveData.setValue(new Pair<>(null, getErrorMessage(response.errorBody().string())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<EditProfileModel> call, Throwable t) {
                editProfileMutableLiveData.setValue(null);
            }
        });
    }
}