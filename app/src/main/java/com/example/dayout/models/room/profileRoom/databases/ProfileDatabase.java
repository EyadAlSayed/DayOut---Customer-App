package com.example.dayout.models.room.profileRoom.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.dayout.models.profile.ProfileData;
import com.example.dayout.models.profile.ProfileModel;
import com.example.dayout.models.room.profileRoom.interfaces.IProfileModel;

import static com.example.dayout.config.AppConstants.PROFILE_DB;

@Database(entities = {ProfileModel.class, ProfileData.class}, exportSchema = false, version = 1)
abstract public class ProfileDatabase extends RoomDatabase {

    private static ProfileDatabase instance;

    public abstract IProfileModel iProfileModel();

    public static synchronized ProfileDatabase getINSTANCE(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ProfileDatabase.class, PROFILE_DB)
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
