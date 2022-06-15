package com.example.dayout.models.profile.organizer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class ProfileUser {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String first_name;
    public String last_name;
    public String photo;
    public String gender;
    public String phone_number;
    public String email;
    public String confirm_at;
    public String created_at;
    public String updated_at;
}
