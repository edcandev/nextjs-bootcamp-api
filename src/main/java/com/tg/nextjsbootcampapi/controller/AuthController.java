package com.tg.nextjsbootcampapi.controller;

import com.tg.nextjsbootcampapi.auth.AuthenticationService;
import com.tg.nextjsbootcampapi.dto.response.AuthenticationResponse;
import com.tg.nextjsbootcampapi.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


  private final AuthenticationService authenticationService;


  public AuthController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody User user
  ) {
    return ResponseEntity.status(200).body(authenticationService.register(user));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(
      @RequestBody User user
  ) {
    return ResponseEntity.status(200).body(authenticationService.authenticate(user));
  }

}
