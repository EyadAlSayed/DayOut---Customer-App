package com.example.dayout.models.room.pollsRoom.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dayout.models.poll.PollChoice;
import com.example.dayout.models.poll.PollsData;
import com.example.dayout.models.poll.PollsPaginationData;
import com.example.dayout.models.poll.PollsPaginationModel;
import com.example.dayout.models.room.pollsRoom.Interfaces.IPolls;

import static com.example.dayout.config.AppConstants.POLL_DB;

@Database(entities = {PollsPaginationModel.class, PollsPaginationData.class, PollsData.class, PollChoice.class}, version = 1)
abstract public class PollsDatabase extends RoomDatabase {

    private static PollsDatabase instance;

    public abstract IPolls iPolls();

    public static  synchronized PollsDatabase getINSTANCE(Context context){
        if (instance == null){
            instance = Room.
                    databaseBuilder(context.getApplicationContext()
                            ,PollsDatabase.class, POLL_DB)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
