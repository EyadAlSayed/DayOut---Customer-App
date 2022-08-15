package com.example.dayout.viewModels;

import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dayout.api.ApiClient;
import com.example.dayout.models.authModels.LoginModel;
import com.example.dayout.models.profile.ProfileData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.dayout.config.AppConstants.getErrorMessage;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    private final ApiClient apiClient = new ApiClient();
    private final Gson gson = new Gson();

    private static AuthViewModel instance;

    public static AuthViewModel getINSTANCE() {
        if (instance == null) {
            instance = new AuthViewModel();
        }
        return instance;
    }


    public MutableLiveData<Pair<LoginModel, String>> loginMutableLiveData;
    public MutableLiveData<Pair<ProfileData, String>> registerMutableLiveData;
    public MutableLiveData<Pair<Boolean,String>> successfulMutableLiveData;

    public void  checkPhoneNumberExist(JsonObject jsonObject){
        successfulMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().checkPhoneNumberExist(jsonObject).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    successfulMutableLiveData.setValue(new Pair<>(true,null));
                }
                else {
                    try {
                        successfulMutableLiveData.setValue(new Pair<>(null, getErrorMessage(response.errorBody().string())));
                    } catch (Exception e) {
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

    public void  resetPassword(JsonObject jsonObject){
        successfulMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().resetPassword(jsonObject).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    successfulMutableLiveData.setValue(new Pair<>(true,null));
                }
                else {
                    try {
                        successfulMutableLiveData.setValue(new Pair<>(null, getErrorMessage(response.errorBody().string())));
                    } catch (Exception e) {
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

    public void login(JsonObject jsonObject) {
        loginMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().login(jsonObject).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.isSuccessful()) {
                    loginMutableLiveData.setValue(new Pair<>(response.body(), null));
                } else {
                    try {
                        Log.d(TAG, "onResponse: " + response.errorBody().string());
                        loginMutableLiveData.setValue(new Pair<>(null, getErrorMessage(response.errorBody().string())));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                loginMutableLiveData.setValue(null);
            }
        });
    }

    public void registerPassenger(JsonObject model) {
        registerMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().registerPassenger(model).enqueue(new Callback<ProfileData>() {
            @Override
            public void onResponse(Call<ProfileData> call, Response<ProfileData> response) {
                if (response.isSuccessful()) {
                    registerMutableLiveData.setValue(new Pair<>(response.body(), null));
                } else {
                    try {
                        registerMutableLiveData.setValue(new Pair<>(null, getErrorMessage(response.errorBody().string())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileData> call, Throwable t) {
                registerMutableLiveData.setValue(null);
            }
        });
    }

}
