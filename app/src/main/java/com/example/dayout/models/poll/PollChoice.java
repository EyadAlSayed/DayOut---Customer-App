package com.example.dayout.models.poll;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.dayout.models.profile.ProfileData;
import com.example.dayout.models.room.profileRoom.converter.ProfileDataConverter;
import com.example.dayout.models.room.profileRoom.converter.ProfileDataListConverter;

import java.util.List;

import static com.example.dayout.config.AppConstants.POLL_CHOICE;

@Entity(tableName = POLL_CHOICE)
public class PollChoice {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String value;
    public int poll_id;
    @TypeConverters(ProfileDataListConverter.class)
    public List<ProfileData> users;
}
