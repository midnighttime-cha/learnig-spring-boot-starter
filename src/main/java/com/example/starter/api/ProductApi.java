package com.example.starter.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.starter.business.ProductBusiness;
import com.example.starter.exception.ProductException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/product")
public class ProductApi {

  private final ProductBusiness product;

  public ProductApi(ProductBusiness product) {
    this.product = product;
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<String> getProductById(@PathVariable("id") String id) throws ProductException {
    String response = product.getProductById(id);
    return ResponseEntity.ok(response);
  }

}
