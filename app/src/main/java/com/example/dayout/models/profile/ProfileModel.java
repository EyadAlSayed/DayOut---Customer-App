package com.example.dayout.models.profile;

public class ProfileModel {

    public boolean success;
    public String message;
    public Data data;

    public class Data{
        public int id;
        public String first_name;
        public String last_name;
        public String email;
        public String phone_number;
        public String photo;
        public String gender;
        public String mobile_token;
        public int customer_trip_count;
        public int organizer_follow_count;
    }

}
