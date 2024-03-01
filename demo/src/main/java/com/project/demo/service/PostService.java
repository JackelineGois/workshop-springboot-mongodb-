package com.project.demo.service;

import com.project.demo.domain.Post;
import com.project.demo.repository.PostRepository;
import com.project.demo.service.exception.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  @Autowired
  private PostRepository repo;

  public Post findById(String id) {
    Optional<Post> user = repo.findById(id);
    return user.orElseThrow(() ->
      new ObjectNotFoundException("Id não encontrado")
    );
  }

  public List<Post> findByTitle(String txt) {
    return repo.searchTitle(txt);
  }
}
