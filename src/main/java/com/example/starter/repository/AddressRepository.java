package com.example.starter.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.starter.entity.Address;
import com.example.starter.entity.User;

public interface AddressRepository extends CrudRepository<Address, String> {

  List<Address> findByUser(User user);
}
