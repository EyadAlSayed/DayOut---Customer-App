package com.example.dayout.models.room.organizersRoom.converters;

import androidx.room.TypeConverter;

import com.example.dayout.models.notification.NotificationData;
import com.example.dayout.models.profile.organizer.OrganizerProfileData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class OrganizerProfileDataConverter implements Serializable {

    @TypeConverter
    public String fromOrganizer(OrganizerProfileData organizerProfileData) {

        if (organizerProfileData == null)
            return null;

        Type type = new TypeToken<OrganizerProfileData>() {
        }.getType();
        Gson gson = new Gson();

        return gson.toJson(organizerProfileData, type);
    }


    @TypeConverter
    public NotificationData toOrganizer(String data) {


        if (data == null)
            return null;

        Type type = new TypeToken<List<OrganizerProfileData>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.fromJson(data, type);

    }
}
