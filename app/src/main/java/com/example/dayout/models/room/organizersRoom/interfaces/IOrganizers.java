package com.example.dayout.models.room.organizersRoom.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dayout.models.profile.ProfileData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

import static com.example.dayout.config.AppConstants.NOTIFICATIONS_DATA;
import static com.example.dayout.config.AppConstants.PROFILE_DATA;

@Dao
public interface IOrganizers {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertOrganizer(ProfileData organizer);

    @Query("select * from " + PROFILE_DATA + " where isOrganizer = 1")
    Single<List<ProfileData>> getOrganizers();

    @Query("select * from " + PROFILE_DATA + " where isOrganizer = 1 AND iFollowHim = 1")
    Single<List<ProfileData>> getFollowedOrganizers();

    @Query("delete from "+PROFILE_DATA)
    Single<Void> deleteAll();
}
