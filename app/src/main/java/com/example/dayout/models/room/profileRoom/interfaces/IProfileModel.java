package com.example.dayout.models.room.profileRoom.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dayout.models.popualrPlace.PopularPlaceData;
import com.example.dayout.models.profile.ProfileData;

import io.reactivex.Completable;
import io.reactivex.Single;

import static com.example.dayout.config.AppConstants.PROFILE_DATA;
import static com.example.dayout.config.AppSharedPreferences.GET_USER_ID;

@Dao
public interface IProfileModel {


    @Insert
    Completable insertProfile(ProfileData profileData);

    @Query("select * from " + PROFILE_DATA+" where id = :userId")
    Single<ProfileData> getProfile(int userId);
}
