package com.example.dayout.models.trip;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Type implements Serializable {

    public class Data {
        public int id;
        public String name;
    }

    public boolean success;
    public String message;
    public List<Data> data;

    public List<String> getDataName(){
        List<String> names = new ArrayList<>();
        for (Data d : data){
            names.add(d.name);
        }
        return names;
    }
}
