package com.example.starter.exception;

public class EmailException extends BaseException {

  public EmailException(String code) {
    super("file." + code);
  }

  public static EmailException templateNotFound() {
    return new EmailException("template.not.found");
  }

}
