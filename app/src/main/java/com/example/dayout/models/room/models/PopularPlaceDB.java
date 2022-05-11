//package com.example.dayout.models.room.models;
//
//import java.io.Serializable;
//import androidx.room.ColumnInfo;
//import androidx.room.Entity;
//import androidx.room.Ignore;
//import androidx.room.PrimaryKey;
//import androidx.room.TypeConverters;
//
//import com.example.dayout.models.PopualrPlace.PopularPlace;
//import com.example.dayout.models.room.databases.DataConverter;
//import com.google.gson.annotations.SerializedName;
//
//import java.io.Serializable;
//import java.util.List;
//
//
//@Entity(tableName = "popularplace_table")
//@TypeConverters(DataConverter.class)
//public class PopularPlaceDB implements Serializable {
//
//    @PrimaryKey(autoGenerate =  true)
//    public int id;
//    public String name;
//    public String address;
//    public String summary;
//    public String description;
//    public List<PhotoDB> photos;
//    public int type_id;
//    public int place_trips_count;
//
//    public PopularPlaceDB(int id, String name, String address, String summary, String description, List<PhotoDB> photos, int type_id, int place_trips_count) {
//        this.id = id;
//        this.name = name;
//        this.address = address;
//        this.summary = summary;
//        this.description = description;
//        this.photos = photos;
//        this.type_id = type_id;
//        this.place_trips_count = place_trips_count;
//    }
//
////    public PopularPlace.Data toModel(){
////        return new PopularPlace.Data(id,name,address,summary,description,);
////    }
//}
