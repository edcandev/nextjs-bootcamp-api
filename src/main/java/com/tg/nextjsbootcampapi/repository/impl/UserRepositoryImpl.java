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

      // Path path = Path.of("data/data.json").toAbsolutePath();
      // System.out.println(path);
      // File file = path.toFile();

      String jsonStr = """
          [
            {
              "email": "ivaalvarez@deloitte.com",
              "password": "Pass123.",
              "first_name": "Ivan",
              "last_name": "Alvarez",
              "job_title": "SPECIALIST_MASTER",
              "has_project": true
            },
            {
              "email": "abalbuenaespinosa@deloitte.com",
              "password": "Pass123.",
              "first_name": "Angeherib",
              "last_name": "Balbuena Espinoza De Los Monteros",
              "job_title": "CONSULTANT",
              "has_project": true
            },
            {
              "email": "ecanoroman@deloitte.com",
              "password": "Pass123.",
              "first_name": "Edgar Israel",
              "last_name": "Cano Roman",
              "job_title": "CONSULTANT",
              "has_project": true
            },
            {
              "email": "frduhaltvelazquez@deloitte.com",
              "password": "Pass123.",
              "first_name": "Franco Avery",
              "last_name": "Duhalt Velazquez",
              "job_title": "CONSULTANT",
              "has_project": true
            },
            {
              "email": "sespinozafernande@deloitte.com",
              "password": "Pass123.",
              "first_name": "Sergio Ivan",
              "last_name": "Espinoza Fernandez",
              "job_title": "CONSULTANT",
              "has_project": true
            },
            {
              "email": "jferegrinoaguilar@deloitte.com",
              "password": "Pass123.",
              "first_name": "Jose Manuel",
              "last_name": "Feregrino Aguilar",
              "job_title": "CONSULTANT",
              "has_project": true
            },
            {
              "email": "luisgarciagarcia@deloitte.com",
              "password": "Pass123.",
              "first_name": "Luis Octavio",
              "last_name": "Garcia Garcia",
              "job_title": "SENIOR_CONSULTANT",
              "has_project": true
            },
            {
              "email": "braygarcia@deloitte.com",
              "password": "Pass123.",
              "first_name": "Brayan",
              "last_name": "Garcia",
              "job_title": "ANALYST",
              "has_project": false
            },
            {
              "email": "cgomezfigueroa@deloitte.com",
              "password": "Pass123.",
              "first_name": "Carolina",
              "last_name": "Gomez Figueroa",
              "job_title": "ANALYST",
              "has_project": true
            },
            {
              "email": "aguerrasantos@deloitte.com",
              "password": "Pass123.",
              "first_name": "Alejandro Baruch",
              "last_name": "Guerra Santos",
              "job_title": "CONSULTANT",
              "has_project": true
            },
            {
              "email": "edlabastidaarce@deloitte.com",
              "password": "Pass123.",
              "first_name": "Eduardo",
              "last_name": "Labastida Arce",
              "job_title": "CONSULTANT",
              "has_project": true
            },
            {
              "email": "ilopezsoto@deloitte.com",
              "password": "Pass123.",
              "first_name": "Ivan de Jesus",
              "last_name": "Lopez Soto",
              "job_title": "CONSULTANT",
              "has_project": false
            },
            {
              "email": "smontesincin@deloitte.com",
              "password": "Pass123.",
              "first_name": "Sara Doris",
              "last_name": "Montes Incin",
              "job_title": "CONSULTANT",
              "has_project": true
            },
            {
              "email": "jolivadiaz@deloitte.com",
              "password": "Pass123.",
              "first_name": "Juan Carlos",
              "last_name": "Oliva Diaz",
              "job_title": "CONSULTANT",
              "has_project": true
            },
            {
              "email": "colveradeanda@deloitte.com",
              "password": "Pass123.",
              "first_name": "Cristobal",
              "last_name": "Olvera Deanda",
              "job_title": "ANALYST",
              "has_project": true
            },
            {
              "email": "losunatirado@deloitte.com",
              "password": "Pass123.",
              "first_name": "Luis Felipe",
              "last_name": "Osuna Tirado",
              "job_title": "CONSULTANT",
              "has_project": false
            },
            {
              "email": "sserranomarquez@deloitte.com",
              "password": "Pass123.",
              "first_name": "Santiago",
              "last_name": "Serrano Marquez",
              "job_title": "ANALYST",
              "has_project": true
            },
            {
              "email": "csonoracaceres@deloitte.com",
              "password": "Pass123.",
              "first_name": "Carlos Antonio",
              "last_name": "Sonora Caceres",
              "job_title": "SENIOR_CONSULTANT",
              "has_project": true
            },
            {
              "email": "ricasoto@deloitte.com",
              "password": "Pass123.",
              "first_name": "Ricardo Margarito",
              "last_name": "Soto",
              "job_title": "ANALYST",
              "has_project": true
            }
                    
          ]
          """;

      this.users = Arrays.stream(mapper.readValue(jsonStr, User[].class) )
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
