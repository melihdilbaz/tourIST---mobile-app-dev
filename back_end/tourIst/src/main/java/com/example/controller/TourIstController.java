package com.example.controller;

import com.example.demo.DestinationException;
import com.example.demo.ItineraryException;
import com.example.model.Category;
import com.example.model.Comment;
import com.example.model.CommentPayload;
import com.example.model.Destination;
import com.example.model.Itinerary;
import com.example.repo.*;
import com.example.service.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class TourIstController {
	
	@Autowired DestinationRepo destRepo;
	
	@Autowired TourService tourService;
	
	@Autowired ItineraryRepo itineraryRepo;
	
	@Autowired DestinationService destinationService;
	
	@Autowired CategoryRepo categoryRepo ;
	
	@Autowired CommentRepo commentRepo;
	
	//Logger logger = (Logger) LoggerFactory.getLogger(TourIstController.class);
	
	@GetMapping("/itineraries")
	public List<Itinerary> getAllItineraries()  {		
		return itineraryRepo.findAll();
	}
	
	@GetMapping("/itineraries/{id}")
	public Itinerary getItineraryById(@PathVariable("id") String id) {
		return itineraryRepo.findById(id).get();
		
	}
	@PostMapping("/itineraryheader/create")
	public Itinerary createItinerary(@RequestHeader("title") String title) {
		Itinerary it = itineraryRepo.findByName(title);
		if (it != null)
			throw new ItineraryException("This itinerary already exists!..");
		
		return itineraryRepo.save(new Itinerary(title));
		
	}
	@PostMapping("/itineraryheader/add")
	public Itinerary addDestinationToItinerary(@RequestHeader("title") String title, @RequestHeader("name") String destName) {
		if (destRepo.findByNameContainsIgnoreCase(destName).size()==0)
			throw new DestinationException("No such destination!");
		
		Destination dest = destRepo.findByNameContainsIgnoreCase(destName).get(0);
		
		// if(dest==null) throw new RuntimeException("No such destination!");
		
		Itinerary itinerary = tourService.addDestination(title, dest);
		return itinerary;
		}
	
	@PostMapping("/itineraryheader/remove")
	public Itinerary dropDestinationOffTheEnd(@RequestParam("title") String title) {
		Itinerary itinerary = tourService.dropDestination(title);
		return itinerary;
	}
	
    // End point to get detailed information about a destination by its ID
    @GetMapping("/destinations")
    public Destination getDetailedInfo(@RequestParam("id") String id) {
    	if (destRepo.findDestinationById(id) == null)
    		throw new DestinationException("Destination not found!");
        return destRepo.findDestinationById(id);
    }
    
    @GetMapping("/destinations/getall")
    public ResponseEntity<List<Destination>> getAllDestinationsByCategoryName(@RequestParam("categoryName") String categoryName) {
        // Assuming you have a method to find Category by name
        Category category = categoryRepo.findByName(categoryName).get(0);
        if (category == null) {
            // Handle the case where category is not found
            return ResponseEntity.notFound().build();
        }
        List<Destination> destinations = destinationService.getDestinationsByCategory(category);
        return ResponseEntity.ok(destinations);
    }
    
    @GetMapping("/comments")
    public List<Comment> getAllCommentsByDestinationId(@RequestHeader("id") String destinationId) {
    	Destination destination = destRepo.findById(destinationId).get();
        return destination.getComments();
    }
    
    @PostMapping("/post")
    public ResponseEntity<String> postComment(
            @RequestHeader("id") String id,
            @RequestBody CommentPayload commentPayload) {

    	try {
            destinationService.postCommentByDestinationId(id, commentPayload);
            return ResponseEntity.ok("Comment added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
