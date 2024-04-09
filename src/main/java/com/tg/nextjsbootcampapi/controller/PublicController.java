package com.tg.nextjsbootcampapi.controller;

import com.tg.nextjsbootcampapi.dto.response.UserResponse;
import com.tg.nextjsbootcampapi.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "*")
public class PublicController {

  Logger LOGGER = LoggerFactory.getLogger(PublicController.class);

  UsersService usersService;
  public PublicController(UsersService usersService) {
    this.usersService = usersService;
  }


  @GetMapping("/users")
  public ResponseEntity<List<UserResponse>> getUsers() {

    LOGGER.info("[api/public/users] - REQUEST");

    return ResponseEntity.ok(usersService.getAllUsersResponse());
  }

}
