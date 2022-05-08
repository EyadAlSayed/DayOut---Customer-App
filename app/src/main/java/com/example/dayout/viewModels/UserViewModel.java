package com.example.dayout.viewModels;

import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.example.dayout.api.ApiClient;
import com.example.dayout.models.EditProfileModel;
import com.example.dayout.models.ProfileModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel {
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

    public void getPassengerProfile(){
        profileMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().getPassengerProfile().enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                if(response.isSuccessful()){
                    profileMutableLiveData.setValue(new Pair<>(response.body(), null));
                } else {
                    profileMutableLiveData.setValue(new Pair<>(null, response.body().message));
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                profileMutableLiveData.setValue(null);
            }
        });
    }

    public void editProfile(EditProfileModel model){
        editProfileMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().editProfile(model).enqueue(new Callback<EditProfileModel>() {
            @Override
            public void onResponse(Call<EditProfileModel> call, Response<EditProfileModel> response) {
                if(response.isSuccessful()){
                    editProfileMutableLiveData.setValue(new Pair<>(response.body(), null));
                } else {
                    editProfileMutableLiveData.setValue(new Pair<>(null, response.message()));
                }
            }

            @Override
            public void onFailure(Call<EditProfileModel> call, Throwable t) {
                editProfileMutableLiveData.setValue(null);
            }
        });
    }
}