package com.example.dayout.models.profile.organizer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.dayout.models.profile.ProfileData;
import com.example.dayout.models.room.organizersRoom.converters.OrganizerProfileDataConverter;

import java.util.ArrayList;

import static com.example.dayout.config.AppConstants.ORGANIZERS_TABLE;

@Entity(tableName = ORGANIZERS_TABLE)
public class OrganizersModel {
    @TypeConverters(OrganizerProfileDataConverter.class)
    public OrganizerProfileData data;

    @PrimaryKey(autoGenerate = true)
    int modelId;
}