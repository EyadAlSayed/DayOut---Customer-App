package com.example.dayout.models.profile;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.dayout.models.room.profileRoom.converter.ProfileDataConverter;

import java.io.Serializable;

import static com.example.dayout.config.AppConstants.PROFILE_TABLE;

@Entity(tableName = PROFILE_TABLE)
public class ProfileModel implements Serializable {

    public boolean success;
    public String message;

    @TypeConverters(ProfileDataConverter.class)
    public ProfileData data;

    @PrimaryKey(autoGenerate = true)
    int modelId;

}
