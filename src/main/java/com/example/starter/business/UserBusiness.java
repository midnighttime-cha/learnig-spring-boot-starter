package com.example.starter.business;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.starter.entity.User;
import com.example.starter.exception.BaseException;
import com.example.starter.exception.FileException;
import com.example.starter.exception.UserException;
import com.example.starter.mapper.UserMapper;
import com.example.starter.model.MLoginRequest;
import com.example.starter.model.MRegisterRequest;
import com.example.starter.model.MRegisterResponse;
import com.example.starter.service.TokenService;
import com.example.starter.service.UserService;

@Service
public class UserBusiness {

  private final UserService userService;

  private final UserMapper userMapper;

  private final TokenService tokenService;

  public UserBusiness(UserService userService, UserMapper userMapper, TokenService tokenService) {
    this.userService = userService;
    this.userMapper = userMapper;
    this.tokenService = tokenService;
  }

  public MRegisterResponse register(MRegisterRequest request) throws BaseException {

    User users = userService.create(request.getEmail(), request.getPassword(), request.getName());

    MRegisterResponse response = userMapper.toRegisterReponse(users);

    return response;
  }

  public String login(MLoginRequest request) throws BaseException {

    Optional<User> opt = userService.findByEmail(request.getEmail());

    if (opt.isEmpty()) {
      throw UserException.loginFailEmailNotFound();
    }

    User user = opt.get();

    if (!userService.matchPassword(request.getPassword(), user.getPassword())) {
      throw UserException.loginFailPasswordIncorrect();
    }

    // TDOD: Gennerate JWT
    return tokenService.tokenize(user);
  }

  public String uploadProfilePicture(MultipartFile file) throws BaseException {
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
