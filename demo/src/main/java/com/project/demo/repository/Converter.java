package com.project.demo.repository;

import com.project.demo.domain.User;
import com.project.demo.dto.UserDTO;

public interface Converter {
  public UserDTO convertFromEntity(User u);

  public User convertFromDTO(UserDTO dto);
}
