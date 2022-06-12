package com.example.dayout.models.trip;

import java.io.Serializable;
import java.util.List;

public class TripPaginationData implements Serializable {

    int current_page;
    public List<TripData> data;
}
