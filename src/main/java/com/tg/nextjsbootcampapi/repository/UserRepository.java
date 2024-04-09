package com.tg.nextjsbootcampapi.repository;

import com.tg.nextjsbootcampapi.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

  Optional<User> findByEmail(String email);

  List<User> findAll();

  User save(User userToSave);
}
