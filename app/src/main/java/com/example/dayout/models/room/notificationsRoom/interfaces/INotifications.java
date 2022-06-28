package com.example.dayout.models.room.notificationsRoom.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dayout.models.notification.NotificationData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

import static com.example.dayout.config.AppConstants.NOTIFICATIONS_DATA;

@Dao
public interface INotifications {

    @Insert
    Completable insertNotification(NotificationData notification);


    @Query("select * from " + NOTIFICATIONS_DATA)
    Single<List<NotificationData>> getNotifications();
}
