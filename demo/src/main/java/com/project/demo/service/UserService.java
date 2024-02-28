package com.project.demo.service;

import com.project.demo.domain.User;
import com.project.demo.dto.UserDTO;
import com.project.demo.repository.UserRepository;
import com.project.demo.service.exception.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private UserRepository repo;

  public List<User> findAll() {
    return repo.findAll();
  }

  public UserDTO findById(String id) {
    Optional<User> optionalUser = repo.findById(id);
    User user = optionalUser.orElseThrow(() ->
      new ObjectNotFoundException("Id não encontrado")
    );
    UserDTO userDTO = modelMapper.map(user, UserDTO.class);
    return userDTO;
  }
}
