package com.example.dayout.models.trip;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.dayout.models.room.tripsRoom.converters.CustomerTripConverter;
import com.example.dayout.models.room.tripsRoom.converters.PlaceTripDataConverter;
import com.example.dayout.models.room.tripsRoom.converters.PlaceTripDataListConverter;
import com.example.dayout.models.room.tripsRoom.converters.TripPhotoDataConverter;
import com.example.dayout.models.room.tripsRoom.converters.TripTypeConverter;
import com.example.dayout.models.trip.place.PlaceTripData;
import com.example.dayout.models.trip.tripType.TripType;

import java.util.ArrayList;

import static com.example.dayout.config.AppConstants.TRIP_DATA;

@Entity(tableName = TRIP_DATA)
public class TripData {
    @PrimaryKey(autoGenerate = true)
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
    public boolean is_in_trip;
    @TypeConverters(TripTypeConverter.class)
    public ArrayList<TripType> types = new ArrayList<>();
    @TypeConverters(PlaceTripDataListConverter.class)
    public ArrayList<PlaceTripData> place_trips = new ArrayList<>();
    @TypeConverters(TripPhotoDataConverter.class)
    public ArrayList<TripPhotoData> trip_photos = new ArrayList<>();
    @TypeConverters(CustomerTripConverter.class)
    public ArrayList<CustomerTrip> customer_trips = new ArrayList<>();

    public boolean  isActive = false;
    public String stopsToDetails;
}
