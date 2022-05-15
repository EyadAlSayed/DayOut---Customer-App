package com.example.dayout.models.room.profileRoom.interfaces;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.dayout.models.profile.ProfileData;

import io.reactivex.Single;

import static com.example.dayout.config.AppConstants.PROFILE_DATA;

@Dao
public interface IProfileModel {

    @Query("select * from " + PROFILE_DATA)
    Single<ProfileData> getProfile();
}
