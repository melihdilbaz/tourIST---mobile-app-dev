package com.example.tourist;

import com.example.tourist.Destination;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Itinerary {
    private String id;
    private String name;

    private List<Destination> list = new ArrayList<>();

    public Itinerary() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Itinerary(String id, String name, List<Destination> list) {
        super();
        this.id = id;
        this.name = name;
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Destination> getList() {
        return list;
    }

    public void setList(Destination dest) {
        this.list.add(dest);
    }


}
