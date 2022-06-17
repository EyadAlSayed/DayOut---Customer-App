package com.example.dayout.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ToDeleteModel  {

    public class Data {
        public int id;
        public String title;
        public int organizer_id;
        public int trip_status_id;
        public String description;
        public String begin_date;
        public String expire_date;
        public String end_booking;
        public int price;
       
   
        public int customer_trips_count;
        public List<PlaceTrip> place_trips;
        public List<Type> types;
        public List<TripPhoto> trip_photos;
    }

    public class Link{
        public String url;
        public String label;
        public boolean active;
    }

    public class Pivot{
        public int trip_id;
        public int type_id;
    }

    public class Place{
        public int id;
        public String name;
    }

    public class PlaceTrip{
        public int id;
        public int place_id;
        public int trip_id;
        public int order;
        public String description;
        public Place place;
    }

    public int current_page;
    public List<Data> data;
    public String first_page_url;
    public int from;
    public int last_page;
    public String last_page_url;
    public List<Link> links;
    public Object next_page_url;
    public String path;
    public int per_page;
    public Object prev_page_url;
    @SerializedName("to")
    public int myto;
    public int total;

    public class TripPhoto{
        public int id;
        public int trip_id;
        public String path;
    }

    public class Type{
        public int id;
        public String name;
        public Pivot pivot;
    }



}
