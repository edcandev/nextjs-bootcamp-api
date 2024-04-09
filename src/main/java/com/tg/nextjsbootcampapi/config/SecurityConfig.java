package com.tg.nextjsbootcampapi.config;

import com.tg.nextjsbootcampapi.filter.JwtAuthenticationFilter;
import com.tg.nextjsbootcampapi.service.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

  private final UserDetailServiceImpl userDetailService;
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  public SecurityConfig(UserDetailServiceImpl userDetailService, JwtAuthenticationFilter jwtAuthenticationFilter) {
    this.userDetailService = userDetailService;
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
  }


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            request ->request
                .requestMatchers("/api/public/**", "/api/auth/login", "/api/auth/register")
                .permitAll()
                .anyRequest()
                .authenticated()
        )
        .userDetailsService(userDetailService)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

}
