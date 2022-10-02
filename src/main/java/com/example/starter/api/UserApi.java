package com.example.starter.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.starter.business.UserBusiness;
import com.example.starter.exception.BaseExeption;
import com.example.starter.model.MLoginRequest;
import com.example.starter.model.MRegisterRequest;
import com.example.starter.model.MRegisterResponse;

@RestController
@RequestMapping("/user")
public class UserApi {

  private final UserBusiness business;

  public UserApi(UserBusiness business) {
    this.business = business;
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody MLoginRequest request) throws BaseExeption {
    String response = business.login(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/register")
  public ResponseEntity<MRegisterResponse> register(@RequestBody MRegisterRequest request) throws BaseExeption {
    MRegisterResponse response = business.register(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/uploadProfile")
  public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws BaseExeption {
    String response = business.uploadProfilePicture(file);
    return ResponseEntity.ok(response);
  }
}