package com.example.dayout.models.room.organizersRoom.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dayout.models.profile.ProfileData;
import com.example.dayout.models.profile.organizer.OrganizerProfileData;
import com.example.dayout.models.profile.organizer.OrganizersModel;
import com.example.dayout.models.profile.organizer.ProfileUser;
import com.example.dayout.models.room.organizersRoom.interfaces.IOrganizers;

import static com.example.dayout.config.AppConstants.ORGANIZERS_DB;

@Database(entities = {OrganizersModel.class, OrganizerProfileData.class, ProfileData.class, ProfileUser.class}, exportSchema = false
        ,version = 1)
abstract public class OrganizersDatabase extends RoomDatabase {

    private static OrganizersDatabase instance;

    public abstract IOrganizers iOrganizers();


    public static  synchronized OrganizersDatabase getINSTANCE(Context context){
        if (instance == null){
            instance = Room.
                    databaseBuilder(context.getApplicationContext()
                    , OrganizersDatabase.class, ORGANIZERS_DB)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;

    }
}
