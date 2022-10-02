package com.example.starter.exception;

public class UserException extends BaseExeption {

  public UserException(String code) {
    super("user." + code);
  }

  // REGTISTER
  public static UserException requestNull() {
    return new UserException("register.request.null");
  }

  public static UserException emailNull() {
    return new UserException("register.email.null");
  }

  // CREATE USER
  public static UserException createEmailNull() {
    return new UserException("create.email.null");
  }

  public static UserException createPasswordNull() {
    return new UserException("create.password.null");
  }

  public static UserException createNameNull() {
    return new UserException("create.name.null");
  }

  public static UserException createEamilDuplicated() {
    return new UserException("create.email.duplicated");
  }

  // LOGIN
  public static UserException loginFailEmailNotFound() {
    return new UserException("login.fail");
  }

  public static UserException loginFailPasswordIncorrect() {
    return new UserException("login.fail");
  }
}
