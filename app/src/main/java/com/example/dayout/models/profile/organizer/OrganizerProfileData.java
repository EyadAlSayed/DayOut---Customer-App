package com.example.dayout.models.profile.organizer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.dayout.models.profile.ProfileData;
import com.example.dayout.models.room.profileRoom.converter.ProfileDataConverter;
import com.example.dayout.models.room.profileRoom.converter.ProfileDataListConverter;

import java.util.ArrayList;

import static com.example.dayout.config.AppConstants.ORGANIZERS_DATA;

@Entity(tableName = ORGANIZERS_DATA)
public class OrganizerProfileData {

    public int current_page;
    @TypeConverters(ProfileDataListConverter.class)
    public ArrayList<ProfileData> data;
    public String next_page_url;
    public String prev_page_url;
    public int total;

    @PrimaryKey(autoGenerate = true)
    int id;
}