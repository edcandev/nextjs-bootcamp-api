package com.tg.nextjsbootcampapi.auth;

import com.tg.nextjsbootcampapi.dto.response.AuthenticationResponse;
import com.tg.nextjsbootcampapi.jwt.JwtService;
import com.tg.nextjsbootcampapi.model.User;
import com.tg.nextjsbootcampapi.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

  public AuthenticationResponse register(User request) {

    if(userRepository.findByEmail(request.getEmail()).isPresent()) {
      return new AuthenticationResponse("User already exists");
    }

    User userToSave = new User();

    userToSave.setFirstName(request.getFirstName());
    userToSave.setLastName(request.getLastName());
    userToSave.setEmail(request.getEmail());
    userToSave.setPassword(passwordEncoder.encode(request.getPassword()));

    if(request.getHasProject() != null) {
      userToSave.setHasProject(request.getHasProject());
    }
    userToSave.setJobTitle(request.getJobTitle());


    User savedUser = userRepository.save(userToSave);

    String jwt = jwtService.generateToken(savedUser);


    return new AuthenticationResponse(jwt);

  }

  public AuthenticationResponse authenticate(User request) {

    if(userRepository.findByEmail(request.getEmail()).isEmpty()) {
      return new AuthenticationResponse("User doesn't exists");
    }

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );

    User user = userRepository.findByEmail(request.getEmail()).orElseThrow( () -> new UsernameNotFoundException("User doesn't exists"));
    String jwt = jwtService.generateToken(user);
    return new AuthenticationResponse(jwt);

  }

}
