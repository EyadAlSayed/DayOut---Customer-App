package com.example.dayout.models.trip;

import java.io.Serializable;
import java.util.List;

public class TripPost implements Serializable {

    public class Data{
        public String first_page_url;
        public int from;
        public int last_page;
        public String last_page_url;
        public List<Link> links;
        public Object next_page_url;
        public String path;
        public int per_page;
        public Object prev_page_url;
        public int to;
        public int total;
        public int id;
        public String title;
        public int organizer_id;
        public int trip_status_id;
        public String description;
        public String begin_date;
        public String expire_date;
        public String end_booking;
        public int price;
        public String status;
        public List<TripModel.PlaceTrip> place_trips;
        public List<TripModel.TripPhoto> trip_photos;
    }

    public class Link{
        public String url;
        public String label;
        public boolean active;
    }

    public class Data1{
        public int current_page;
        public List<Data> data;
    }

    public boolean success;
    public String message;
    public Data1 data;




}
