package com.example.dayout.models.trip;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.dayout.models.profile.ProfileData;
import com.example.dayout.models.room.profileRoom.converter.ProfileDataConverter;

import java.io.Serializable;

import static com.example.dayout.config.AppConstants.CUSTOMER_TRIP;

@Entity(tableName = CUSTOMER_TRIP)
public class CustomerTrip implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int customer_id;
    public int trip_id;
    public int checkout;
    public float rate;
    @TypeConverters(ProfileDataConverter.class)
    public ProfileData user = new ProfileData();
}
