package org.sopt.domain.user.presentation;

import org.sopt.domain.user.application.dto.UserCreateCommand;
import org.sopt.domain.user.application.dto.UserLoginCommand;
import org.sopt.domain.user.application.usecase.UserCommandUsecase;
import org.sopt.domain.user.presentation.dto.UserCreateRequest;
import org.sopt.domain.user.presentation.dto.UserLoginRequest;
import org.sopt.global.response.ApiResponse;
import org.sopt.global.security.jwt.dto.JwtTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserCommandUsecase userCommandUsecase;

	@PostMapping
	@Operation(summary = "유저 생성", description = "유저를 생성합니다.")
	public ResponseEntity<ApiResponse<Void>> createUser(@Valid @RequestBody UserCreateRequest request) {
		UserCreateCommand command = UserCreateCommand.from(request);
		userCommandUsecase.createUser(command);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(ApiResponse.success(HttpStatus.CREATED.value(), "회원가입이 성공적으로 완료되었습니다.", null));
	}

	@PostMapping("/login")
	@Operation(summary = "로그인", description = "유저 아이디와 비밀번호로 로그인을 진행합니다.")
	public ResponseEntity<ApiResponse<JwtTokenResponse>> login(@Valid @RequestBody UserLoginRequest request) {
		UserLoginCommand command = UserLoginCommand.from(request);
		String token = userCommandUsecase.login(command);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(ApiResponse.success(HttpStatus.OK.value(), "로그인 성공", new JwtTokenResponse(token)));
	}
}
