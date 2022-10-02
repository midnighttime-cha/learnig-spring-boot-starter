package com.example.starter.exception;

public class FileException extends BaseExeption {

  public FileException(String code) {
    super("file." + code);
  }

  public static FileException fileNull() {
    return new FileException("null");
  }

  public static FileException fileMaxSize() {
    return new FileException("max.size");
  }

  public static FileException unsupported() {
    return new FileException("unsupported.file.type");
  }

}
