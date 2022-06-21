package com.example.dayout.models.room.pollsRoom.converters;

import androidx.room.TypeConverter;

import com.example.dayout.models.poll.PollChoice;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class PollsChoiceConverter implements Serializable {

    @TypeConverter
    public String fromChoice(PollChoice pollChoice) {

        if (pollChoice == null)
            return null;

        Type type = new TypeToken<PollChoice>() {
        }.getType();
        Gson gson = new Gson();

        return gson.toJson(pollChoice, type);
    }


    @TypeConverter
    public PollChoice toChoice(String data) {


        if (data == null)
            return null;

        Type type = new TypeToken<List<PollChoice>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.fromJson(data, type);

    }
}
