package com.example.starter.mapper;

import org.mapstruct.Mapper;

import com.example.starter.entity.User;
import com.example.starter.model.MRegisterResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {

  MRegisterResponse toRegisterReponse(User user);

}
