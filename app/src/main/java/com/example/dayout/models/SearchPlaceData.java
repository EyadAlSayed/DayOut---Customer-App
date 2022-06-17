package com.example.dayout.models;

import com.example.dayout.models.popualrPlace.PlaceData;

import java.util.List;

public class SearchPlaceData {

    public int current_page;
    public List<PlaceData> data;
    public String next_page_url;
    public String prev_page_url;
    public int total;
}
