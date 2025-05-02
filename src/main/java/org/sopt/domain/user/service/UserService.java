package org.sopt.domain.user.service;

import org.sopt.domain.user.exception.UserErrorCode;
import org.sopt.exception.CustomException;
import org.sopt.domain.user.dto.UserCreateRequest;
import org.sopt.domain.user.entity.User;
import org.sopt.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Transactional
  public void createUser(UserCreateRequest request) {
    validateNickName(request.author());

    if (userRepository.existsByAuthor(request.author())) {
      throw new CustomException(UserErrorCode.DUPLICATE_NICKNAME);
    }

    userRepository.save(new User(request.author()));
  }

  private void validateNickName(String nickname) {
    if (nickname == null || nickname.isBlank()) {
      throw new CustomException(UserErrorCode.NICKNAME_REQUIRED);
    }

    if (nickname.length() > 10) {
      throw new CustomException(UserErrorCode.NICKNAME_TOO_LONG);
    }


  }

}
