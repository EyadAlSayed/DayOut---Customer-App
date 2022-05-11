//package com.example.dayout.models.room.models;
//
//import androidx.room.Entity;
//import androidx.room.PrimaryKey;
//import androidx.room.TypeConverters;
//
//import com.example.dayout.models.PopualrPlace.PopularPlace;
//import com.example.dayout.models.room.databases.DataConverter;
//
//import java.io.Serializable;
//
//@Entity(tableName = "photo_table")
//@TypeConverters(DataConverter.class)
//public class PhotoDB  implements Serializable {
//
//    @PrimaryKey(autoGenerate = true)
//    public int id;
//    public int place_id;
//    public String path;
//
//    public PhotoDB(int id, int place_id, String path) {
//        this.id = id;
//        this.place_id = place_id;
//        this.path = path;
//    }
//
//    public PopularPlace.Photo toModel(){
//        return new PopularPlace.Photo(id,path,path);
//    }
//}
