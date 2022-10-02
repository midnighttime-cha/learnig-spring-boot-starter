package com.example.starter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "users")
public class User extends BaseEntity {

  @Column(nullable = false, unique = true, length = 60)
  private String email;

  @Column(nullable = false, length = 120)
  private String password;

  @Column(nullable = false, length = 120)
  private String name;

}
