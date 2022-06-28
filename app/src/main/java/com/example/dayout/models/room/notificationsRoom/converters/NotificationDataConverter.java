package com.example.dayout.models.room.notificationsRoom.converters;

import androidx.room.TypeConverter;

import com.example.dayout.models.notification.NotificationData;
import com.example.dayout.models.poll.PollChoice;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class NotificationDataConverter implements Serializable {

    @TypeConverter
    public String fromNotification(NotificationData notificationData) {

        if (notificationData == null)
            return null;

        Type type = new TypeToken<NotificationData>() {
        }.getType();
        Gson gson = new Gson();

        return gson.toJson(notificationData, type);
    }


    @TypeConverter
    public NotificationData toNotification(String data) {


        if (data == null)
            return null;

        Type type = new TypeToken<List<NotificationData>>() {
        }.getType();
        Gson gson = new Gson();

        return gson.fromJson(data, type);

    }
}
