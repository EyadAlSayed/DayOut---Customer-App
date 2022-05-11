//package com.example.dayout.models.room.databases;
//
//import androidx.room.TypeConverter;
//
//import com.example.dayout.models.PopualrPlace.PopularPlace;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.io.Serializable;
//import java.lang.reflect.Type;
//import java.util.List;
//
//public class PhotoConverter implements Serializable {
//
//    @TypeConverter
//    public String fromSpecList(List<PopularPlacePhoto> specializationList) {
//
//        if (specializationList == null)
//            return null;
//
//        Type type = new TypeToken<List<PopularPlacePhoto>>() {
//        }.getType();
//        Gson gson = new Gson();
//
//        String json = gson.toJson(specializationList, type);
//        return json;
//    }
//
//
//    @TypeConverter
//    public List<PopularPlacePhoto> toSpecList(String sspecList) {
//
//
//        if (sspecList == null)
//            return null;
//
//        Type type = new TypeToken<List<PopularPlacePhoto>>() {
//        }.getType();
//        Gson gson = new Gson();
//
//        return gson.fromJson(sspecList, type);
//
//    }
//
//
//    @TypeConverter
//    public String fromSickList(List<String> sickList) {
//
//        if (sickList == null)
//            return null;
//
//        Type type = new TypeToken<List<String>>() {
//        }.getType();
//        Gson gson = new Gson();
//
//        return gson.toJson(sickList, type);
//    }
//
//
//    @TypeConverter
//    public List<String> toSickList(String ssickList) {
//
//
//        if (ssickList == null)
//            return null;
//
//        Type type = new TypeToken<List<String>>() {
//        }.getType();
//        Gson gson = new Gson();
//
//        return gson.fromJson(ssickList, type);
//
//    }
//
//
//    @TypeConverter
//    public String fromDate(PopularPlacePhoto date) {
//        return date.toString();
//    }
//
//
//}
//
//
//
