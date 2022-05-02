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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getCustomer_trip_count() {
        return customer_trip_count;
    }

    public void setCustomer_trip_count(int customer_trip_count) {
        this.customer_trip_count = customer_trip_count;
    }

    public int getOrganizer_follow_count() {
        return organizer_follow_count;
    }

    public void setOrganizer_follow_count(int organizer_follow_count) {
        this.organizer_follow_count = organizer_follow_count;
    }
}