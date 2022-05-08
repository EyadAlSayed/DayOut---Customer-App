package com.example.dayout.viewModels;

import android.util.Pair;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dayout.api.ApiClient;
import com.example.dayout.models.LoginModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    private final ApiClient apiClient = new ApiClient();

    private static AuthViewModel instance;

    public static AuthViewModel getINSTANCE() {
        if (instance == null) {
            instance = new AuthViewModel();
        }
        return instance;
    }


    public MutableLiveData<Pair<LoginModel, String>> loginMutableLiveData;


    public void login(JsonObject jsonObject){
        loginMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().login(jsonObject).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.isSuccessful()) {
                    loginMutableLiveData.setValue(new Pair<>(response.body(), null));
                } else {
                    ErrorBody errorBody = null;
                    try {
                        Log.e(TAG, "onResponse: login " + response.code());
                        Log.e(TAG, "onResponse:   " + response.errorBody().string());
                        errorBody = gson.fromJson(response.errorBody().string(), ErrorBody.class);
                        Pair<LoginModel, String> pair = new Pair<>(null, errorBody.message);
                        loginMutableLiveData.setValue(pair);
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

    public void registerPassenger(UserRegisterModel model){
        registerMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().registerPassenger(model).enqueue(new Callback<UserRegisterModel>() {
            @Override
            public void onResponse(Call<UserRegisterModel> call, Response<UserRegisterModel> response) {
                if(response.isSuccessful()){
                    registerMutableLiveData.setValue(new Pair<>(response.body(), null));
                } else{
                    System.out.println(response.message());
                    registerMutableLiveData.setValue(new Pair<>(null,response.message()));
                }
            }

            @Override
            public void onFailure(Call<UserRegisterModel> call, Throwable t) {
                registerMutableLiveData.setValue(null);
            }
        });
    }

}
