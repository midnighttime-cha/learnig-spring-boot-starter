package com.example.starter.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.starter.entity.User;
import com.example.starter.exception.UserException;
import com.example.starter.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public User create(String email, String password, String name) throws UserException {
    User entity = new User();

    // Validate
    if (Objects.isNull(email)) {
      throw UserException.createEmailNull();
    }

    if (Objects.isNull(password)) {
      throw UserException.createPasswordNull();
    }

    if (Objects.isNull(name)) {
      throw UserException.createNameNull();
    }

    // Verify
    if (repository.existsByEmail(email)) {
      throw UserException.createEamilDuplicated();
    }

    entity.setEmail(email);
    entity.setPassword(password);
    entity.setName(name);

    return repository.save(entity);
  }
}
