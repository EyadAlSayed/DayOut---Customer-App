package com.example.dayout.models.poll;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.dayout.models.room.pollsRoom.converters.PollsChoiceConverter;

import java.io.Serializable;
import java.util.List;

import static com.example.dayout.config.AppConstants.POLL_DATA;

@Entity(tableName = POLL_DATA)
public class PollsData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String description;
    public int organizer_id;

    @TypeConverters(PollsChoiceConverter.class)
    public List<PollChoice> poll_choices;
}
