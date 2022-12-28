package com.example.starter.util;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

  private SecurityUtil() {

  }

  public static Optional<String> getCurrentUserId() {
    SecurityContext context = SecurityContextHolder.getContext();
    if (context == null) {
      Optional.empty();
    }

    Authentication authentication = context.getAuthentication();
    if (authentication == null) {
      Optional.empty();
    }

    Object principal = authentication.getPrincipal();
    if (principal == null) {
      Optional.empty();
    }

    String userId = (String) principal;
    return Optional.of(userId);
  }
}
