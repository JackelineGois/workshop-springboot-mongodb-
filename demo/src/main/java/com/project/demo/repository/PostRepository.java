package com.project.demo.repository;

import com.project.demo.domain.Post;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PostRepository extends MongoRepository<Post, String> {
  @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
  List<Post> searchTitle(String text);

  //Query methods
  List<Post> findByTitleContainingIgnoreCase(String text);
}
