package com.example.dayout.models.profile.organizer;

import com.example.dayout.models.profile.ProfileData;

import java.util.ArrayList;

public class OrganizerProfileData {

    public int current_page;
    public ArrayList<ProfileData> data;
    public String first_page_url;
    public int from;
    public int last_page;
    public String last_page_url;
    public ArrayList<Link> links;
    public Object next_page_url;
    public String path;
    public int per_page;
    public Object prev_page_url;
    //@JsonProperty("to")
    public int myto;
    public int total;
    public int id;
    public int user_id;
    public String credential_photo;
    public String created_at;
    public String updated_at;
    public String bio;
    public ProfileUser user;

    public class Link{
        public String url;
        public String label;
        public boolean active;
    }
}