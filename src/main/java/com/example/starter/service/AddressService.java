package com.example.starter.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.starter.entity.Address;
import com.example.starter.entity.User;
import com.example.starter.repository.AddressRepository;

@Service
public class AddressService {

  private final AddressRepository repository;

  public AddressService(AddressRepository repository) {
    this.repository = repository;
  }

  public List<Address> findByUser(User user) {
    return repository.findByUser(user);
  }

  public Address create(User user, String line1, String line2, String zipcode) {
    Address entity = new Address();

    entity.setUser(user);
    entity.setLine1(line1);
    entity.setLine2(line2);
    entity.setZipcode(zipcode);

    return repository.save(entity);
  }
}
