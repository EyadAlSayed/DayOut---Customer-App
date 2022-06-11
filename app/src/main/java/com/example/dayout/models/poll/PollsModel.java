package com.example.dayout.models.poll;

import com.example.dayout.models.profile.ProfileData;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PollsModel implements Serializable {

    public List<PollsData> data;
}
