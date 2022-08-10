package com.example.dayout.models.trip;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.dayout.models.room.tripsRoom.converters.TripDataConverter;

import java.io.Serializable;
import java.util.List;

import static com.example.dayout.config.AppConstants.TRIP_PAGINATION_DATA;

@Entity(tableName = TRIP_PAGINATION_DATA)
public class TripPaginationData implements Serializable {

    int current_page;
    @TypeConverters(TripDataConverter.class)
    public List<TripData> data;
    public String next_page_url;
    public String prev_page_url;
    public int total;

    @PrimaryKey
    int id;
}
