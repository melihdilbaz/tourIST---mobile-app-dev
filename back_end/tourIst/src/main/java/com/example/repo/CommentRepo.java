package com.example.repo;

import com.example.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CommentRepo extends MongoRepository<Comment, String> {
}

