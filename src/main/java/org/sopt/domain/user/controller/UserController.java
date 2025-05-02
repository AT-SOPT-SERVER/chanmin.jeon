package org.sopt.domain.user.controller;

import org.sopt.common.response.ApiResponse;
import org.sopt.domain.user.dto.UserCreateRequest;
import org.sopt.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Void>> signup(
      @RequestBody UserCreateRequest request
  ) {
    userService.createUser(request);
    return ResponseEntity
        .status(201)
        .body(ApiResponse.success(201, "회원가입 성공", null));
  }
}
