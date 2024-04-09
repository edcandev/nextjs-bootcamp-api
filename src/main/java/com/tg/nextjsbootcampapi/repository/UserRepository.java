package com.tg.nextjsbootcampapi.repository;

import com.tg.nextjsbootcampapi.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findByEmail(String email);

}
