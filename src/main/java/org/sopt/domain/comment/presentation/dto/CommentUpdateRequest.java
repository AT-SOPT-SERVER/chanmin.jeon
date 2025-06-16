package org.sopt.domain.comment.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentUpdateRequest(
	@NotBlank(message = "댓글 내용은 필수입니다.")
	@Size(max = 300, message = "댓글은 최대 300자까지 작성할 수 있습니다.")
	String content
) {

}
