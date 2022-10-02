package com.example.starter.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.starter.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);

}
