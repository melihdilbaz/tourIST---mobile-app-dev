package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Category {
	@Id private String id;
	private String name;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Category(String categoryName) {
		super();
		this.name = categoryName;
	}

	public String getCategoryName() {
		return name;
	}

	public void setCategoryName(String categoryName) {
		this.name = categoryName;
	}
	
	
}
