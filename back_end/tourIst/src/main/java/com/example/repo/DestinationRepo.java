package com.example.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.model.*;

public interface DestinationRepo extends MongoRepository<Destination, String>{
    public Destination findDestinationById(String id);
	
	public List<Destination> findByName(String name);
	public List<Destination> findByNameContainsIgnoreCase(String name);
    public List<Destination> findByCategory(Category category);
    

}
