package com.example.dayout.models.trip.triptype;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TripType implements Serializable {

    public int id;
    public String name;

    public TripType(int id, String name) {
        this.id = id;
        this.name = name;
    }


}
