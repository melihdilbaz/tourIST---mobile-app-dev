package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document 
public class Comment {

@Id private String id;
	
	private String name;
	private String text;
	
	public Comment(String name, String text) {
		super();
		this.name = name;
		this.text = text;
	}
	
	public Comment() {
		// TODO Auto-generated constructor stub
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", text=" + text + "]";
	}
	
	

}
