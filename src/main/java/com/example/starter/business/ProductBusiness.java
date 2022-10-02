package com.example.starter.business;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.starter.exception.BaseExeption;
import com.example.starter.exception.ProductException;

@Service
public class ProductBusiness {

  public String getProductById(String id) throws BaseExeption {
    if (Objects.equals("1234", id)) {
      throw ProductException.notFound();
    }
    return id;
  }
}
