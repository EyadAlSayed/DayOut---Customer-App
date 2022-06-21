package com.example.dayout.models.poll;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.dayout.models.room.pollsRoom.converters.PollsPaginationDataConverter;

import java.io.Serializable;

import static com.example.dayout.config.AppConstants.POLL_TABLE;

@Entity(tableName = POLL_TABLE)
public class PollsPaginationModel implements Serializable {

    @TypeConverters(PollsPaginationDataConverter.class)
    public PollsPaginationData data;

    @PrimaryKey(autoGenerate = true)
    int modelId;
}
