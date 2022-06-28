package com.example.dayout.models.trip.roadMap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.dayout.models.room.tripsRoom.converters.PlaceTripDataConverter;
import com.example.dayout.models.trip.place.PlaceTripData;

import java.io.Serializable;
import java.util.List;

import static com.example.dayout.config.AppConstants.ROAD_MAP_DATA;

@Entity(tableName = ROAD_MAP_DATA)
public class RoadMapData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public int organizer_id;
    public int trip_status_id;
    public String description;
    public String begin_date;
    public String expire_date;
    public String end_booking;
    public int price;

    @TypeConverters(PlaceTripDataConverter.class)
    public List<PlaceTripData> place_trips;
}
