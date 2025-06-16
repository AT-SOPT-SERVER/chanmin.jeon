package org.sopt.domain.comment.application.dto;

import org.sopt.domain.comment.presentation.dto.CommentCreateRequest;

public record CommentCreateCommand(
	Long postId,
	Long userId,
	String content
) {

	public static CommentCreateCommand from(Long postId, Long userId, CommentCreateRequest request) {
		return new CommentCreateCommand(postId, userId, request.content());
	}
}
