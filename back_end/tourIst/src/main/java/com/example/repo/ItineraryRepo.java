package com.example.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.model.*;

public interface ItineraryRepo extends MongoRepository<Itinerary, String>{
	public Itinerary findByName(String name);

}
