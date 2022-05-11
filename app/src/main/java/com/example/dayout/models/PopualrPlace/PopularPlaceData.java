//package com.example.dayout.models.PopualrPlace;
//
//import androidx.room.Entity;
//import androidx.room.PrimaryKey;
//import androidx.room.TypeConverters;
//
//import com.example.dayout.models.room.databases.PhotoConverter;
//
//import java.io.Serializable;
//import java.util.List;
//
//@Entity(tableName = "popularplace_data")
//public class PopularPlaceData implements Serializable {
//
//    @PrimaryKey(autoGenerate =  true)
//    public int id;
//    public String name;
//    public String address;
//    public String summary;
//    public String description;
//
//    @TypeConverters(PhotoConverter.class)
//    public List<PopularPlacePhoto> photos;
//    public int type_id;
//    public int place_trips_count;
//}
