package com.example.starter.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.starter.entity.Social;
import com.example.starter.entity.User;

public interface SocialRepository extends CrudRepository<Social, String> {

  Optional<Social> findByUser(User user);
}
