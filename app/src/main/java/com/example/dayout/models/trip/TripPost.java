package com.example.dayout.models.trip;

import java.io.Serializable;
import java.util.List;

public class TripPost implements Serializable {

    public boolean success;
    public String message;
    public List<TripData> data;

}
