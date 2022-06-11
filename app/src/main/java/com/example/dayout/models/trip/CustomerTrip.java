package com.example.dayout.models.trip;

import com.example.dayout.models.profile.ProfileData;

public class CustomerTrip {
    public int id;
    public int customer_id;
    public int trip_id;
    public int checkout;
    public float rate;
    public String created_at;
    public String updated_at;
    public ProfileData user = new ProfileData();
}
