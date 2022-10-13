package com.example.starter.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

  @Column(nullable = true, length = 13)
  private String civilId;

  @OneToOne(mappedBy = "user", orphanRemoval = true)
  private Social social;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
  private List<Address> addresses;

}
