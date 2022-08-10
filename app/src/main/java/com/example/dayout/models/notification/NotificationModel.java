package com.example.dayout.models.notification;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.dayout.models.room.notificationsRoom.converters.NotificationDataConverter;

import java.util.ArrayList;

import static com.example.dayout.config.AppConstants.NOTIFICATIONS_TABLE;

@Entity(tableName = NOTIFICATIONS_TABLE)
public class NotificationModel {

    @TypeConverters(NotificationDataConverter.class)
    public ArrayList<NotificationData> data = new ArrayList<>();

    @PrimaryKey(autoGenerate = true)
    int modelId;
}
