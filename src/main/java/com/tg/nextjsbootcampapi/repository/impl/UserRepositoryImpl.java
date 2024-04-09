package com.tg.nextjsbootcampapi.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tg.nextjsbootcampapi.model.User;
import com.tg.nextjsbootcampapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {
  Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);

  private List<User> users = new ArrayList<>();

  public UserRepositoryImpl(PasswordEncoder passwordEncoder) {

    LOGGER.info("[INSERTING INITIAL DATA]");

    ObjectMapper mapper = new ObjectMapper();

    try {

      Path path = Path.of("data/data.json").toAbsolutePath();
      System.out.println(path);
      File file = path.toFile();

      this.users = Arrays.stream(mapper.readValue(file, User[].class))
          .toList()
          .stream()
          .peek(user -> user.setPassword( passwordEncoder.encode(user.getPassword())))
          .toList();

      this.users = users.stream().peek(user -> user.setId(users.indexOf(user) + 1)).toList();
      System.out.println(users);

    } catch (
        IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return this.users.stream().filter(user -> Objects.equals(user.getEmail(), email)).findFirst();
  }

  @Override
  public List<User> findAll() {
    return this.users;
  }

  @Override
  public User save(User userToSave) {

    List<User> newList = new ArrayList<>();
    Collections.copy(this.users, newList);

    userToSave.setId(this.users.size() + 1);
    LOGGER.info(userToSave.toString());

    newList.add(userToSave);

    this.users = newList;
    return userToSave;
  }

}
