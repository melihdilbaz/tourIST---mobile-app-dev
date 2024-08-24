package com.example.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Itinerary {
	@Id private String id;
	@Indexed(unique = true)
	private String name;
	
	@DBRef
	private List<Destination> list = new ArrayList<>();

	public Itinerary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Itinerary(String name) {
		super();
		this.name = name;
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
