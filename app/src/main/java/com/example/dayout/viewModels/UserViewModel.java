package com.example.dayout.viewModels;

import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.MutableLiveData;

import com.example.dayout.api.ApiClient;
import com.example.dayout.models.notification.NotificationModel;

import com.example.dayout.models.profile.ProfileModel;
import com.example.dayout.models.profile.organizer.OrganizersModel;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.dayout.api.ApiClient.BASE_URL;
import static com.example.dayout.config.AppConstants.getErrorMessage;

public class UserViewModel {

    private final String TAG ="UserViewModel";
    private final ApiClient apiClient = new ApiClient();
    private static UserViewModel instance;



    public static final String  USER_PHOTO_URL = BASE_URL + "api/user/profile/id/photo";

    public MutableLiveData<Pair<ProfileModel, String>> profileMutableLiveData;
    public MutableLiveData<Pair<ProfileModel, String>> editProfileMutableLiveData;
    public MutableLiveData<Pair<NotificationModel, String>> notificationMutableLiveData;
    public MutableLiveData<Pair<OrganizersModel, String>> organizersMutableLiveData;
    public MutableLiveData<Pair<Boolean,String>> successfulMutableLiveData;
    public MutableLiveData<Pair<Boolean, String>> reportMutableLiveData;
    public MutableLiveData<Pair<Boolean, String>> followMutableLiveData;

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
                    try {
                        profileMutableLiveData.setValue(new Pair<>(null, getErrorMessage(response.errorBody().string())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                profileMutableLiveData.setValue(null);
            }
        });
    }
    public void getNotifications(){
        notificationMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().getNotifications().enqueue(new Callback<NotificationModel>() {
            @Override
            public void onResponse(Call<NotificationModel> call, Response<NotificationModel> response) {
                if(response.isSuccessful()){
                    notificationMutableLiveData.setValue(new Pair<>(response.body(), null));
                } else {
                    try {
                        notificationMutableLiveData.setValue(new Pair<>(null, getErrorMessage(response.errorBody().string())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<NotificationModel> call, Throwable t) {
                notificationMutableLiveData.setValue(null);
            }
        });
    }
    public void getAllOrganizers(){
        organizersMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().getAllOrganizers().enqueue(new Callback<OrganizersModel>() {
            @Override
            public void onResponse(Call<OrganizersModel> call, Response<OrganizersModel> response) {
                if(response.isSuccessful()){
                    organizersMutableLiveData.setValue(new Pair<>(response.body(), null));
                } else {
                    try {
                        organizersMutableLiveData.setValue(new Pair<>(null, getErrorMessage(response.errorBody().string())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<OrganizersModel> call, Throwable t) {
                organizersMutableLiveData.setValue(null);
            }
        });
    }
    public void  getAllFollowedOrganizers(){
        organizersMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().getAllFollowedOrganizers().enqueue(new Callback<OrganizersModel>() {
            @Override
            public void onResponse(Call<OrganizersModel> call, Response<OrganizersModel> response) {
                if (response.isSuccessful()){
                    organizersMutableLiveData.setValue(new Pair<>(response.body(),null));
                }
                else {
                    try {
                        organizersMutableLiveData.setValue(new Pair<>(null, getErrorMessage(response.errorBody().string())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<OrganizersModel> call, Throwable t) {
                organizersMutableLiveData.setValue(null);
            }
        });
    }
    public void logOut( ){
        successfulMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().logOut().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    successfulMutableLiveData.setValue(new Pair<>(true,null));
                }
                else {
                    try {
                        successfulMutableLiveData.setValue(new Pair<>(null, getErrorMessage(response.errorBody().string())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                successfulMutableLiveData.setValue(null);
            }
        });
    }

    public void sendFirebaseToken(JsonObject firebaseObj){
        successfulMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().sendFirebaseToken(firebaseObj).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    successfulMutableLiveData.setValue(new Pair<>(true,null));
                }
                else {
                    try {
                        successfulMutableLiveData.setValue(new Pair<>(null, getErrorMessage(response.errorBody().string())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                successfulMutableLiveData.setValue(null);
            }
        });
    }


    public void editProfile(JsonObject model){
        editProfileMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().editProfile(model).enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
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
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                editProfileMutableLiveData.setValue(null);
            }
        });
    }

    public void reportUser(JsonObject object){
        reportMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().reportUser(object).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    reportMutableLiveData.setValue(new Pair<>(true, null));
                } else {
                    try {
                        reportMutableLiveData.setValue(new Pair<>(null, getErrorMessage(response.errorBody().string())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                reportMutableLiveData.setValue(null);
            }
        });
    }

    public void followOrganizer(int organizerId){
        followMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().followOrganizer(organizerId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    followMutableLiveData.setValue(new Pair<>(true, null));
                } else {
                    try {
                        followMutableLiveData.setValue(new Pair<>(null, getErrorMessage(response.errorBody().string())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                followMutableLiveData.setValue(null);
            }
        });
    }

}