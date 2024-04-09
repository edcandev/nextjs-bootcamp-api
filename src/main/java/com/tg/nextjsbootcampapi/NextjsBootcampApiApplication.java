package com.tg.nextjsbootcampapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tg.nextjsbootcampapi.dto.UsersList;
import com.tg.nextjsbootcampapi.model.User;
import com.tg.nextjsbootcampapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class NextjsBootcampApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NextjsBootcampApiApplication.class, args);
	}

}
