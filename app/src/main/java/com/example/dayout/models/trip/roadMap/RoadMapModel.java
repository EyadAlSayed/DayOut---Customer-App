package com.example.dayout.models.trip.roadMap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.dayout.models.room.roadMapRoom.converters.RoadMapDataConverter;

import java.io.Serializable;

import static com.example.dayout.config.AppConstants.ROAD_MAP_TABLE;

@Entity(tableName = ROAD_MAP_TABLE)
public class RoadMapModel implements Serializable {

    @TypeConverters(RoadMapDataConverter.class)
    public RoadMapData data;

    @PrimaryKey(autoGenerate = true)
    int modelId;

}
