package com.example.dayout.models.trip;

import com.example.dayout.models.profile.ProfileData;

import java.io.Serializable;

public class CustomerTrip implements Serializable {
    public int id;
    public int customer_id;
    public int trip_id;
    public int checkout;
    public float rate;
    public ProfileData user = new ProfileData();
}
