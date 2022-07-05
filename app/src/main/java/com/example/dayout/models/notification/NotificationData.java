package com.example.dayout.models.notification;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static com.example.dayout.config.AppConstants.NOTIFICATIONS_DATA;

@Entity(tableName = NOTIFICATIONS_DATA)
public class NotificationData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String body;
    public int user_id;
}
