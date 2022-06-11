package com.example.dayout.models.trip;

import com.example.dayout.models.trip.place.PlaceTripData;
import com.example.dayout.models.trip.triptype.TripType;

import java.util.ArrayList;

public class TripData {
    public int id;
    public String title;
    public int organizer_id;
    public int trip_status_id;
    public String description;
    public String begin_date;
    public String expire_date;
    public String end_booking;
    public int price;
    public String created_at;
    public String updated_at;
    public int customer_trips_count;
    public ArrayList<TripType> types = new ArrayList<>();
    public ArrayList<PlaceTripData> place_trips = new ArrayList<>();
    public ArrayList<TripPhotoData> trip_photos = new ArrayList<>();
    public ArrayList<CustomerTrip> customer_trips = new ArrayList<>();

    public boolean  isActive = false;
    public String stopsToDetails;
}
