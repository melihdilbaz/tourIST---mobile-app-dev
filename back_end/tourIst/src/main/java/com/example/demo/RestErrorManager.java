package com.example.demo;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// both a controller and handler (exception)
@RestControllerAdvice
public class RestErrorManager {

	@ExceptionHandler(DestinationException.class)
	public Message<String> handleNotFoundDestination(DestinationException ex) {
		return new Message<String> (LocalDateTime.now(), "ERROR", ex.getMessage());
	}
	
	@ExceptionHandler(ItineraryException.class)
	public Message<String> handleNotFoundItinerary(ItineraryException ex) {
		return new Message<String> (LocalDateTime.now(), "ERROR", ex.getMessage());
	}

}
