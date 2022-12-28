package com.example.starter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.starter.config.token.TokenFilterConfigurer;
import com.example.starter.service.TokenService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final TokenService tokenService;

  private final String[] PUBLIC = {
      "/actuator/**",
      "/user/register",
      "/user/login",
      "/socket"
  };

  public SecurityConfig(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    super.configure(auth);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors().disable() // Disable CORS
        .csrf().disable() // Disable CSRF
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        // Session stateless: When
        // requesting for API the server
        // know the client. But the server
        // unknow client when response data.
        .and().authorizeRequests()
        .antMatchers(PUBLIC)
        .anonymous()
        // if requesting to the
        // API: "/user/register" or
        // "/user/login" the
        // allowed.
        .anyRequest()
        .authenticated() // Other APIs must log in.
        .and().apply(new TokenFilterConfigurer(tokenService));
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("http://localhost:4200");
    config.addAllowedHeader("*");
    config.addAllowedMethod("OPTIONS");
    config.addAllowedMethod("POST");
    config.addAllowedMethod("GET");
    config.addAllowedMethod("PUT");
    config.addAllowedMethod("DELETE");
    source.registerCorsConfiguration("/**", config);

    return new CorsFilter(source);
  }
}
