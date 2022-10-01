package com.example.starter.business;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.starter.exception.BaseExeption;
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

}
