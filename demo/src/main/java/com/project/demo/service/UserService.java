package com.project.demo.service;

import com.project.demo.domain.User;
import com.project.demo.dto.UserDTO;
import com.project.demo.repository.UserRepository;
import com.project.demo.service.exception.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository repo;

  public List<User> findAll() {
    return repo.findAll();
  }

  public User findById(String id) {
    Optional<User> optionalUser = repo.findById(id);
    return optionalUser.orElseThrow(() ->
      new ObjectNotFoundException("Id não encontrado")
    );
  }

  public User insert(User obj) {
    return repo.insert(obj);
  }

  public void delete(String id) {
    findById(id);
    repo.deleteById(id);
  }

  public User update(User obj) {
    Optional<User> optionalUser = repo.findById(obj.getId());
    User newObj = optionalUser.orElseThrow(() ->
      new ObjectNotFoundException("Id não encontrado")
    );
    updateDate(newObj, obj);
    return repo.save(newObj);
  }

  private void updateDate(User newObj, User obj) {
    newObj.setName(obj.getName());
    newObj.setEmail(obj.getEmail());
  }

  public User fromDTO(UserDTO objDto) {
    return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
  }
}
