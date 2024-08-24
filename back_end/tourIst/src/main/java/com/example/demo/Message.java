package com.example.demo;

import java.time.LocalDateTime;

public class Message<T> { // we do not know what to return
	private LocalDateTime time;
	private String status;
	
	private T data; // data carrier

	public Message(LocalDateTime time, String status, T data) {
		super();
		this.time = time;
		this.status = status;
		this.data = data;
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
