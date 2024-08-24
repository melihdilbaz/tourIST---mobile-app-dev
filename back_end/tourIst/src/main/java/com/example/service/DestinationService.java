package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Category;
import com.example.model.Destination;
import com.example.model.Comment;
import com.example.model.CommentPayload;
import com.example.repo.CommentRepo;
import com.example.repo.DestinationRepo;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepo destinationRepo;
    
    @Autowired CommentRepo commentRepo;

    public List<Destination> getDestinationsByCategory(Category category) {
        return destinationRepo.findByCategory(category);
    }
    
    public Destination postCommentByDestinationId(String destinationId, CommentPayload commentPayload) {
        // Find the destination by ID
        Destination destination = destinationRepo.findById(destinationId).get();
        
        if (destination==null) 
        	throw new RuntimeException("No such destination");
        // Create a new comment
        Comment comment = new Comment(commentPayload.getName(), commentPayload.getText());
        commentRepo.save(comment);
        // Add the comment to the destination
        destination.getComments().add(comment);

     // Save the updated destination
        return destinationRepo.save(destination);
        
    }
    
}

