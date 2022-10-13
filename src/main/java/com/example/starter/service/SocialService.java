package com.example.starter.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.starter.entity.Social;
import com.example.starter.entity.User;
import com.example.starter.repository.SocialRepository;

@Service
public class SocialService {

  private final SocialRepository repository;

  public SocialService(SocialRepository repository) {
    this.repository = repository;
  }

  public Optional<Social> findByUser(User user) {
    return repository.findByUser(user);
  }

  public Social create(User user, String facebook, String line, String instragram, String tiktok) {
    Social entity = new Social();

    entity.setUser(user);
    entity.setFacebook(facebook);
    entity.setLine(line);
    entity.setInstragram(instragram);
    entity.setTiktok(tiktok);

    return repository.save(entity);
  }
}
