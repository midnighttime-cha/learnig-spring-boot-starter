package com.example.starter.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.starter.entity.User;

@Service
public class TokenService {

  @Value("${APP_SECRET}")
  private String secret;

  @Value("${APP_ISSUER}")
  private String issuer;

  public String tokenize(User user) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MINUTE, 60);
    Date expireAt = calendar.getTime();

    return JWT.create()
        .withIssuer(issuer)
        .withClaim("principal", user.getId())
        .withClaim("role", "USER")
        .withExpiresAt(expireAt)
        .sign(algorithm());
  }

  public DecodedJWT verify(String token) {
    try {
      JWTVerifier verifier = JWT.require(algorithm())
          .withIssuer(issuer)
          .build(); // Reusable verifier instance

      return verifier.verify(token);
    } catch (Exception e) {
      return null;
    }
  }

  public Algorithm algorithm() {
    return Algorithm.HMAC256(secret);
  }
}
