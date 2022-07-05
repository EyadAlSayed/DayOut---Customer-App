package com.example.dayout.models.trip;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.dayout.models.room.tripsRoom.converters.TripPaginationDataConverter;

import java.io.Serializable;

import static com.example.dayout.config.AppConstants.TRIP_TABLE;

@Entity(tableName = TRIP_TABLE)
public class TripPaginationModel  implements Serializable {

    @TypeConverters(TripPaginationDataConverter.class)
    public TripPaginationData data;

    @PrimaryKey(autoGenerate = true)
    int modelId;
}
