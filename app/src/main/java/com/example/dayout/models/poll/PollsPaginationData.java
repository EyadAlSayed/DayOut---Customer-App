package com.example.dayout.models.poll;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.dayout.models.profile.ProfileData;
import com.example.dayout.models.room.pollsRoom.converters.PollsDataConverter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import static com.example.dayout.config.AppConstants.POLL_PAGINATION_DATA;

@Entity(tableName = POLL_PAGINATION_DATA)
public class PollsPaginationData implements Serializable {

    int current_page;
    public String next_page_url;

    @TypeConverters(PollsDataConverter.class)
    public List<PollsData> data;

    @PrimaryKey(autoGenerate = true)
    int id;
}
