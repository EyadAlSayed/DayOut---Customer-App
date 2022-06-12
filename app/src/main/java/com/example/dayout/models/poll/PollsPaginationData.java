package com.example.dayout.models.poll;

import com.example.dayout.models.profile.ProfileData;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PollsPaginationData implements Serializable {

    int current_page;
    public List<PollsData> data;
}
