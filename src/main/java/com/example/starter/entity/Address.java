package com.example.starter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "addresses")
public class Address extends BaseEntity {

  @Column(length = 120)
  private String line1;

  @Column(length = 120)
  private String line2;

  @Column(length = 120)
  private String zipcode;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
