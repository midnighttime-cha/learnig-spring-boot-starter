package com.example.starter.exception;

public class UserException extends BaseExeption {

  public UserException(String code) {
    super("user." + code);
  }

  public static UserException requestNull() {
    return new UserException("register.request.null");
  }

  public static UserException emailNull() {
    return new UserException("register.email.null");
  }
}
