package com.example.starter.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.starter.business.UserBusiness;
import com.example.starter.entity.User;
import com.example.starter.exception.BaseExeption;
import com.example.starter.model.MRegisterRequest;
import com.example.starter.model.TestResponse;

@RestController
@RequestMapping("/user")
public class UserApi {

  private final UserBusiness business;

  public UserApi(UserBusiness business) {
    this.business = business;
  }

  @GetMapping
  public TestResponse test() {
    TestResponse response = new TestResponse();

    response.setName("O");
    response.setFood("KFC");

    return response;
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody MRegisterRequest request) throws BaseExeption {
    User response = business.register(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/uploadProfile")
  public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws BaseExeption {
    String response = business.uploadProfilePicture(file);
    return ResponseEntity.ok(response);
  }
}