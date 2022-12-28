package com.example.starter.model;

import java.util.Date;

import lombok.Data;

@Data
public class ChatMessage {

  private String from;

  private String message;

  private Date created;

  public ChatMessage() {
    created = new Date();
  }

}
