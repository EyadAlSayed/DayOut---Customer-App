package com.example.dayout.models.room.pollsRoom.Interfaces;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dayout.models.poll.PollsData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

import static com.example.dayout.config.AppConstants.POLL_DATA;


@Dao
public interface IPolls {


    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    Completable insertPoll(PollsData pollDB);


    @Query("select * from " + POLL_DATA)
    Single<List<PollsData>> getPolls();

    @Query("delete from "+POLL_DATA)
    Single<Void> deleteAll();
}
