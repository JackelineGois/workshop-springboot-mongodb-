package com.project.demo.repository;

import com.project.demo.domain.Post;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
  //Query methods
  List<Post> findByTitleContainingIgnoreCase(String text);
}
