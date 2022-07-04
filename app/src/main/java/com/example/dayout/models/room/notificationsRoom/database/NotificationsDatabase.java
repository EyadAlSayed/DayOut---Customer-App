package com.example.dayout.models.room.notificationsRoom.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dayout.models.notification.NotificationData;
import com.example.dayout.models.notification.NotificationModel;
import com.example.dayout.models.room.notificationsRoom.interfaces.INotifications;

import static com.example.dayout.config.AppConstants.NOTIFICATIONS_DB;

@Database(entities = {NotificationModel.class, NotificationData.class}, exportSchema = false, version = 1)
abstract public class NotificationsDatabase extends RoomDatabase {

    private static NotificationsDatabase instance;

    public abstract INotifications iNotifications();

    public static synchronized NotificationsDatabase getINSTANCE(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext()
                        ,NotificationsDatabase.class, NOTIFICATIONS_DB)
                        .fallbackToDestructiveMigration()
                        .build();
        }
        return instance;
    }
}
