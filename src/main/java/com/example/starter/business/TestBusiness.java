package com.example.starter.business;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.starter.exception.BaseExeption;
import com.example.starter.exception.FileException;
import com.example.starter.exception.UserException;
import com.example.starter.model.MRegisterRequest;

@Service
public class TestBusiness {

  public String register(MRegisterRequest request) throws BaseExeption {

    if (request == null) {
      throw UserException.requestNull();
    }

    if (Objects.isNull(request.getEmail())) {
      throw UserException.emailNull();
    }

    return "";
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

    try {
      // Business Logic uploadfile
      byte[] bytes = file.getBytes();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return "";
  }

}
