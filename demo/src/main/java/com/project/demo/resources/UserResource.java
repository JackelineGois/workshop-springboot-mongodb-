package com.project.demo.resources;

import com.project.demo.domain.User;
import com.project.demo.dto.UserDTO;
import com.project.demo.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

  @Autowired
  private UserService service;

  @GetMapping
  public ResponseEntity<List<UserDTO>> findAll() {
    List<User> list = service.findAll();
    List<UserDTO> listDto = list
      .stream()
      .map(x -> new UserDTO(x))
      .collect(Collectors.toList());
    return ResponseEntity.ok().body(listDto);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<UserDTO> findById(@PathVariable String id) {
    UserDTO objUser = service.findById(id);
    return ResponseEntity.ok().body(objUser);
  }
}
