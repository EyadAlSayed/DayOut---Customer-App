package com.example.dayout.models.popualrPlace;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.dayout.models.room.popularPlaceRoom.converters.PhotoConverter;

import java.io.Serializable;
import java.util.List;

import static com.example.dayout.config.AppConstants.POPULAR_PLACE_DATA;
import static com.example.dayout.config.AppConstants.POPULAR_PLACE_TABLE;

@Entity(tableName = POPULAR_PLACE_DATA)
public class PlaceData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String address;
    public String summary;
    public String description;

    @TypeConverters(PhotoConverter.class)
    public List<PopularPlacePhoto> photos;
    public int type_id;
    public boolean status;


    public int favorites_count;
    public int place_trips_count;

    public boolean isFavorite = false;
}
