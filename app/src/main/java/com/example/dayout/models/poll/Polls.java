package com.example.dayout.models.poll;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Polls implements Serializable {
    public class Data {
        public int id;
        public String title;
        public String description;
        public int organizer_id;
        public Organizer organizer;
        public List<PollChoice> poll_choices;
    }

    public class Link{
        public String url;
        public String label;
        public boolean active;
    }

    public class Organizer{
        public int id;
        public int user_id;
        public String credential_photo;
        public String bio;
    }

    public class PollChoice{
        public int id;
        public String value;
        public int poll_id;
        public List<User> users;
    }

    public int current_page;
    public List<Data> data;
    public String first_page_url;
    public int from;
    public int last_page;
    public String last_page_url;
    public List<Link> links;
    public Object next_page_url;
    public String path;
    public int per_page;
    public Object prev_page_url;
    @SerializedName("to")
    public int myto;
    public int total;

    public class User{
        public int id;
        public String first_name;
        public String last_name;
        public Object email;
        public String phone_number;
        public Object photo;
        public String gender;
        public Object mobile_token;
        public Object verified_at;
    }

}
