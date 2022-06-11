package com.example.dayout.models.poll;

import java.io.Serializable;
import java.util.List;

public class PollsData implements Serializable {
    public int id;
    public String title;
    public String description;
    public int organizer_id;
    public List<PollChoice> poll_choices;
}
