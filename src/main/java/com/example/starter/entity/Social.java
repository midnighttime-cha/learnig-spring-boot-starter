package com.example.starter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "socials")
public class Social extends BaseEntity {

  @Column(length = 120)
  private String facebook;

  @Column(length = 120)
  private String line;

  @Column(length = 120)
  private String instragram;

  @Column(length = 13)
  private String tiktok;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
