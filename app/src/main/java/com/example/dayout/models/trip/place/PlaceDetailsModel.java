package com.example.dayout.models.trip.place;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.dayout.models.popualrPlace.PlaceData;
import com.example.dayout.models.room.placeRoom.converters.PlaceDataConverter;

import java.io.Serializable;

import static com.example.dayout.config.AppConstants.PLACES_TABLE;

@Entity(tableName = PLACES_TABLE)
public class PlaceDetailsModel implements Serializable {

    @TypeConverters(PlaceDataConverter.class)
    public PlaceData data;

    @PrimaryKey(autoGenerate = true)
    int modelId;
}
