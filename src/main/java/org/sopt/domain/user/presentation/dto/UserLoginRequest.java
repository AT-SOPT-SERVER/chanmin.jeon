package org.sopt.domain.user.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(

	@NotBlank(message = "닉네임은 필수입니다.")
	String author,

	@NotBlank(message = "비밀번호는 필수입니다.")
	String password
) {
}
