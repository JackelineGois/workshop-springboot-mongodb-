package com.project.demo.resources;

import com.project.demo.domain.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

  @GetMapping
  public ResponseEntity<List<User>> findAll() {
    User m = new User("1", "Maria", "maria@gmail.com");
    User a = new User("2", "Alex", "Alex@gmail.com");

    List<User> list = new ArrayList<>();
    list.addAll(Arrays.asList(m, a));
    return ResponseEntity.ok().body(list);
  }
}
