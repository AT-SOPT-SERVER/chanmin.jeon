package org.sopt.domain.user.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(

	@NotBlank(message = "닉네임은 필수입니다.")
	@Size(max = 10, message = "넥네임은 10자 이하로 입력해주세요.")
	String author,

	@NotBlank(message = "비밀번호는 필수입니다.")
	@Pattern(
		regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$",
		message = "비밀번호는 최소 8자 이상, 영문, 숫자, 특수문자를 포함해야 합니다."
	)
	String password
) {
}
