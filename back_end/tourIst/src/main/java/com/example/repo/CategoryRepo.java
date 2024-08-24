package com.example.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.model.*;

public interface CategoryRepo extends MongoRepository<Category, String>{
	// findByAll : by default
	// findById(String id) : by default
	
	public List<Category> findByName(String name);
	
	public List<Category> findByNameContainsIgnoreCase(String name);
	
}
