package org.sopt.domain.post.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostUpdateRequest(

	@NotBlank(message = "제목은 필수입니다.")
	@Size(max = 30, message = "제목은 최대 30자까지 입력 가능합니다.")
	String title,

	@NotBlank(message = "내용은 필수입니다.")
	@Size(max = 1000, message = "내용은 최대 1000자까지 입력 가능합니다.")
	String content,

	@NotBlank(message = "태그는 필수입니다.")
	String tag
) {
}
