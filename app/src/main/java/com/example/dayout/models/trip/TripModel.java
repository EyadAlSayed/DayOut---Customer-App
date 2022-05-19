package com.example.dayout.models.trip;

import com.example.dayout.models.popualrPlace.PopularPlacePhoto;

import java.util.ArrayList;

public class TripModel {

    public String message;
    public String succeed;

    public ArrayList<Data> data = new ArrayList<>();

    public class Data{
        public int id;
        public String title;
        public int organizer_id;

//    1 : available
//    2 : ended
//    3 : started
//    4 : closed
        public int trip_status_id;
        public String description;
        public String begin_date;
        public String expire_date;
        public String end_booking;
        public int price;
        public int customer_trips_count;
        public ArrayList<PlaceTrip> place_trips = new ArrayList<>();
        public ArrayList<PopularPlacePhoto> trip_photos = new ArrayList<>();

        public boolean isActive = false;
        public String stopsToDetails;
    }

    public class Place{
        public int id;
        public String name;
        public String address;
        public String summary;
        public String description;
        public Object deleted_at;
        public String created_at;
        public String updated_at;
        public int type_id;
    }

    public class PlaceTrip{
        public int id;
        public int place_id;
        public int trip_id;
        public int order;
        public String description;
        public Object deleted_at;
        public String created_at;
        public String updated_at;
        public Place place = new Place();
    }


    public class TripPhoto{
        public int id;
        public int trip_id;
    }
}
