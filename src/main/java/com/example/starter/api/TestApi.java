package com.example.starter.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.starter.business.TestBusiness;
import com.example.starter.exception.BaseExeption;
import com.example.starter.model.MRegisterRequest;
import com.example.starter.model.TestResponse;

@RestController
@RequestMapping("/test")
public class TestApi {

  private final TestBusiness business;

  public TestApi(TestBusiness business) {
    this.business = business;
  }

  @GetMapping
  public TestResponse test() {
    TestResponse response = new TestResponse();

    response.setName("O");
    response.setFood("KFC");

    return response;
  }

  @PostMapping
  @RequestMapping("/register")
  public ResponseEntity<String> register(@RequestBody MRegisterRequest request) throws BaseExeption {
    String response = business.register(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/uploadProfile")
  public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws BaseExeption {
    String response = business.uploadProfilePicture(file);
    return ResponseEntity.ok(response);
  }
}