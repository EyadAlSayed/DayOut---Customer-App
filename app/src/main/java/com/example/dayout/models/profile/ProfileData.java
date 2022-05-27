package com.example.dayout.models.profile;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


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
    public int customer_trip_count;
    public int organizer_follow_count;

    public boolean hasPhoto;
}
