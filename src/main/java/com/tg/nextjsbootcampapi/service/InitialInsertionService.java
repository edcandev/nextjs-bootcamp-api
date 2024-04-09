package com.tg.nextjsbootcampapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tg.nextjsbootcampapi.model.User;
import com.tg.nextjsbootcampapi.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class InitialInsertionService {

  Logger LOGGER = LoggerFactory.getLogger(InitialInsertionService.class);

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public InitialInsertionService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @PostConstruct
  public void init() {

    LOGGER.info("[INSERTING INITIAL DATA]");
    ObjectMapper mapper = new ObjectMapper();

    try {

      File file = ResourceUtils.getFile("classpath:data.json");
      List<User> usersList = Arrays.stream(mapper.readValue(file, User[].class))
          .toList()
          .stream()
          .peek(user -> user.setPassword( passwordEncoder.encode(user.getPassword()))).toList();
      userRepository.saveAll(usersList);
      
    } catch (
        IOException e) {
      e.printStackTrace();
    }

  }

}
