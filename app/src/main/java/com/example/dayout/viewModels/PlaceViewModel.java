package com.example.dayout.viewModels;

import android.util.Pair;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dayout.api.ApiClient;
import com.example.dayout.models.popualrPlace.PopularPlaceModel;
import com.example.dayout.models.trip.PlaceDetailsModel;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.dayout.api.ApiClient.BASE_URL;
import static com.example.dayout.config.AppConstants.getErrorMessage;
import static com.example.dayout.config.AppSharedPreferences.GET_USER_ID;

public class PlaceViewModel extends ViewModel {


    private static final String TAG = "PlaceViewModel";
    private final ApiClient apiClient = new ApiClient();

    private static PlaceViewModel instance;

    public static PlaceViewModel getINSTANCE() {
        if (instance == null) {
            instance = new PlaceViewModel();
        }
        return instance;
    }

    public static final String  PLACE_PHOTO_URL = BASE_URL + "storage/places/";

    public MutableLiveData<Pair<PopularPlaceModel, String>> popularMutableLiveData;
    public MutableLiveData<Pair<PlaceDetailsModel, String>> placeDetailsMutableLiveData;

    public MutableLiveData<Pair<Boolean, String>> successfulMutableLiveData;

    public void getPopularPlaces() {
        popularMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().getPopularPlaces(GET_USER_ID()).enqueue(new Callback<PopularPlaceModel>() {
            @Override
            public void onResponse(Call<PopularPlaceModel> call, Response<PopularPlaceModel> response) {
                if (response.isSuccessful()) {
                    popularMutableLiveData.setValue(new Pair<>(response.body(), null));
                } else {
                    try {
                        popularMutableLiveData.setValue(new Pair<>(null, getErrorMessage(response.errorBody().string())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<PopularPlaceModel> call, Throwable t) {
                popularMutableLiveData.setValue(null);
            }
        });
    }

    public void addToFavorite(JsonObject jsonObject) {
        successfulMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().addToFavorite(jsonObject).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    successfulMutableLiveData.setValue(new Pair<>(true, null));
                } else {
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

    public void getPlaceDetails(int placeId){
        placeDetailsMutableLiveData = new MutableLiveData<>();
        apiClient.getAPI().getPlaceDetails(placeId).enqueue(new Callback<PlaceDetailsModel>() {
            @Override
            public void onResponse(Call<PlaceDetailsModel> call, Response<PlaceDetailsModel> response) {
                if (response.isSuccessful()){
                    placeDetailsMutableLiveData.setValue(new Pair<>(response.body(),null));
                }
                else {
                    try {
                        placeDetailsMutableLiveData.setValue(new Pair<>(null, getErrorMessage(response.errorBody().string())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<PlaceDetailsModel> call, Throwable t) {
                placeDetailsMutableLiveData.setValue(null);
            }
        });
    }
}
