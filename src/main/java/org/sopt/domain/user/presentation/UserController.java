package org.sopt.domain.user.presentation;

import org.sopt.domain.user.application.dto.UserCreateCommand;
import org.sopt.domain.user.application.dto.UserLoginCommand;
import org.sopt.domain.user.application.usecase.UserCommandUsecase;
import org.sopt.domain.user.presentation.dto.UserCreateRequest;
import org.sopt.domain.user.presentation.dto.UserLoginRequest;
import org.sopt.global.common.exception.ApiResponse;
import org.sopt.global.common.jwt.dto.JwtTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserCommandUsecase userCommandUsecase;

	@PostMapping
	public ResponseEntity<ApiResponse<Void>> createUser(@Valid @RequestBody UserCreateRequest request) {
		UserCreateCommand command = UserCreateCommand.from(request);
		userCommandUsecase.createUser(command);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(ApiResponse.success(HttpStatus.CREATED.value(), "회원가입이 성공적으로 완료되었습니다.", null));
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<JwtTokenResponse>> login(@Valid @RequestBody UserLoginRequest request) {
		UserLoginCommand command = UserLoginCommand.from(request);
		String token = userCommandUsecase.login(command);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(ApiResponse.success(HttpStatus.OK.value(), "로그인 성공", new JwtTokenResponse(token)));
	}
}
