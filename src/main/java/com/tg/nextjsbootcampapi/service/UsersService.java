package com.tg.nextjsbootcampapi.service;

import com.tg.nextjsbootcampapi.dto.response.UserResponse;
import com.tg.nextjsbootcampapi.model.User;
import com.tg.nextjsbootcampapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
  private final UserRepository userRepository;
  public UsersService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  public List<UserResponse> getAllUsersResponse() {
    return ((List<User>) userRepository.findAll())
        .stream()
        .filter(User::getActive)
        .map(user ->
          new UserResponse(
            user.getId(),
            user.getEmail(),
            user.getActive(),
            user.getFirstName(),
            user.getLastName(),
            user.getJobTitle(),
            user.getHasProject()
          )
        ).toList();
  }

}
