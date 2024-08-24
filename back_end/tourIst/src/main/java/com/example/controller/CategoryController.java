package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Category;
import com.example.repo.CategoryRepo;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired CategoryRepo categoryRepo;
	
	@GetMapping("/list")
	public List<Category> getAllCategories() {
		return categoryRepo.findAll();
	} 
	
}
