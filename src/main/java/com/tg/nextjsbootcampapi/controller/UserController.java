package com.tg.nextjsbootcampapi.controller;

import com.tg.nextjsbootcampapi.model.User;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @GetMapping("/hello")
  @ExceptionHandler(SignatureException.class)
  public String getUserInfo() {
    return "Hello from protected route!";
  }

}
