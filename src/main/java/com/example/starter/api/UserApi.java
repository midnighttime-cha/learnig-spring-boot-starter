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
import com.example.starter.exception.BaseException;
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
  public ResponseEntity<String> login(@RequestBody MLoginRequest request) throws BaseException {
    String response = business.login(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/register")
  public ResponseEntity<MRegisterResponse> register(@RequestBody MRegisterRequest request) throws BaseException {
    MRegisterResponse response = business.register(request);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/refreshToken")
  public ResponseEntity<String> refreshToken() throws BaseException {
    String refreshToken = business.refreshToken();
    return ResponseEntity.ok(refreshToken);
  }

  @PostMapping("/uploadProfile")
  public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws BaseException {
    String response = business.uploadProfilePicture(file);
    return ResponseEntity.ok(response);
  }
}