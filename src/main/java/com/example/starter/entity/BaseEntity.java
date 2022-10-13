package com.example.starter.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity {

  @Id
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
  @Column(length = 36, nullable = false, updatable = false)
  private String id;

}
