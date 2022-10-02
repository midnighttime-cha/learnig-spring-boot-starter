package com.example.starter.business;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.starter.entity.User;
import com.example.starter.exception.BaseExeption;
import com.example.starter.exception.FileException;
import com.example.starter.model.MRegisterRequest;
import com.example.starter.service.UserService;

@Service
public class UserBusiness {

  private final UserService userService;

  public UserBusiness(UserService userService) {
    this.userService = userService;
  }

  public User register(MRegisterRequest request) throws BaseExeption {

    System.out.println(request);
    User users = userService.create(request.getEmail(), request.getPassword(), request.getName());

    // TODO mapper

    return users;
  }

  public String uploadProfilePicture(MultipartFile file) throws BaseExeption {
    // Validate file
    if (file == null) {
      throw FileException.fileNull();
    }

    // Validate size
    if (file.getSize() > 1048576 * 2) {
      throw FileException.fileMaxSize();
    }

    String contentType = file.getContentType();

    if (contentType == null) {
      throw FileException.unsupported();
    }

    List<String> supportedTypes = Arrays.asList("image/jpeg", "image/png");

    if (supportedTypes.contains(contentType)) {
      throw FileException.unsupported();
    }

    // try {
    // // Business Logic uploadfile
    // byte[] bytes = file.getBytes();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }

    return "";
  }

}
