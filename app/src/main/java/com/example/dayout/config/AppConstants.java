package com.example.dayout.config;

import android.util.Log;
import android.util.Patterns;

import com.example.dayout.R;
import com.example.dayout.models.error.ErrorModel;
import com.google.gson.Gson;

import java.util.regex.Pattern;

public class AppConstants {

    public final static int AUTH_FRC = R.id.auth_fr_c;
    public final static int MAIN_FRC = R.id.main_fr_c;

    public final static Pattern NAME_REGEX = Pattern.compile("[a-zA-Z]([a-zA-Z]+| )*");
    public final static Pattern PHONE_NUMBER_REGEX = Pattern.compile("09\\d{8}");
    public final static Pattern EMAIL_REGEX = Patterns.EMAIL_ADDRESS;


    // shared preferences keys

    public static final String USER_ID = "user_id";
    public static final String ACC_TOKEN = "acc_token";
    public static final String REMEMBER_ME = "remember_me";
    public static final String LAN = "lan";


    // const function

    public static String getErrorMessage(String errorAsString) {
        Log.e("error", "getErrorMessage: "+errorAsString );
        try {
            Gson gson = new Gson();
            ErrorModel errorModel = gson.fromJson(errorAsString, ErrorModel.class);
            return errorModel.getMessage();
        } catch (Exception e) {
            return "Failed while reading the error message";
        }
    }


    // room keys

    // popular place keys
    public static final String POPULAR_PLACE_DB = "popularplace_database";
    public static final String POPULAR_PLACE_TABLE = "popularplace_table";
    public static final String POPULAR_PLACE_DATA = "popularplace_data";
    public static final String POPULAR_PLACE_PHOTO = "popularplace_photo";

    // profile keys
    public static final String PROFILE_DB = "profile_database";
    public static final String PROFILE_TABLE = "profile_table";
    public static final String PROFILE_DATA = "profile_data";

    //polls keys
    public static final String POLL_DB = "poll_database";
    public static final String POLL_TABLE = "poll_table";
    public static final String POLL_PAGINATION_DATA = "poll_pagination_data";
    public static final String POLL_DATA = "poll_data";
    public static final String POLL_CHOICE = "poll_choice";

    //trip keys
    public static final String TRIP_DB = "trip_database";
    public static final String TRIP_TABLE = "trip_table";
    public static final String TRIP_PAGINATION_DATA = "trip_pagination_data";
    public static final String TRIP_DATA = "trip_data";
    public static final String TRIP_TYPE = "trip_type";
    public static final String PLACE_TRIP_DATA = "place_trip_data";
    public static final String TRIP_PHOTO_DATA = "trip_photo_data";
    public static final String CUSTOMER_TRIP = "customer_trip";

    //notifications keys
    public static final String NOTIFICATIONS_DB = "notifications_database";
    public static final String NOTIFICATIONS_TABLE = "notifications_table";
    public static final String NOTIFICATIONS_DATA = "notifications_data";

    //road map keys
    public static final String ROAD_MAP_DB = "road_map_database";
    public static final String ROAD_MAP_TABLE = "road_map_table";
    public static final String ROAD_MAP_DATA = "road_map_data";

    //organizers keys
    public static final String ORGANIZERS_DB = "organizers_database";
    public static final String ORGANIZERS_TABLE = "organizers_table";
    public static final String ORGANIZERS_DATA = "organizers_data";
    public static final String PROFILE_USER = "profile_user";

    //place keys
    public static final String PLACES_DB = "places_database";
    public static final String PLACES_TABLE = "places_table";
}
