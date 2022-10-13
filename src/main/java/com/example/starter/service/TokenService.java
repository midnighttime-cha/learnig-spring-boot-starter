package com.example.starter.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.starter.entity.User;

@Service
public class TokenService {

  @Value("${APP_SECRET}")
  private String secret;

  @Value("${APP_ISSUER}")
  private String issuer;

  public String tokenize(User user) {
    Algorithm algorithm = Algorithm.HMAC256(secret);

    return JWT.create()
        .withIssuer(issuer)
        .withClaim("principal", user.getId())
        .withClaim("role", "USER")
        .sign(algorithm);
  }
}
