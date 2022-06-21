package com.example.dayout.models.room.pollsRoom.Interface;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dayout.models.poll.PollsData;
import com.example.dayout.models.popualrPlace.PlaceData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

import static com.example.dayout.config.AppConstants.POLL_DATA;
import static com.example.dayout.config.AppConstants.POPULAR_PLACE_DATA;


@Dao
public interface IPolls {


    @Insert
    Completable insertPoll(PollsData pollDB);


    @Query("select * from " + POLL_DATA)
    Single<List<PollsData>> getPolls();
}
