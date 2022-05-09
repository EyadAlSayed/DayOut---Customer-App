package com.example.dayout.models;

import java.util.ArrayList;

public class ProfileModel {

    public boolean success;
    public String message;
    public ArrayList<Data> data;

    public class Data{
        public int id;
        public String first_name;
        public String last_name;
        public String email;
        public String phone_number;
        public String photo;
        public String gender;
        public Object mobile_token;
        public String verified_at;
        public String created_at;
        public String updated_at;
        public int customer_trip_count;
        public int organizer_follow_count;
    }

}
