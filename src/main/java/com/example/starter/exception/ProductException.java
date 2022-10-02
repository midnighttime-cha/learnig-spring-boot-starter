package com.example.starter.exception;

public class ProductException extends BaseExeption {

  public ProductException(String code) {
    super("product." + code);
  }

  public static ProductException notFound() {
    return new ProductException("not.found");
  }

}