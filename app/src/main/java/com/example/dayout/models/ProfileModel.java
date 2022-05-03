package com.example.dayout.models;

import java.io.Serializable;

public class ProfileModel implements Serializable {

    public String message;

    public int id;
    public String first_name;
    public String last_name;
    public String photo;
    public String gender;
    public String phone_number;
    public int customer_trip_count;
    public int organizer_follow_count;

    public String password;
    public String email;
}