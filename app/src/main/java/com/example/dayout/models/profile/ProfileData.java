package com.example.dayout.models.profile;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;


import com.example.dayout.models.profile.organizer.ProfileUser;
import com.example.dayout.models.room.profileRoom.converter.ProfileDataConverter;
import com.example.dayout.models.room.profileRoom.converter.ProfileUserConverter;

import java.io.Serializable;

import static com.example.dayout.config.AppConstants.PROFILE_DATA;

@Entity(tableName = PROFILE_DATA)
public class ProfileData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String first_name;
    public String last_name;
    public String email;
    public String phone_number;
    public String photo;
    public String gender;
    public String mobile_token;
    public String password;
    public int customer_trip_count;
    public int organizer_follow_count;

    //organizer profile
    public int user_id;
    public String bio;
    public int trips_count;
    public int followers_count;
    public float rating;
    public boolean iFollowHim;
    @TypeConverters(ProfileUserConverter.class)
    public ProfileUser user;
}
