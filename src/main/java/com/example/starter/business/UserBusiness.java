package com.example.starter.business;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.starter.entity.User;
import com.example.starter.exception.BaseExeption;
import com.example.starter.exception.FileException;
import com.example.starter.exception.UserException;
import com.example.starter.mapper.UserMapper;
import com.example.starter.model.MLoginRequest;
import com.example.starter.model.MRegisterRequest;
import com.example.starter.model.MRegisterResponse;
import com.example.starter.service.UserService;

@Service
public class UserBusiness {

  private final UserService userService;

  private final UserMapper userMapper;

  public UserBusiness(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  public MRegisterResponse register(MRegisterRequest request) throws BaseExeption {

    User users = userService.create(request.getEmail(), request.getPassword(), request.getName());

    MRegisterResponse response = userMapper.toRegisterReponse(users);

    return response;
  }

  public String login(MLoginRequest request) throws BaseExeption {

    Optional<User> opt = userService.findByEmail(request.getEmail());

    if (opt.isEmpty()) {
      throw UserException.loginFailEmailNotFound();
    }

    User user = opt.get();

    if (!userService.matchPassword(request.getPassword(), user.getPassword())) {
      throw UserException.loginFailPasswordIncorrect();
    }

    // TDOD: Gennerate JWT
    String token = "JWT TODO";

    return token;
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
