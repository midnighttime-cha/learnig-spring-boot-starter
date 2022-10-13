package com.example.starter.config.token;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.starter.service.TokenService;

public class TokenFilter extends GenericFilterBean {

  private final TokenService tokenService;

  public TokenFilter(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    String autherization = request.getHeader("Authorization");

    if (ObjectUtils.isEmpty(autherization)) {
      filterChain.doFilter(servletRequest, servletResponse);
      return;
    }

    if (!autherization.startsWith("Bearer ")) {
      filterChain.doFilter(servletRequest, servletResponse);
      return;
    }

    String token = autherization.substring(7);
    DecodedJWT decodedJWT = tokenService.verify(token);

    if (decodedJWT == null) {
      filterChain.doFilter(servletRequest, servletResponse);
      return;
    }

    String principal = decodedJWT.getClaim("principal").asString();
    String role = decodedJWT.getClaim("role").asString();

    List<GrantedAuthority> autherities = new ArrayList<>();
    autherities.add(new SimpleGrantedAuthority(role));

    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal,
        "(protacted)", autherities);

    SecurityContext context = SecurityContextHolder.getContext();
    context.setAuthentication(authenticationToken);

    filterChain.doFilter(servletRequest, servletResponse);
  }

}
