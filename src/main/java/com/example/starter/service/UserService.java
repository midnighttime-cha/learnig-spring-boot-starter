package com.example.starter.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.starter.entity.User;
import com.example.starter.exception.BaseException;
import com.example.starter.exception.UserException;
import com.example.starter.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository repository;

  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  public Optional<User> findByEmail(String email) {
    return repository.findByEmail(email);
  }

  public User update(User user) {
    return repository.save(user);
  }

  public User userUpdateName(String id, String name) throws BaseException {
    Optional<User> opt = repository.findById(id);

    if (opt.isEmpty()) {
      throw UserException.notFound();
    }

    User user = opt.get();
    user.setName(name);

    return repository.save(user);
  }

  public void delete(String id) {
    repository.deleteById(id);
  }

  public boolean matchPassword(String rawPassword, String encodedPassword) {
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }

  public User create(String email, String password, String name) throws BaseException {
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
    entity.setPassword(passwordEncoder.encode(password));
    entity.setName(name);

    return repository.save(entity);
  }
}
