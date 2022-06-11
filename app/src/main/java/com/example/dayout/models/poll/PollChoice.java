package com.example.dayout.models.poll;

import com.example.dayout.models.profile.ProfileData;

import java.util.List;

public class PollChoice {
    public int id;
    public String value;
    public int poll_id;
    public List<ProfileData> users;
}
